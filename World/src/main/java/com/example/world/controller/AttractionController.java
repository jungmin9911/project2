package com.example.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.world.dto.AttractionVO;
import com.example.world.service.AttractionService;

@Controller
public class AttractionController {

	@Autowired
	AttractionService ats;
	
	@RequestMapping("/attractionDetail")
	public String attractionDetail(@RequestParam("pseq") int pseq, Model model) {
	    
		 AttractionVO attractionvo = ats.getAttraction(pseq);
	     model.addAttribute("attraction", attractionvo);
		
	    return "attractionDetail"; 
	}

	
	
	
	
	
	
	
}
