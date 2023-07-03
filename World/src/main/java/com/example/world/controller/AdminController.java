package com.example.world.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.world.dto.AdminVo;
import com.example.world.dto.AttractionVO;
import com.example.world.dto.BannerVO;
import com.example.world.dto.MemberVO;
import com.example.world.dto.NoticeVO;
import com.example.world.dto.Paging;
import com.example.world.dto.QnaVO;
import com.example.world.service.AdminService;
import com.example.world.service.AttractionService;
import com.example.world.service.QnaService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;



@Controller
public class AdminController {

	@Autowired
	AdminService as;
	
	@RequestMapping("/admin")
	public String admin() {
		return "admin/adminLogin/adminLoginForm";
	}
	
	@RequestMapping("/adminlogin")
	public ModelAndView admin_login(
						@RequestParam(value="id", required=false) String workId,
						@RequestParam(value="pwd", required=false) String workPwd, 
						HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView();
		if( workId == null ) {
			mav.addObject("msg" , "아이디를 입력하세요");
			mav.setViewName("admin/adminLogin/adminLoginForm");
			return mav;
		}else if( workId.equals("") ) {
			mav.addObject("msg" , "아이디를 입력하세요");
			mav.setViewName("admin/adminLogin/adminLoginForm");
			return mav;
		}else if( workPwd == null) {
			mav.addObject("msg" , "패쓰워드를 입력하세요");
			mav.setViewName("admin/adminLogin/adminLoginForm");
			return mav;
		}else if( workPwd.equals("")) {
			mav.addObject("msg" , "패쓰워드를 입력하세요");
			mav.setViewName("admin/adminLogin/adminLoginForm");
			return mav;
		}
		
		int result = as.workerCheck( workId, workPwd );
		
		if(result == 1) {
	    		HttpSession session = request.getSession();
	    		session.setAttribute("workId", workId);
	    		mav.setViewName("redirect:/adminMain");
		} else if (result == 0) {
	        	mav.addObject("message", "비밀번호를 확인하세요.");
	        	mav.setViewName("admin/adminLogin/adminLoginForm");
		} else if (result == -1) {
	    		mav.addObject("message", "아이디를 확인하세요.");
	    		mav.setViewName("admin/adminLogin/adminLoginForm");
		}	
		
		return mav;
	}	
	
	@RequestMapping("/adminMain")
	public String adminMain() {
		return "admin/adminLogin/adminMain";
	}
	
	@RequestMapping("/adminLogout")
	public String adminLogout() {
		return "redirect:/";
	}
	
	
	//공지사항
	@RequestMapping("/adminNotice")
	public ModelAndView adminNotice(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		if(id==null)
			mav.setViewName("redirect:/adminLoginForm");
		else {
			HashMap<String, Object> result = as.getNoticeList(request);
			mav.addObject("noticeList", (List<AdminVo>)result.get("noticeList"));
			mav.addObject("paging", (Paging)result.get("paging"));
			mav.addObject("key", (String)result.get("key"));
			mav.setViewName("admin/adminNotice/adminnotice");
		}
		return mav;
	}
	
