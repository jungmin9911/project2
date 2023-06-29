package com.example.world.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.world.dto.NoticeVO;
import com.example.world.dto.Paging;

@Mapper
public interface INoticeDao {

	int getAllCount(String string, String string2, String key);
	List<NoticeVO> listNotice(Paging paging, String key);
	Object viewNotice(int nseq);

}
