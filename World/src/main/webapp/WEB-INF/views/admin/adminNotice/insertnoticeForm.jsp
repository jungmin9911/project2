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
			url:"<%=request.getContextPath() %>/fileup",    // 현재주소의 fileup 리퀘스트로 요청  http://localhost:8070/fileup
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
	            	$("#filename").append("<img src='images/"+data.FILENAME+"' height='150'/>");
	            }
	        },
	        error: function() {				alert("실패");			}
		});
	
	});
});
</script>
<article>
<h1> 공지사항 추가 </h1>  
<form name="insertnt" method="post">

	<table border="1" cellspacing="0"  width="800">
		<tr>
			<th>관리자 아이디</th> <td width="642" >
			<input type="text" name="id" size="47" 
				value="${noticeVO.id}"></td>
		</tr>
		<tr>
			<th>제목</th> <td width="642" >
			<input type="text" name="title" size="47" 
				value="${noticeVO.title}"></td>
		</tr>
		
		<tr height="250"><th>내용</th>
	    	<td width="642" style="vertical-align:top; ">
	   			<input type="hidden" name="ncontent" id="image" >
	   			<div id="filename"></div>
	   		</td>
	   	</tr>
	   	
	</table>

</form>
	<div style="position:relative; top:-70px; left:500px; ">
		<form name="fromm" id="fileupForm" method="post" enctype="multipart/form-data">
					<input type="file" name="fileimage">
					<input type="button" id="myButton" value="추가">
		</form>
	</div>
	<div class="update_buttons">
			<input class="btn" type="button" value="공지등록" onclick="go_insertnt()">
			<input class="btn" type="reset" value="목록으로" onClick="go_mov('adminnotice')" >
		</div>
</article>
<%@ include file ="../footer.jsp"%>