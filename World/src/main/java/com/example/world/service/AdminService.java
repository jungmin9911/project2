package com.example.world.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.world.dao.IAdminDao;
import com.example.world.dto.AdminVo;
import com.example.world.dto.NoticeVO;
import com.example.world.dto.Paging;

@Service
public class AdminService {

	@Autowired
	IAdminDao adao;

	public int workerCheck(String workId, String workPwd) {
		System.out.println("id : " + workId);
		String pwd = adao.getPwd( workId );
		System.out.println("pwd : " + pwd);
		int result=0;
		
		if(pwd == null) result = -1;   // 아이디가 없습니다
		else if( workPwd.equals(pwd)) result =  1;   // 정상 로그인
		else if( !workPwd.equals(pwd)) result =  0;  // 비번이 틀립니다
		
		return result;
	}

	//공지사항
	public HashMap<String, Object> getNoticeList(HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		
		if(request.getParameter("first")!=null) {
			session.removeAttribute("page");
			session.removeAttribute("key");
		}
		
		int page=1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		}else if(session.getAttribute("page")!=null) {
			page = (int) session.getAttribute("page");
		}else {
			page=1;
			session.removeAttribute("page");
		}
		
		String key="";
		if(request.getParameter("key")!=null) {
			key = request.getParameter("key");
			session.setAttribute("key", key);
		}else if(session.getAttribute("key")!=null) {
			key = (String)session.getAttribute("key");
		}else {
			session.removeAttribute("key");
			key="";
		}
		
		Paging paging = new Paging();
		paging.setPage(page);
		
		int count = adao.getAllCount("notice", "title", key);
		paging.setTotalCount(count);
		paging.paging();
		
		List<AdminVo> noticeList = adao.listNotice(paging, key);
		result.put("noticeList", noticeList);
		result.put("paging", paging);
		result.put("key", key);
		
		return result;
	}

	public void updateNotice(NoticeVO noticevo) {
		adao.updateNotice(noticevo);
	}

}
