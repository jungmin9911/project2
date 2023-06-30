package com.example.world.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.world.dto.AttractionVO;
import com.example.world.dto.MemberVO;
import com.example.world.dto.TicketVO;
import com.example.world.service.AttractionService;
import com.example.world.service.TicketService;

@Controller
public class TicketController {

	@Autowired
	TicketService ts;

	/*
	 * @RequestMapping("/event01") public String event01() { return
	 * "notice/event01"; }
	 */

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
	
	@RequestMapping(value = "/passTicketInsert", method = RequestMethod.POST)
	public String passTicketInsert( HttpServletRequest request,
									@RequestParam("kind") int kind,
									@RequestParam("p1") int p1,
									@RequestParam("p2") int p2,
									@RequestParam("visitdate") String visitdate
									
			) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		String url="";
		if (mvo == null)
			return "member/login";
		// ---------------------로그인 확인 ------------------------
		else {
			TicketVO tvo = new TicketVO();
			tvo.setKind(kind);
			tvo.setP1(p1);
			tvo.setP2(p2);
			
			LocalDate visitDate = LocalDate.parse(visitdate);
			tvo.setVisitdate(visitDate);

			switch (kind) {
		    case 0:
		        tvo.setPrice1(p1 * 53000);
		        tvo.setPrice2(p2 * 30000);
		        break;
		    case 1:
		        tvo.setPrice1(p1 * 110000);
		        tvo.setPrice2(p2 * 70000);
		        String[] attrr = request.getParameterValues("attraction");
		        tvo.setTatname1(attrr[0]);
		        tvo.setTatname2(attrr[1]);
		        tvo.setTatname3(attrr[2]);
		        break;
			}
			ts.insertTicket(tvo);
			url= "redirect:/mypage/cartList";
		}
		return url;
	}
}
