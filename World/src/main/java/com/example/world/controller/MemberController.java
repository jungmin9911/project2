package com.example.world.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.world.dto.KakaoProfile;
import com.example.world.dto.KakaoProfile.KakaoAccount;
import com.example.world.dto.KakaoProfile.KakaoAccount.Profile;
import com.example.world.dto.MemberVO;
import com.example.world.dto.OAuthToken;
import com.example.world.service.MemberService;
import com.google.gson.Gson;

@Controller
public class MemberController {

	@Autowired
	MemberService ms;
	
	@RequestMapping("loginForm")
	public String login_form() {
		return "member/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("dto") @Valid MemberVO membervo, BindingResult result, Model model,
			HttpServletRequest request) {
		String url = "member/login";
		if (result.getFieldError("id") != null)
			model.addAttribute("message", result.getFieldError("id").getDefaultMessage());
		else if (result.getFieldError("pwd") != null)
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
		else {

			MemberVO mvo = ms.getMember(membervo.getId());
			if (mvo == null)
				model.addAttribute("message", "ID가 없습니다");
			else if (mvo.getPwd() == null)
				model.addAttribute("message", "관리자에게 문의하세요");
			else if (!mvo.getPwd().equals(membervo.getPwd()))
				model.addAttribute("message", "암호가 잘못되었습니다");
			else if (mvo.getPwd().equals(membervo.getPwd())) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", mvo);
				url = "redirect:/";
			}
		}
		return url;
	}

	@RequestMapping("/logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		return "redirect:/";
	}

	@RequestMapping("/kakaostart")
	public @ResponseBody String kakaostart() {
		String a = "<script type='text/javascript'>" + "location.href='https://kauth.kakao.com/oauth/authorize?"
				+ "client_id=fb58d066fb2ae98931cb969be61cf039&" + "redirect_uri=http://localhost:8070/kakaoLogin&"
				+ "response_type=code';" + "</script>";
		return a;
	}

	@RequestMapping("/kakaoLogin")
	public String loginKakao(HttpServletRequest request) throws UnsupportedEncodingException, IOException {

		// 카카오 아이디, 비번 인증 + 아이디 이메일 제공 동의 후 전송되는 암호화 코드
		String code = request.getParameter("code");
		// 전송된 암호화코드를 이용해서 토큰을 요청
		// 토큰 요청 주소 url 설정 및 파라미터
		String endpoint = "https://kauth.kakao.com/oauth/token";
		URL url = new URL(endpoint); // import java.net.URL;
		String bodyData = "grant_type=authorization_code&";
		bodyData += "client_id=fb58d066fb2ae98931cb969be61cf039&";
		bodyData += "redirect_uri=http://localhost:8070/kakaoLogin&";
		bodyData += "code=" + code;
		// Stream 연결 및 토큰 수신
		HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // import java.net.HttpURLConnection;
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		conn.setDoOutput(true);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
		bw.write(bodyData);
		bw.flush();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String input = "";
		StringBuilder sb = new StringBuilder(); // 조각난 String 을 조립하기위한 객체
		while ((input = br.readLine()) != null) {
			sb.append(input);
			System.out.println(input); // 수신된 토큰을 콘솔에 출력합니다
		}
		

		// 위 토큰내용을 본따 만든 클래스에 gson 파싱으로 옮겨 담습니다( sb -> OAuthToken )
		Gson gson = new Gson();
		OAuthToken oAuthToken = gson.fromJson(sb.toString(), OAuthToken.class);

		// oAuthToken을 이용해서 사용자 정보를 요청 수신
		String endpoint2 = "https://kapi.kakao.com/v2/user/me";
		URL url2 = new URL(endpoint2);
		// import java.net.HttpURLConnection;
		HttpsURLConnection conn2 = (HttpsURLConnection) url2.openConnection();
		conn2.setRequestProperty("Authorization", "Bearer " + oAuthToken.getAccess_token());
		conn2.setDoOutput(true);
		BufferedReader br2 = new BufferedReader(new InputStreamReader(conn2.getInputStream(), "UTF-8"));
		String input2 = "";
		StringBuilder sb2 = new StringBuilder();
		while ((input2 = br2.readLine()) != null) {
			sb2.append(input2);
			System.out.println(input2);
		}
	

		// 전달받은 회원정보를 kakaoProfile 객체에 담습니다.( sb2 -> kakaoProfile )
		Gson gson2 = new Gson();
		KakaoProfile kakaoProfile = gson2.fromJson(sb2.toString(), KakaoProfile.class);

		System.out.println(kakaoProfile.getId());
		KakaoAccount ac = kakaoProfile.getAccount();
		System.out.println(ac.getEmail());
		Profile pf = ac.getProfile();
		System.out.println(pf.getNickname());

		MemberVO mvo = ms.getMember(kakaoProfile.getId());
		if (mvo == null) {
			mvo = new MemberVO();
			mvo.setId(kakaoProfile.getId());
			mvo.setEmail(ac.getEmail());
			mvo.setName(pf.getNickname());
			mvo.setProvider("kakao");
			ms.joinKakao(mvo);
		}

		HttpSession session = request.getSession();
		session.setAttribute("loginUser", mvo);

		return "redirect:/";
	}

	@RequestMapping("/contract")
	public String contract() {
		return "member/contract";
	}

	@RequestMapping("/joinForm")
	public String join_form() {
		return "member/join";
	}

	@RequestMapping("idCheckForm")
	public String id_check_form(@RequestParam("id") String id, Model model) {
		MemberVO mvo = ms.getMember(id);
		int result = 0;
		if (mvo == null)
			result = -1;
		else
			result = 1;
		model.addAttribute("result", result);
		model.addAttribute("id", id);
		return "member/idcheck";
	}

	@RequestMapping(value = "join", method=RequestMethod.POST)
	public String join(
					@ModelAttribute("dto") @Valid MemberVO membervo,
					BindingResult result, Model model, HttpServletRequest request, 
					@RequestParam("reid") String reid, @RequestParam("pwdCheck") String pwdCheck) {
		
		model.addAttribute("reid", reid);
		String url = "member/joinForm";
		
		if( result.getFieldError("id")!=null) 
			model.addAttribute("message", result.getFieldError("id").getDefaultMessage());
		else if( result.getFieldError("pwd")!=null) 
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
		else if( result.getFieldError("name")!=null) 
			model.addAttribute("message", result.getFieldError("name").getDefaultMessage());
		else if( result.getFieldError("email")!=null) 
			model.addAttribute("message", result.getFieldError("email").getDefaultMessage());
		else if( !reid.equals(membervo.getId()) ) 
			model.addAttribute("message", "id 중복체크를 하지 않았습니다");
		else if( !pwdCheck.equals( membervo.getPwd() ) ) 
			model.addAttribute("message", "비밀번호 확인이 일치하지 않습니다");
		else {
			model.addAttribute("message", "회원가입이 완료되었습니다. 로그인 하세요");
			ms.insertMember(membervo);
			url = "member/login";
		}
		return url;
	}
	
	
	
	@RequestMapping("/memberEditForm")
	public String member_Edit_Form(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		model.addAttribute("dto", mvo);
		
		return "member/memberUpdateForm";
	}

	
	@RequestMapping(value = "memberUpdate",  method=RequestMethod.POST)
	public String memberUpdate(@ModelAttribute("dto") @Valid MemberVO membervo,
			BindingResult result, Model model, HttpServletRequest request,
			@RequestParam(value="pwdCheck", required=false) String pwdCheck ) {
		
		String url = "member/memberUpdateForm";
		
		if( membervo.getProvider().equals("") ) {
			
			if( result.getFieldError("pwd")!=null ) 
				request.setAttribute("message", "비밀번호를 입력하세요");
			else if( !pwdCheck.equals( membervo.getPwd() ) ) 
				request.setAttribute("message", "비밀번호 확인이 일치하지 않습니다");
			else if( result.getFieldError("name")!=null) {
				request.setAttribute("message", result.getFieldError("name").getDefaultMessage());
			}else{
				ms.updateMember(membervo);
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", membervo);
				url="redirect:/";
			}
			
		}else {
			
			if( result.getFieldError("name")!=null ) 
				request.setAttribute("message", result.getFieldError("name").getDefaultMessage());
			else {
				ms.updateMember(membervo);
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", membervo);
				url="redirect:/";
			}
		}
		
		return url;
	}
	
	
	
	@RequestMapping("/withdrawal")
	public String withdrawal( HttpServletRequest request , Model model) {
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		ms.withdrawalMember( mvo.getId() );
		model.addAttribute("message" , "회원탈퇴가 정상적으로 처리되었습니다");
		return "member/login";
	}

	
	@RequestMapping("/selectId")
	public String selectId() {
		return "member/findId";	
 	}
	
	
	@RequestMapping("/re")
	public String re(
			HttpServletRequest request , Model model,
			@RequestParam("name") String name,
			@RequestParam("phone") String phone
			) {
		
		if (name != null && !name.isEmpty() && phone != null && !phone.isEmpty()) {
	          MemberVO member = ms.selectId(name, phone);
	          if( member==null) {
	        	  model.addAttribute("message", "일치하는 정보가 없습니다");
	          }else {
	        	  model.addAttribute("Lmember", member);
	          }
	          
	          //System.out.println(member.getName());
	         //System.out.println(member.getPhone());
	      }
		
		return "member/findId";	
 	}
	
	
}


















