<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../header.jsp"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#myButton').click( function(){
		
		var formselect = $("#fileupForm")[0]; 
		var formdata = new FormData(formselect);   
		
		$.ajax({    
			url:"<%=request.getContextPath() %>/fileupno",    // 현재주소의 fileup 리퀘스트로 요청  http://localhost:8070/fileup
			type:"POST",
			enctype:"multipart/form-data",
			async: false, 
			data: formdata,
	    	timeout: 10000,
	    	contentType : false,
	        processData : false,
	        
	        success : function(data){ 
	            if( data.STATUS == 1 ){
	            	$("#filename").append("<div>"+data.FILENAME+"</div>");
	            	$("#image").val(data.FILENAME);
	            	$("#filename").append("<img src='images/notice_images/"+data.FILENAME+"' height='150'/>");
	            }
	        },
	        error: function() {				alert("실패");			}
		});
	
	});
});
</script>
<style>
 input {
  width: 400px;
  height: 46px;
  font-size: 20px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}</style>
<article>
<div class="findfindd">
<div class="id_foundd">
<h1 style="font-size:50px; text-align:center; height:110px;margin-top:40px"> 공지사항 수정 </h1>  
<form name="frm" method="post">
	<input type="hidden" name="nseq" value="${noticeVO.nseq}">
 	<input type="hidden" name="oldfilename" value="${noticeVO.ncontent}">
	
	<table class= "baba" >
		<tr>
			<th>&nbsp;관리자 아이디</th> <td >
			<input type="text" name="id" size="47" 
				value="${noticeVO.id}"></td>
		</tr>
		</table>
		<table class= "baba" >
		<tr>
			<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제목&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th> <td >
			<input type="text" name="title" size="47" 
				value="${noticeVO.title}"></td>
		</tr>
		</table>
		<table class= "baba" >
		<tr height="250"><th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;내용&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
	    	<td width="642" style="vertical-align:top; ">
	    	<img src="images/notice_images/${noticeVO.ncontent}" width="500px" height="300px">
	   			<input type="hidden" name="ncontent" id="image" >
	   			<div id="filename"></div>
	   		</td>
	   	</tr>
	   	
	</table>

</form>
</div>
</div>
	<div style="position:relative;width: 600px;left: 1000px;bottom:100px ">
		<form name="fromm" id="fileupForm" method="post" enctype="multipart/form-data">
					<input type="file" name="fileimage"id="mymyy">
					<input type="button" id="myButtonn" value="추가">
		</form>
	</div>
	<div class="update_buttonss">
			<input class="btnpp" type="button" value="수정" onclick="go_mod_savent()">
			<input class="btnnn" type="reset" value="취소" onClick="location.href='adminNotice'"> 
		</div>
</article>
<%@ include file ="../footer.jsp"%>