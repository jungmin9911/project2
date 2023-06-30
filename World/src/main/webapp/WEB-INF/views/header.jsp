<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>World</title>
<script src="script/jquery-3.6.4.js"></script>
<script src="script/script.js"></script>
<script src="script/event02.js"></script>  
<script src="script/member.js"></script>
<link href="css/world.css" rel="stylesheet">
</head>
<body>
<div id="wrap">
	<header>
		<div id ="top_menu">
			<ul>
				<c:choose>
					<c:when test ="${empty loginUser}">
						<li><a href="loginForm">login</a></li>
						<li><a href="/contract">회원가입</a></li>
						<li><a href="/admin">관리자</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="world.do?command=cartList&changMenu=1">마이페이지</a></li>
						<li><a href="/logout">logout</a></li>
						<li><a href="world.do?command=editForm">정보수정</a></li>
						<li>${loginUser.name}(${loginUser.id})</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div id="sub_menu">
			<div class="logo">
				<a href="webmain">
					<img src="images/mainlogo.jpg"/>
				</a>
			</div>
			<div class="category">
				<div class="dropdown">
					<button class="dropbtn"> 
						<span style="">즐길거리</span>
					</button>
					<div class="dropdown-content">
						<a href="/attractionForm">어트렉션</a>
						<a href="/parade">퍼레이드</a>
					</div>
				</div>
				<div class="dropdown">
					<button class="dropbtn"> 
						<span>예매하기</span>
					</button>
					<div class="dropdown-content">
			        	<a href="reserve">예매</a>
			        	<a href="event01">이달의 혜택</a>
					</div>
				</div>
				<div class="dropdown">
					<button class="dropbtn"> 
						<span>이용가이드</span>
					</button>
					<div class="dropdown-content">
						<a href="guide">운영/운휴</a>
						<a href="howCome">오시는 길</a>
					</div>
				</div>
				<div class="dropdown">
					<button class="dropbtn"> 
						<span>소통서비스</span>
				    </button>
				    <div class="dropdown-content">
						<a href="notice">공지사항</a>
						<a href="qna">Q&amp;A</a>
				    </div>
				</div>
			</div>
		</div>
	</header>
