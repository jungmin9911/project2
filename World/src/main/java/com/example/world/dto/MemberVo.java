package com.example.world.dto;

import java.sql.Timestamp;

import lombok.Data;


@Data
public class MemberVo {
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String zip_num;
	private String address1;
	private String address2;
	private String phone;
	private String useyn;
	private Timestamp indate;
	
	
	
	
}
