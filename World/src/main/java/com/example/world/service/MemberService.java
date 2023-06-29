package com.example.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.world.dao.IMemberDao;
import com.example.world.dto.MemberVo;

@Service
public class MemberService {

	@Autowired
	IMemberDao mdao;

	public MemberVo getMember(String id) {
		return mdao.getMember(id);
	}
	
	public void joinKakao(MemberVo mvo) {
		mdao.joinKakao( mvo );	
		}

	public void insertMember(MemberVo membervo) {
		mdao.insertMember(membervo);
		}

	public void updateMember( MemberVo membervo) {
		mdao.updateMember( membervo );
		}

	public void withdrawalMember(String id) {
		mdao.withDrawalMember(id);		
		}
}
