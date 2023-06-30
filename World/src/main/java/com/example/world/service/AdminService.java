package com.example.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.world.dao.IAdminDao;

@Service
public class AdminService {

	@Autowired
	IAdminDao idao;

	public int workerCheck(String lworkId, String lworkPwd) {
		
		System.out.println("id : " + workId);
		String pwd = adao.getPwd( workId );
		System.out.println("pwd : " + pwd);
		int result=0;
		
		if(pwd == null) result = -1;   // 아이디가 없습니다
		else if( workPwd.equals(pwd)) result =  1;   // 정상 로그인
		else if( !workPwd.equals(pwd)) result =  0;  // 비번이 틀립니다
		
		return result;
	}
}
