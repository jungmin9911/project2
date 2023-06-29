package com.example.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.world.service.TicketService;

@Controller
public class TicketController {

	@Autowired
	TicketService os;
}
