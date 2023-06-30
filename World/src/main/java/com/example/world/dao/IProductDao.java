package com.example.world.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.world.dto.BannerVO;
import com.example.world.dto.ProductVo;

@Mapper
public interface IProductDao {

	List<ProductVo> getNewList();
	List<ProductVo> getBestList();
	List<ProductVo> getKindList(String kind);
	ProductVo getProduct(int pseq);
	List<BannerVO> getBannerList();

}
