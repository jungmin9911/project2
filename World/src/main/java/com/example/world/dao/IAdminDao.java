package com.example.world.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.world.dto.AdminVo;

@Mapper
public interface IAdminDao {

	String getPwd(String workId);

		

		
	}


