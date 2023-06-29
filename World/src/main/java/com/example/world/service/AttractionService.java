package com.example.world.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.world.dao.IAttractionDao;
import com.example.world.dto.AttractionVO;

@Service
public class AttractionService {

	@Autowired
	IAttractionDao atdao;

	public AttractionVO getAttraction(int pseq) {
		
		
		return null;
  }
  
  //여기 아래있는 코드 재찬님이 보시고 필요없으면 지우면 돼요! -정민
	public ArrayList<AttractionVO> getBestattraction() {
		
		return atdao.getBestattraction();
	}
  
}
