<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
    $(function(){
    	var num=0;
    	setInterval(function(){
            $('#imgs').animate({ left : num * -405 },1000);
                num++;
                if(num==5)num=0;
        }, 2000);
    });
</script>

</head>
<body>

<!-- 400x300  크기의 이미지로 롤링 베너를 구현하세요-->


<div id = "main_img">
	<div id = "view" style="position:relative; width:400px; overflow:hidden; height:300px; border-radius:20px;">
		<div id ="imgs" style="position:absolute; width:2050px; height:300px; text-align:left;">
			<c:forEach items="${bannerList}" var="bannerVO">
				<img src="/product_images/${bannerVO.image}" style="width:400px; height:300px; margin:0">
			</c:forEach>
		
		</div>
	</div>
</div>


</body>
</html>