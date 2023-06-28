package com.example.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.AmusementPark.dao.IAttractionDao;

@Service
public class AttractionService {

	@Autowired
	IAttractionDao atdao;
}
