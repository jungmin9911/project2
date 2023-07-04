<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 재설정</title>
<script src="script/member.js"></script>
<link href="css/world.css" rel="stylesheet">
<style>
input {
  width: 230px;
  height: 32px;
  font-size: 10px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}

</style>
</head>
<body>
<div class="findfind">
<div class="id_found">
   <h2 class="abcde">비밀번호 재설정</h2>
  
   <form method="post" name="formm" action="world.do">
      <input type="hidden" name="command" value="selectPwd" />
      <table class="find-id">
         <tr>

         <th>&nbsp;&nbsp;&nbsp;&nbsp;아이디&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><td><input type="text" name="id" class="input-text" maxlength="30" value="${param.id}" readonly /></td>

         </tr>
          </table>
          <table class="find-id">
         <tr>

         <th>&nbsp;&nbsp;&nbsp;비밀번호&nbsp;&nbsp;&nbsp;</th><td><input type="text" name="name" class="input-text" maxlength="30" /></td>

         </tr>
           </table>
            <table class="find-id">
         <tr>

         <th>비밀번호 확인</th><td><input type="text" name="phone" class="input-text" maxlength="30" /></td>

         </tr>
      </table>
      <br />
      <div class="founfd">
      <input type="button" value="재설정" class="button" onclick="checkPwd();" />  </div>
  	  <div class="founfff">
  	  <input type="button" value="취소" class="button">
      <br><h2>${message}</h2></div>
    
      </form>
    </div>
   </div>

</body>
</html>
