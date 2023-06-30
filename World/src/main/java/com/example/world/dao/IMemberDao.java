package com.example.world.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.world.dto.MemberVO;

@Mapper
public interface IMemberDao {

	MemberVO getMember(String id);
	void joinKakao(MemberVO mvo);
	void insertMember(MemberVO membervo);
	void updateMember(MemberVO membervo);
	void withDrawalMember(String id);
	MemberVO selectId(String name, String phone);

}
