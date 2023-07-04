package com.example.world.service;


import java.time.LocalDate;
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

	public ArrayList<Cart2VO> getvisitList(LocalDate visitDate) {
		
		return tdao.getvisitList(visitDate);
	}

	


}

