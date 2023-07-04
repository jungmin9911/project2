package com.example.world.dao;


import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.world.dto.Cart2VO;
import com.example.world.dto.TicketVO;

@Mapper
public interface ITicketDao {

	void insertTicket(TicketVO tvo);

	void deleteCart(int cseq);

	void orderCart(int cseq);

	ArrayList<Cart2VO> getvisitList(LocalDate visitDate);


}
