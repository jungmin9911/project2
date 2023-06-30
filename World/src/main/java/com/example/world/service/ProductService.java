package com.example.world.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.world.dao.IProductDao;
import com.example.world.dto.BannerVO;
import com.example.world.dto.ProductVo;

@Service
public class ProductService {

	@Autowired
	IProductDao pdao;

	public HashMap<String, Object> getBestNewList() {
		HashMap<String, Object> result = new HashMap<String, Object>();

		List<ProductVo> newList = pdao.getNewList();
		List<ProductVo> bestList = pdao.getBestList();
		
		result.put("newProductList", newList);
		result.put("bestProductList", bestList);
		
		return result;
	}

	public List<ProductVo> getKindList(String kind) {
		return pdao.getKindList( kind );
	}

	public ProductVo getProduct(int pseq) {
		return pdao.getProduct( pseq );
	}

	public List<BannerVO> getBannerList() {
		return pdao.getBannerList();
	}
}


