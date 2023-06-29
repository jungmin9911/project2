package com.example.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.world.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService ms;
	
}
