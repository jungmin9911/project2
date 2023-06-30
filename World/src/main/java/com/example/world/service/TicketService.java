package com.example.world.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.world.dao.ITicketDao;
import com.example.world.dto.AttractionVO;

@Service
public class TicketService {
	@Autowired
	ITicketDao tdao;


}

