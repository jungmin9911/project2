package com.example.world.dto;

import java.sql.Timestamp;

import lombok.Data;


@Data
public class TicketVO {
	private Integer ptseq;
	private Integer apassprice;
	private Integer cpassprice;
	private Integer tpassprice;
	private Integer cfastprice;
	private Integer afastprice;
	private Timestamp visitdate;
	private Integer aquantity;
	private Integer tquantity;
	private Integer cquantity;
	private Timestamp indate;
	
	
	
	
	
}
