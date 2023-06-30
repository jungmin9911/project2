package com.example.world.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.world.dto.TicketVO;

@Mapper
public interface ITicketDao {

	void insertTicket(TicketVO tvo);


}
