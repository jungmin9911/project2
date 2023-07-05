<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../header.jsp" %>  

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
<div class="attatt">
<div class="addadd">
<h1 style="font-size:50px; text-align:center; height:110px;margin-top:40px"> 어트랙션 추가 </h1>  
<form name="insertat"  method="post">

<table class="ttatta">
		<tr>
			<th>어트랙션이름</th> <td width="642" >
			<input type="text" name="atname" size="47" ></td>
		</tr>
		</table>
		<table class="ttatta">
		<tr>
			<th>어트랙션 설명</th> <td width="642">
			<input type="text" name="acontent" size="47" ></td>
		</tr>
		</table>
		<table class="ttatta">
		<tr>
			<th>태그1</th> <td width="642" >
			<input type="text" name="act1" size="47" ></td>
		</tr>
		</table>
		<table class="ttatta">
		<tr>
			<th>태그2</th> <td width="642" >
			<input type="text" name="act2" size="47" ></td>
		</tr>
		</table>
		<table class="ttatta">
		<tr>
			<th>탑승인원(숫자)</th> <td width="642" >
			<input type="text" name="pnum" size="47" ></td>
		</tr>
		</table>
		<table class="ttatta">
		<tr>
			<th>제한사항1(키)</th> <td width="642" >
			<input type="text" name="limitkey" size="47" ></td>
		</tr>
		</table>
		<table class="ttatta">
		<tr>
			<th>제한사항2(나이)</th> <td width="642" >
			<input type="text" name="limitage" size="47" ></td>
		</tr>
		</table>
		<table class="ttatta">
		<tr>
			<th>베스트(Y/N)</th> <td width="642" >
			<input type="text" name="bestat" size="47" ></td>
		</tr>
		</table>
		<table class="ttatta">
		<tr>
			<th>운휴정보(Y/N)</th> <td width="642" >
			<input type="text" name="aresult" size="47" ></td>
		</tr>
	</table>
	
		<tr style="height:250px"><th>어트랙션 이미지</th>
	    	<td width="642" style="vertical-align:top; ">
	   			<input type="hidden" name="image" id="image" >
	   			<div id="filename"></div>
	   		</td>
	   	</tr>
	   
	   	


</form>
	<div style="position:relative; top:-70px; left:500px; ">
		<form name="fromm" id="fileupForm" method="post" enctype="multipart/form-data">
					<input type="file" name="fileimage">
					<input type="button" id="myButton" value="추가">
		</form>
	</div>
	
	<div class="update_buttons">
			<input class="btn" type="button" value="기구등록" onclick="go_insertat()">
			<input class="btn" type="button" value="목록으로" onclick="go_mov('adminAttraction')">
	</div> 
	</div>
	</div>
	</article>
<%@ include file ="../footer.jsp"%>