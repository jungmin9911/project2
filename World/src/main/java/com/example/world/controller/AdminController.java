package com.example.world.controller;

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
	 public String adminPage() {
		
        return "admin/adminLogin/adminLoginForm";
    }
	
	@RequestMapping("adminLogin")
	public ModelAndView admin_login(
						@RequestParam(value="LworkId", required=false) String LworkId,
						@RequestParam(value="LworkPwd", required=false) String LworkPwd, 
						HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView();
		if( LworkId == null ) {
			mav.addObject("msg" , "아이디를 입력하세요");
			mav.setViewName("admin/adminLoginForm");
			return mav;
		}else if( LworkId.equals("") ) {
			mav.addObject("msg" , "아이디를 입력하세요");
			mav.setViewName("admin/adminLoginForm");
			return mav;
		}else if( LworkPwd == null) {
			mav.addObject("msg" , "패쓰워드를 입력하세요");
			mav.setViewName("admin/adminLoginForm");
			return mav;
		}else if( LworkPwd.equals("")) {
			mav.addObject("msg" , "패쓰워드를 입력하세요");
			mav.setViewName("admin/adminLoginForm");
			return mav;
		}
		
		int result = as.workerCheck( LworkId, LworkPwd );
		
		if(result == 1) {
	    		HttpSession session = request.getSession();
	    		session.setAttribute("workId", LworkId);
	    		mav.setViewName("redirect:/adminMain");
		} else if (result == 0) {
	        	mav.addObject("message", "비밀번호를 확인하세요.");
	        	mav.setViewName("admin/adminLoginForm");
		} else if (result == -1) {
	    		mav.addObject("message", "아이디를 확인하세요.");
	    		mav.setViewName("admin/adminLoginForm");
		}	
		
		return mav;
	}

	
}
