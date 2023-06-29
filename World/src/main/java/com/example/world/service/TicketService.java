package com.example.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.world.dao.ITicketDao;

@Service
public class TicketService {
	@Autowired
	ITicketDao odao;
}
