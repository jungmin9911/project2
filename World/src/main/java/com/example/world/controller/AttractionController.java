package com.example.world.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping("/attractionDetail")
	public String attractionDetail(@RequestParam("aseq") int aseq, Model model) {
		
		AttractionVO attractionvo = ats.getAttraction(aseq);
        model.addAttribute("result", attractionvo);

        return "attraction/attractionDetail";
	}
	
	@RequestMapping("/attractionForm")
    public String attractionForm(Model model) {
		
        List<AttractionVO> attractionList = ats.getAttractionList();
        model.addAttribute("attractionList", attractionList);
        return "attraction/attractionForm";
    }
	
	@RequestMapping("/parade")
	 public String parade() {
		
	        return "parade/parade";
	    }
	
}
