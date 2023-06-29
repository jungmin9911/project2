package com.example.world.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.world.dto.AttractionVO;
import com.example.world.service.AttractionService;

@Controller
public class AttractionController {

	@Autowired
	AttractionService ats;
	
	@RequestMapping("/")
	public String start() {
		return "start";
	}
	
	@RequestMapping("/webmain")
	public ModelAndView webmain(){
		
		ModelAndView mav =new ModelAndView();
		ArrayList<AttractionVO> bestList = ats.getBestattraction();
		mav.addObject("bestList",  bestList );
		mav.setViewName("main");
		
		return mav;
	}
	
	
}
