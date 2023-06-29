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

import com.example.world.dto.MemberVO;

import com.example.world.dto.NoticeVO;
import com.example.world.dto.Paging;
import com.example.world.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	NoticeService ns;
	
	//운영운휴
	@RequestMapping("/guide")
	public String guide() {
		return "guide/operation";
	}
	
	//오시는길
	@RequestMapping("/howCome")
	public String howCome() {
		return "guide/howCome";
	}
	
	//공지사항
	@RequestMapping("/notice")
	public ModelAndView notice(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		/*
		 * if(mvo==null) mav.setViewName("member/login"); else {
		 */
			HashMap<String, Object> result = ns.getNotice(request);
			mav.addObject("notice", (List<NoticeVO>)result.get("noticeList"));
			mav.addObject("paging", (Paging)result.get("paging"));
			mav.addObject("key", (String)result.get("key"));
			mav.setViewName("notice/notice");
		/*}*/
		return mav;
	}
	
	@RequestMapping("noticeDetail")
	public ModelAndView noticeDetail(HttpServletRequest request, 
			@RequestParam("nseq") int nseq) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		/*if(mvo==null) mav.setViewName("member/login");
		else {*/
			mav.addObject("noticeVO", ns.viewNotice(nseq));
			mav.setViewName("notice/noticeDetail");
			/* } */
		return mav;
	}
	
}
