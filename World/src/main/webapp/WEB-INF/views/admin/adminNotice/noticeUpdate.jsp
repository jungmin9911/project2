<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../header.jsp"%>
<article>
<div class="update">
	<form method="post" name="frm" enctype="multipart/form-data" >
		<input type="hidden" name="nseq" value="${noticeVO.nseq}">
		<div class="update_title">공지사항 수정</div>
		<div class="update_main">
			<div>
				<p>제목</p><input type="text" name="title" value="${noticeVO.title}"><br>
			</div>
			<div>
		    	<p>내용</p><input type="file" name="ncontent" value=""><br>
		    </div>
		    <div>
		    	<p>관리자 아이디</p><input type="text" name="id" value="${noticeVO.id}">
		    </div>
		</div>
		<div class="update_buttons">
			<input class="btn" type="button" value="공지수정" onclick="go_mod_savent('${noticeVO.nseq}')">
			<input class="btn" type="reset" value="취소" onClick="goToDetail(${noticeVO.nseq})">
		</div>
	</form>		
</div>
</article>
<%@ include file ="../footer.jsp"%>