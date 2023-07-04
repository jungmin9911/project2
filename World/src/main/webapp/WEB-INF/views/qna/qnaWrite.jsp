<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<article>
<div class="qna">
	<div class="qnaimg" style="background-image:url('images/qnaaa.png');"></div>
	<div class="qnaBox">
		<h2> 1:1 고객 게시판 </h2>
		<h3>고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h3>
		<form class="fix02" name="qna" method="post" action='qnaWrite'>
			<input type="hidden" name="command" value="qnaWrite">
			
			<label>Secret mode</label>
    		<input name="check" type="checkbox" value="secret" onchange="enabled()">
    			&nbsp;비밀글로 하기&nbsp;&nbsp;
    		<input name="pass" type="password" size="15" disabled="disabled"><br>
			
	    	<table class="fix03">
				<tr>
					<th>제목</th>
					<td width="500" style="text-align:left;">
						<input type="text" name="title" size="50" >
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td align="left" style="text-align:left;font-size:115%;">
						<textarea rows="8" cols="52" name="content" ></textarea>
					</td>
				</tr>
			</table>
			<br>
		<div id="buttons">
			<input type="submit" value="글쓰기" class="purpleBtn" style="border:none;" > 
			<input type="reset" value="취소" class="purpleBtn"  
				onclick="qnaList'" style="border:none;">
		</div>
		</form>
	</div>
</div>
</article>
<%@ include file="../footer.jsp" %>