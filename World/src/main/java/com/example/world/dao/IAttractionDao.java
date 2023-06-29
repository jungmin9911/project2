package com.example.world.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.world.dto.AttractionVO;

@Mapper
public interface IAttractionDao {

	ArrayList<AttractionVO> getBestattraction();

	List<AttractionVO> getAttraction(int pseq);

}
