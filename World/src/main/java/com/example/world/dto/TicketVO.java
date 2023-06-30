package com.example.world.dto;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TicketVO {
	
	private Integer cseq;
	private String id;  
	private Integer kind;  
	private Integer p1;
	private Integer p2;
	private String tatname1;
	private String tatname2;
	private String tatname3;
	private Timestamp indate;	
	private Date visitdate;
	private Integer price1;
	private Integer price2;
	private Integer result;
	
	
	
	
}
