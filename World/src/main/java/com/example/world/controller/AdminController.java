package com.example.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.world.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	AdminService as;
}
