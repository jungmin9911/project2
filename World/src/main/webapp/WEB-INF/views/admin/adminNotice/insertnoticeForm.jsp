<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../header.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#myButtonn').click( function(){
		
		var formselect = $("#fileupForm")[0]; 
		var formdata = new FormData(formselect);   
		var kindValue = "notice"; 
        
        formdata.append("kind", kindValue);
		
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
<h1 style="font-size:50px; text-align:center; height:110px;margin-top:40px"> 공지사항 추가 </h1>  
<form name="insertnt" method="post">

	<table class= "baba" >
		<tr>
			<th>&nbsp;&nbsp;관리자 아이디</th> <td>
			<input type="text" name="id" size="47" 
				value="${noticeVO.id}"></td>
		</tr>
		</table>
		<table class= "baba" >
		<tr>
			<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제목&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th> <td>
			<input type="text" name="title" size="47" 
				value="${noticeVO.title}"></td>
		</tr>
		</table>
		<table class= "baba" >
		
		<tr style="height:250px"><th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;내용</th>
	    	<td width="700" style="vertical-align:top; ">
	   			<input type="hidden" name="ncontent" id="image" >
	   			<div id="filename"></div>
	   		</td>
	   	</tr>

	</table>

</form>
</div>
</div>
<div class="update_buttonss">
			<input class="btnpp" type="button" value="공지등록" onclick="go_insertnt()">
			<input class="btnnn" type="reset" value="목록으로" onClick="go_mov('adminnotice')" >
		</div>
	<div style="position:relative; bottom:300px;width: 600px; left:1100px; ">
		<form name="fromm" id="fileupForm" method="post" enctype="multipart/form-data">
					<input type="file" name="fileimage" id="mymyy">
					<input type="button" id="myButtonn" value="추가">
		</form>
	</div>
	
</article>
<%@ include file ="../footer.jsp"%>