	//공지사항 디테일
	@RequestMapping("adminNoticeDetail")
	public ModelAndView adminNoticeDetail(HttpServletRequest request, 
			@RequestParam("nseq") int nseq) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		if(id==null)
			mav.setViewName("redirect:/adminLoginForm");
		else {
			mav.addObject("noticeVO", as.viewNotice(nseq));
			mav.setViewName("admin/adminNotice/adminnoticeDetail");
		}
		return mav;
	}
	
	//공지사항 수정
	@RequestMapping("/adminUpdateNoticeForm")
	public String adminUpdateNoticeForm(HttpServletRequest request, Model model) {
		return "admin/adminNotice/noticeUpdate";
	}
	
	@RequestMapping(value="noticeUpdate", method=RequestMethod.POST)
	public String noticeUpdate(@ModelAttribute("dto") @Valid NoticeVO noticevo,
			BindingResult result, Model model, HttpServletRequest request) {
		String url="admin/adminNotice/noticeUpdate";
		if(result.getFieldError("id")!=null)
			model.addAttribute("message", result.getFieldError("id").getDefaultMessage());
		else if(result.getFieldError("title")!=null)
			model.addAttribute("message", result.getFieldError("title").getDefaultMessage());
		else if(result.getFieldError("ncontent")!=null)
			model.addAttribute("message", result.getFieldError("ncontent").getDefaultMessage());
		else {
			if(noticevo.getNcontent()==null||noticevo.getNcontent().equals(""))
				noticevo.setNcontent(request.getParameter("oldfilename"));
			as.updateNotice(noticevo);
			url="admin/adminNotice/admonnoticeDetail?nseq=" + noticevo.getNseq();
		}
		return url;
	}
	
	//회원관리
	@RequestMapping("/adminMember")
	public ModelAndView adminMember(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		if(id==null)
			mav.setViewName("redirect:/adminLoginForm");
		else {
		HashMap<String, Object> result = as.getMeberList ( request );
		mav.addObject("memberList", (List<MemberVO>)result.get("memberList") );
		mav.addObject("paging", (Paging)result.get("paging") );
		mav.addObject("key", (String)result.get("key") );
		mav.setViewName("admin/adminmember/adminMemberList");
		}
		return mav;
	}
	
	// 어트랙션 관리 
	
	@RequestMapping("/adminAttraction")
	public ModelAndView adminAttraction(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		if(id==null)
			mav.setViewName("redirect:/adminLoginForm");
		else {
		HashMap<String, Object> result = as.getAttractionList ( request );
		mav.addObject("attractionList", (List<AttractionVO>)result.get("attractionList") );
		mav.addObject("paging", (Paging)result.get("paging") );
		mav.addObject("key", (String)result.get("key") );
		mav.setViewName("admin/adminattraction/adminattraction");
		}
		return mav;
	}
	
	// 어트래션 추가폼으로 이동
	@RequestMapping("/atInsertForm")
	public String atInsertForm() {
		return "admin/adminattraction/insertAttractionForm";
	}
	



	// 어트랙션 추가 오류 : 부적합한 열유형 (SQL)
	@RequestMapping(value="/insertat", method = RequestMethod.POST)
	public String insertat( @ModelAttribute("dto") @Valid AttractionVO attractionvo,
							BindingResult result, 
							Model model
						) {
		
		String url = "admin/adminattraction/insertAttractionForm";
		if ( result.getFieldError("atname")!= null )
			model.addAttribute("message", result.getFieldError("atname").getDefaultMessage() );
		else if ( result.getFieldError("acontent")!= null )
			model.addAttribute("message", result.getFieldError("acontent").getDefaultMessage() );
		else if ( result.getFieldError("image")!= null )
			model.addAttribute("message", result.getFieldError("image").getDefaultMessage() );
		else if ( result.getFieldError("limitkey")!= null )
			model.addAttribute("message", result.getFieldError("limitkey").getDefaultMessage() );
		else if ( result.getFieldError("limitage")!= null )
			model.addAttribute("message", result.getFieldError("limitage").getDefaultMessage() );
		else {
			as.insertat( attractionvo );
			url ="redirect:/adminAttraction";
		}
		return url;
	}

	
	// atView
	@Autowired 
	AttractionService ats;
	
	@RequestMapping("/adminattractionDetail")
	public ModelAndView adminattractionDetail (@RequestParam("aseq") int aseq
									) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("AttractionVO", ats.getAttraction(aseq) );
		mav.setViewName("admin/adminattraction/adminattractionDetail");
		return mav;
	}
	
	// at 업뎃 폼
	@RequestMapping("adminUpdateAttractionForm")
	public String adminUpdateAttractionForm(
								@RequestParam("aseq") int aseq,
								HttpServletRequest request,
								Model model
			) {
		model.addAttribute("AttractionVO", ats.getAttraction(aseq) );
		return "admin/adminattraction/AttractionUpdate";
	}
	// at 업뎃 오류 : 부적합한 열유형 (SQL)
	@RequestMapping(value = "updateAttraction", method = RequestMethod.POST)
	public String updateAttraction(
			@ModelAttribute("dto") @Valid AttractionVO attractionvo, 
			BindingResult result, Model model, HttpServletRequest request
			
			) {
		
		String url = "" ;
	
			if( request.getParameter ("bestyn") != null) 
				attractionvo.setBestat("Y");
			else attractionvo.setBestat("N");
			
			if( request.getParameter("useyn") != null)
				attractionvo.setAresult("Y");
			else attractionvo.setAresult("N");
			
			if ( attractionvo.getImage() ==null || attractionvo.getImage().equals("") )
				attractionvo.setImage( request.getParameter("oldfilename") );
			
			as.updateAttraction ( attractionvo );
			url = "redirect:adminattractionDetail?aseq=" + attractionvo.getAseq() ;
		
		return url;
	}
		
	//attractionDelete
	
	//Q&A관리
	@RequestMapping("/adminQna")
	public ModelAndView adminQna( HttpServletRequest request
			) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		if(id==null)
			mav.setViewName("redirect:/adminLoginForm");
		else {
		HashMap<String, Object> result = as.getQnaList ( request );
		mav.addObject("qnaList", (List<QnaVO>)result.get("qnaList") );
		mav.addObject("paging", (Paging)result.get("paging") );
		mav.addObject("key", (String)result.get("key") );
		mav.setViewName("admin/adminQna/adminQnaList");
		}
		return mav;
	}
	
	// qnaview
	@Autowired 
	QnaService qs;
	
	@RequestMapping("/adminQnaView")
	public ModelAndView adminQnaView (@RequestParam("lqseq") int lqseq
									) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("qnaVO", qs.getQna(lqseq) );
		mav.setViewName("admin/adminQna/adminQnaView");
		return mav;
	}
	
	// 답글 추가 
	@RequestMapping(value="/adminQnaRepSave", method =RequestMethod.POST)
	public String adminQnaRepSave ( @RequestParam("lqseq") int lqseq,
									@RequestParam("reply") String reply
		) {
	
		as.updateQna( lqseq, reply);
		return "redirect:/adminQnaView?qseq=" + lqseq ;
	}

	
	// 배너 관리 
	
	// 배너 리스트로 이동
	@RequestMapping("/adminBannerList")
	public ModelAndView adminBannerList( ) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("bannerList", as.getBannerList() );
		mav.setViewName("admin/Banner/bannerList");
		return mav;
	}
	
	// 배너등록으로 이동 
	@RequestMapping("/newBannerWrite")
	public String newBannerWrite() {
		return "admin/Banner/writeBanner";
	}
	

	// 파일 업로드 
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="fileup", method=RequestMethod.POST)
	@ResponseBody // 추가해야 원래 페이지로 이동 
	public HashMap<String, Object> fileup( Model model, 
			HttpServletRequest request ) {
		String path = context.getRealPath("/images");
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy()
				);
			result.put("STATUS", 1);
			result.put("FILENAME", multi.getFilesystemName("fileimage") );
			
		}catch (IOException e) {e.printStackTrace();}
		return result;
	}
	
	
	// 배너 등록 
	@RequestMapping(value="/bannerWrite" )
	public String bannerWrite(  BannerVO bannervo	) {
		if( bannervo.getOrder_seq() == 9 ) bannervo.setUseyn("N");
		else bannervo.setUseyn("Y");
		as.insertBanner( bannervo );
		return "redirect:/adminBannerList";
	}
	
	// 배너 순서 변경
	@RequestMapping("/change_order")
	public String change_order(
			HttpServletRequest request,
			@RequestParam("bseq") int bseq,
			@RequestParam("changeval") int changeval 
			) {
		String useyn;
		if( changeval > 8 ) useyn="N";
		else useyn="Y";
		as.updateSeq( changeval, useyn, bseq);
		return"redirect:/adminBannerList";
	}
	
}
