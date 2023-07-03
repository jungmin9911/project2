<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<style>
   #buttons{text-align:center;}
.submit{position:relative; font-size: 20px;padding-bottom:10px; width:200px; height:55px;color: #fff; background:rgb(111, 35, 249);;font-weight:bold;border-radius: 28px;border:1px solid #6317ed;}
.cancel{position:relative; font-size: 20px;padding-bottom:10px; width:200px; height:55px;color:#6317ed; background:white;font-weight:bold;border-radius: 28px;border:1px solid #6317ed;}
</style>

<form name="frm" method="post">
<section class="notice">
  <div class="page-titlee">
        <div class="containerr">
            <h3 style=" margin-right:70px; font-size: 60px;color: #333333;font-weight: 400;text-align: center;"> 배너 리스트 </h3>  
        </div>
    </div>    
    <div class="search-wrap" >
    <button type="submit" class="btn btn-darkk" value="등록" 
    	onClick="go_insertform('newBannerWrite')">새배너등록</button>
   	 </div>
	 <div class="board-list">
        <div class="container">
            <table class="board-table">
                <thead>
                <tr>
                    <th scope="col" class="th-num">번호</th>
                    <th scope="col" class="th-title">제목</th>
                    <th scope="col" class="th-title">순위</th>
                    <th scope="col" class="th-title">사용유무</th>
                    <th scope="col" class="th-date">등록일</th>
<!--                     <th scope="col" class="th-answer">수정</th> -->
                </tr>
                </thead>
		
			<c:choose>
				<c:when test="${bannerListSize==0}">
					<tr>
						<td width="100%" colspan="6" align="center" height="23">등록된
							상품이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${bannerList}" var="bannerVO">
						<tr>
							<td height="23" align="center">${bannerVO.bseq}</td>
							<td
								style="text-align: left; padding-left: 50px; padding-right: 0px; width: 300px;">
								${bannerVO.subject}</td>
							<td>
								<select name="order_seq" id="${bannerVO.bseq}"
								onChange="change_order('${bannerVO.bseq}');">
									<c:forEach var="cnt" begin="1" end="8" varStatus="status">
										<c:choose>
											<c:when test="${cnt==bannerVO.order_seq}">
												<option value="${cnt}" selected>${cnt}</option>
											</c:when>
											<c:otherwise>
												<option value="${cnt}">${cnt}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:choose>
										<c:when test="${bannerVO.order_seq==9}">
											<option value="9" selected>사용안함</option>
										</c:when>
										<c:otherwise>
											<option value="9">사용안함</option>
										</c:otherwise>
									</c:choose>
							</select></td>
							<td>${bannerVO.useyn}</td>
							<td width="150"><fmt:formatDate value="${bannerVO.indate}" /></td>
						<!-- 
							<td><input type="button" value="수정"
								onClick="location.href='editBannerForm?bseq=${bannerVO.bseq}'"></td>
						 -->
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		</div>
	</div>
</section>
</form>




<%@ include file="../footer.jsp"%>