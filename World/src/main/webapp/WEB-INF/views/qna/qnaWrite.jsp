<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<style>
#buttons{text-align:center;}
</style>  

<article><br><br><br>
<h2 style="font-family: 'NotoSans-Bold', '맑은 고딕', 'Malgun Gothic', sans-serif;font-size: 55px;color: #000;text-align: center; padding-top: 50px;">이용문의</h2><br>
<div style="position: relative; text-align: center;">
  <img src="images/qna_image.jpg" style="opacity: 0.3;width:950px;">
  <div style="position: absolute; top: 50%; left: 42%; transform: translate(-50%, -50%);text-align: left;">
    <h3 style="font-size: 27px;font-family: 'NotoSans-Bold', '맑은 고딕', 'Malgun Gothic', sans-serif;line-height: 36px;margin-bottom: 27px;text-align:left;">롯데월드를 이용하기 전<br>
궁금한 사항에 대한 문의를 남겨주세요.</h3><br><h4 style="font-size: 17px; font-weight:bold;" >
⊙ 답변은 이메일 주소로 발송됩니다<br><br>
⊙ 접수 후 1주 이내 회신을 드리기 위해 노력하고 있습니다. 의견 접수가 많거나,<br>
공휴일에 접수될 경우 답변에 시간이 소요될 수 있으니 양해 부탁드립니다.<br><br>
⊙ 불건전한 내용(개인정보보안, 귀책사유에 대한 개인 음해성 비방의 글 등) 또는<br>
광고성 게시글은 사전 통보없이 삭제될 수 있습니다</h4>
 </div>
</article>
<hr>
</div>
<article>
<div id = qna_write>
<form name="qna" method="post">
	<input type="hidden" name="command" value="qnaWrite">
	<table class="qna-table" style="font-size: 13px;width: 100%;border-top: 1px solid #ccc;border-bottom: 1px solid #ccc;">
		<label >이름</label><input type="text" name="name" size="50"><br>
		<label >이메일</label><input type="text" name="email" size="50"><br>
	    <label >제목</label><input type="text" name="subject"  size="50" ><br>
		<label >내용</label><textarea rows="8" cols="52" name="content"></textarea>
	</table>
	<div class="clear"></div><br><br>
	<div id="buttons">
		<input type="submit"  value="등록"     class="submit" onclick="location.href='world.do?command=qnaList'"> 
		<input type="reset"   value="취소"     class="cancel"  onclick="location.href='world.do?command=qnaList'">
	</div>
</div>
</form>
</article><br><br>


<%@ include file="../footer.jsp" %>