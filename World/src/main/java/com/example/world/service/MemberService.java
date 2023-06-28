package com.example.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.AmusementPark.dao.IMemberDao;

@Service
public class MemberService {

	@Autowired
	IMemberDao mdao;
}
