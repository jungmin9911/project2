package com.example.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ezen.AmusementPark.service.QnaService;

@Controller
public class QnaController {

	@Autowired
	QnaService qs;
}
