package com.example.world.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.world.dto.MemberVo;

@Mapper
public interface IMemberDao {

	MemberVo getMember(String id);
	void joinKakao(MemberVo mvo);
	void insertMember(MemberVo membervo);
	void updateMember(MemberVo membervo);
	void withDrawalMember(String id);

}
