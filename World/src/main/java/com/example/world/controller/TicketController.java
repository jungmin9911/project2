package com.example.world.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.world.dto.AttractionVO;
import com.example.world.dto.MemberVO;
import com.example.world.service.AttractionService;
import com.example.world.service.TicketService;

@Controller
public class TicketController {

	@Autowired
	TicketService ts;

	@RequestMapping("/event01")
	public String event01() {
		return "notice/event01";
	}

	@RequestMapping("/reserve")
	public String reserve() {
		return "order/reserve";
	}

	@RequestMapping("/passTicket")
	public String passTicket(HttpServletRequest request) {

		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		String url="";
		if (mvo == null) return "member/login";
		else url = "order/passTicket";
		return url ;
	}
	
	@Autowired
	AttractionService ats;

	@RequestMapping("/fastTicket")
	public String  fastticket(HttpServletRequest request,
							  Model model
									) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		String url="";
		if (mvo == null)
			return "member/login";
		else {
			List<AttractionVO> aseqList = ats.getAseqList();
			model.addAttribute("aseqList", aseqList);
			url = "order/fastTicket";
		}
		return url ;
	}
	
	
	
}
