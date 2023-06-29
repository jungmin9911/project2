package com.example.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.world.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	NoticeService ns;
}
