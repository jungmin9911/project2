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
			url:"<%=request.getContextPath() %>/fileupat",    // 현재주소의 fileup 리퀘스트로 요청  http://localhost:8070/fileup
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
	            	$("#filename").append("<img src='images/attraction_images/"+data.FILENAME+"' height='150'/>");
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
}
</style>
<article>
<div class="attatt">
<div class="addadd">
<h1 style="font-size:50px; text-align:center; height:110px;margin-top:50px; margin-left:50px;"> 어트랙션 수정 </h1>  
<form name="updateat" method="post">
	<input type="hidden" name="aseq" value="${AttractionVO.aseq}">
 	<input type="hidden" name="oldImage" value="${AttractionVO.image}">
	
	<table class="ttatta">
		<tr>
			<th>어트랙션 이름</th> <td>
			<input type="text" name="atname" size="47" 
				value="${AttractionVO.atname }"></td>
		</tr>
		</table>
		<table class="ttatta">
		<tr>
			<th>어트랙션 설명</th> <td style="display:inline-block;float:left;">
			<input type="text" name="acontent" size="47"
				value="${AttractionVO.acontent }" ></td>
		</tr>
		</table>
		<table class="ttatta">
		<tr>
			<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;태그1</th> <td style="display:inline-block; margin-left:50px; float:left;">
			<input type="text" name="act1" size="47" 
				value="${AttractionVO.act1 }"></td>
		</tr>
		</table>
		<table class="ttatta">
		<tr>
			<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;태그2</th> <td style="display:inline-block; margin-left:50px; float:left;">
			<input type="text" name="act2" size="47" 
				value="${AttractionVO.act2 }"></td>
		</tr>
		</table>
		<table class="ttatta">
		<tr>
			<th>탑승인원(숫자)&nbsp;</th> <td style="display:inline-block; margin-left:-14px; float:left;">
			<input type="text" name="pnum" size="47" 
				value="${AttractionVO.pnum }"></td>
		</tr>
	</table>
		<table class="ttatta">
		<tr>
			<th>제한사항1(키)</th> <td style="display:inline-block; margin-left:5px; float:left;">
			<input type="text" name="limitkey" size="47" 
				value="${AttractionVO.limitkey }"></td>
		</tr>
		</table>
		<table class="ttatta">
		<tr>
			<th>제한사항2(나이)</th> <td style="display:inline-block; margin-left:-16px; float:left;">
			<input type="text" name="limitage" size="47" 
				value="${AttractionVO.limitage }"></td>
		</tr>
		</table>
		<table class="ttatta">
		<tr>
			<th>베스트 어트랙션(Y/N)</th>
			<td style="display:inline-block; margin-left:50px; float:left;">
						<c:choose>
				  			<c:when test='${AttractionVO.bestat=="Y"}'>
				  				<input type="radio" style="width:17px;height:17px;border:1px;" name="bestat" value="Y" checked="checked">사용
				  				<input type="radio" style="width:17px;height:17px;border:1px;" name="bestat" value="N">미사용
				  			</c:when>
							<c:otherwise>
								<input type="radio" style="width:17px;height:17px;border:1px;" name="bestat" value="Y">사용
								<input type="radio" style="width:17px;height:17px;border:1px;" name="bestat" value="N" checked="checked">미사용
							</c:otherwise>
						</c:choose>
			</td>
		</tr>
		</table>
		<table class="ttatta">
		<tr>
			<th>운휴 정보(Y/N)</th>
			<td style="display:inline-block; margin-left:124px; float:left;">	
				<c:choose>
				 	<c:when test='${AttractionVO.aresult=="Y"}'>
				 		<input type="radio" style="width:17px;height:17px;border:1px;" name="aresult" value="Y" checked="checked">사용
						<input type="radio" style="width:17px;height:17px;border:1px;" name="aresult" value="N">미사용
				  	</c:when>
					<c:otherwise>
						<input type="radio" style="width:17px;height:17px;border:1px;" name="aresult" value="Y">사용
						<input type="radio" style="width:17px;height:17px;border:1px;" name="aresult" value="N" checked="checked">미사용
					</c:otherwise>
				</c:choose>	
			</td>
		</tr>
		</table>
		<table class="ttatta">
		<tr style="height:250px"><th>어트랙션 이미지</th>
	    	<td width="642" style="vertical-align:top; ">
	    		현재이미지 : <img src="images/${AttractionVO.image}" width="200pt"><br>
	   			<input type="hidden" name="image" id="image" >
	   			<div id="filename"></div>
	   		</td>
	   	</tr>
	   	
	</table>

</form>

	<div style="position:relative; top:13px; left:750px; width:600px;  ">
		<form name="fromm" id="fileupForm" method="post" enctype="multipart/form-data">
					<input type="file" name="fileimage"id="mymy">
					<input type="button" id="myButton" value="추가">
		</form>
	</div>
	<div class="update_buttons">
		<input class="btnp" type="button" value="수정" onclick="go_mod_saveat()">
		<input class="btnn" type="reset" value="취소"
			onclick= "location.href='adminattractionDetail?aseq=${AttractionVO.aseq}'"> 
	</div>
	</div>
	</div>
</article>


<%@ include file ="../footer.jsp"%>