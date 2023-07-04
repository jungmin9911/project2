package com.example.world.service;



import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.world.dao.ITicketDao;
import com.example.world.dto.Cart2VO;
import com.example.world.dto.TicketVO;

@Service
public class TicketService {
	@Autowired
	ITicketDao tdao;

	public void insertTicket(TicketVO tvo) {
		tdao.insertTicket(tvo);
		
	}

	public void deleteCart(int cseq) {
		tdao.deleteCart(cseq);
		
	}

	public void orderCart(int cseq) {
		tdao.orderCart(cseq);
		
	}


	public int orderCheck(int cseq) {
		
		//int cnt=10;
		int visitnum=0;
		int p1=0;
		int p2=0;
		String visitDate= tdao.getvisitDate(cseq);
		
		System.out.println(visitDate);
		String result = visitDate.substring(0, 10);
		System.out.println(result);
		System.out.println();
		
		ArrayList<Cart2VO> visitList = tdao.getvisitList(result);
		
		for(int i=0; i<visitList.size(); i++ ) {
			
			p1 += visitList.get(i).getP1();
			p2 += visitList.get(i).getP2();
		}
		visitnum=p1+p2;
		
	
		System.out.println(visitnum + "명");
		System.out.println();
		
		/*
		if(visitnum > cnt) {
			//mav.addObject("message","선택하신 날짜의 인원이 마감되었습니다 다른 방문일을 선택해 주세요");
			System.out.println("선택하신 날짜의 인원이 마감되었습니다 다른 방문일을 선택해 주세요");
			System.out.println(); System.out.println();
		}
		*/
		
		
		return visitnum;
		
	}
}

