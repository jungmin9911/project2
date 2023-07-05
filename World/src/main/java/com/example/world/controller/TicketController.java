package com.example.world.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.world.dto.AttractionVO;
import com.example.world.dto.Cart2VO;
import com.example.world.dto.MemberVO;
import com.example.world.dto.Paging;
import com.example.world.dto.TicketVO;
import com.example.world.service.AttractionService;
import com.example.world.service.MemberService;
import com.example.world.service.TicketService;

@Controller
public class TicketController {

	@Autowired
	TicketService ts;

	
	 @RequestMapping("/event01") 
	 public String event01() { 
		 return"notice/event01"; 
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
	
	@Autowired
	MemberService ms;
	
	@RequestMapping(value = "/passTicketInsert", method = RequestMethod.POST)
	public ModelAndView passTicketInsert( HttpServletRequest request,
									@RequestParam("kind") int kind,
									@RequestParam("p1") int p1,
									@RequestParam("p2") int p2,
									@RequestParam("visitdate2") String visitdate2
									
			) {
		
		System.out.println( visitdate2 );
	
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		String url="";
		if (mvo == null) { 
			mav.setViewName("member/login");
			return mav;
		// ---------------------로그인 확인 ------------------------
		
		}else {
			TicketVO tvo = new TicketVO();
			tvo.setKind(kind);
			tvo.setP1(p1);
			tvo.setP2(p2);
			tvo.setId(mvo.getId());
		// 오류 : 날짜 형식 인식? 못함 수정필요
			LocalDate visitDate = LocalDate.parse(visitdate2);
			tvo.setVisitdate(visitDate);
	    	

			switch (kind) {
		    case 0:

		        tvo.setPrice1(p1 * 53000);
		        tvo.setPrice2(p2 * 30000);
		        tvo.setTatname1("");
		        tvo.setTatname2("");
		        tvo.setTatname3("");
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
		
			HashMap<String, Object> result =  ms.getCartList( request );
			mav.addObject("cartList",  (List<Cart2VO>)result.get("cartList")  );
			mav.addObject("paging", (Paging)result.get("paging") );
			mav.addObject("key", (String)result.get("key") );
			mav.setViewName("mypage/cartList");
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/cartDelete", method = RequestMethod.POST)
		public ModelAndView cartDelete( HttpServletRequest request
				) {
			
			String[] cseqArr =request.getParameterValues("cseq");
			
			
			for( String cseq : cseqArr)
				ts.deleteCart(Integer.parseInt(cseq));
			
			ModelAndView mav = new ModelAndView();
			HashMap<String, Object> result =  ms.getCartList( request );
			mav.addObject("cartList",  (List<Cart2VO>)result.get("cartList")  );
			mav.addObject("paging", (Paging)result.get("paging") );
			mav.addObject("key", (String)result.get("key") );
			mav.setViewName("mypage/cartList");
			
			return mav;
		
		}
	
	/*
	@Transactional
	@RequestMapping(value = "/cartOrder", method = RequestMethod.POST)
	public ModelAndView cartOrder(HttpServletRequest request) {
	    ModelAndView mav = new ModelAndView();
	    int cnt = 10;
	    String[] cseqArr = request.getParameterValues("cseq");

	    for (String cseq : cseqArr) {
	        ts.orderCart(Integer.parseInt(cseq));
	        int visitNum = ts.orderCheck(Integer.parseInt(cseq));
	        if (visitNum > cnt) {
	            mav.addObject("message", "선택하신 날짜의 인원이 마감되었습니다 다른 방문일을 선택해 주세요");
	            System.out.println("선택하신 날짜의 인원이 마감되었습니다 다른 방문일을 선택해 주세요");
	            System.out.println();
	            System.out.println();
	            throw new RuntimeException("선택하신 날짜의 인원이 마감되었습니다");
	        }
	    }

	    HashMap<String, Object> result = ms.getCartList(request);
	    mav.addObject("cartList", (List<Cart2VO>) result.get("cartList"));
	    mav.addObject("paging", (Paging) result.get("paging"));
	    mav.addObject("key", (String) result.get("key"));
	    mav.setViewName("mypage/cartList");

	    return mav;
	}
	*/
	
	
	
	@Transactional
	@RequestMapping(value = "/cartOrder", method = RequestMethod.POST)
	public ModelAndView cartOrder(HttpServletRequest request) {
	    ModelAndView mav = new ModelAndView();
	  
	    String[] cseqArr = request.getParameterValues("cseq");

	    int result= ts.ticketBuy(cseqArr);
	    
	    if
	   
	    HashMap<String, Object> result = ms.getCartList(request);
        mav.addObject("cartList", (List<Cart2VO>) result.get("cartList"));
        mav.addObject("paging", (Paging) result.get("paging"));
        mav.addObject("key", (String) result.get("key"));
        mav.setViewName("mypage/cartList"); // 에러 페이지로 이동하도록 설정
        mav.addObject("error", "오류가 발생했습니다.");
	    return mav;
	}

	
	
	
	
}
