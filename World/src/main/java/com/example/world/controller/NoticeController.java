package com.example.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.world.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	NoticeService ns;
	
	@RequestMapping("/notice")
	public String notice() {
		return "qna/qna";
	}
	
}
