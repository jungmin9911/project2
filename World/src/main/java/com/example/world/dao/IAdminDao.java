package com.example.world.dao;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.example.world.dto.AdminVo;
import com.example.world.dto.BannerVO;
import com.example.world.dto.NoticeVO;
import com.example.world.dto.Paging;

@Mapper
public interface IAdminDao {

	String getPwd(String workId);

	//공지사항
	int getAllCount(String string, String string2, String key);
	List<AdminVo> listNotice(Paging paging, String key);
	Object viewNotice(int nseq);
	void updateNotice(@Valid NoticeVO noticevo);
	List<BannerVO> getBannerList();
	void insertBanner(BannerVO bannervo);

	}


