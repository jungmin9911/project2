package com.example.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ezen.AmusementPark.service.AttractionService;

@Controller
public class AttractionController {

	@Autowired
	AttractionService ats;
}
