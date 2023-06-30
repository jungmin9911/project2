package com.example.world.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	}

