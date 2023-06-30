package com.example.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.world.dao.ITicketDao;
import com.example.world.dto.TicketVO;

@Service
public class TicketService {
	@Autowired
	ITicketDao tdao;

	public void insertTicket(TicketVO tvo) {
		tdao.insertTicket(tvo);
		
	}


}

