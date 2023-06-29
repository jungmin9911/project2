package com.example.world.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.world.dto.AttractionVO;

@Mapper
public interface IAttractionDao {

	ArrayList<AttractionVO> getBestattraction();

}
