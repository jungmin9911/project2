package com.example.world.controller;

import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.example.world.dto.AdminVo;
import com.example.world.dto.MemberVO;
import com.example.world.dto.NoticeVO;
import com.example.world.dto.Paging;
import com.example.world.service.AdminService;


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
	
	//어트랙션
	@RequestMapping("/adminAttraction")
	public String adminAttraction() {
		return "admin/adminattraction/adminattraction";
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
	public String adminMember() {
		return "admin/adminmember/adminMemberList";
	}
	
	//Q&A관리
	@RequestMapping("/adminQna")
	public String adminQna() {
		return "admin/adminQna/adminQnaList";
	}
}
