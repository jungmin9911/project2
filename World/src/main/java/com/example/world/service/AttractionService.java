package com.example.world.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.world.dao.IAttractionDao;
import com.example.world.dto.AttractionVO;

@Service
public class AttractionService {

	@Autowired
	IAttractionDao atdao;

	public List<AttractionVO> getAttraction(int pseq) {
		
		return atdao.getAttraction(pseq);
		
		
		
  }
  

	public ArrayList<AttractionVO> getBestattraction() {
		
		return atdao.getBestattraction();
	}
  
}
