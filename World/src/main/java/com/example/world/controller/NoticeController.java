package com.example.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ezen.AmusementPark.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	NoticeService ns;
}
