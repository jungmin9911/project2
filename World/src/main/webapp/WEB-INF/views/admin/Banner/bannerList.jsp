<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<form name="frm" method="post">
<section class="notice">
  <div class="page-titlee">
        <div class="containerr">
            <h3 style=" margin-right:70px; font-size: 60px;color: #333333;font-weight: 400;text-align: center;"> 배너 리스트 </h3>  
        </div>
    </div>    
   	 <input type="button" value="새배너 등록" onClick="location.href='/newBannerWrite'"/>
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
                    <th scope="col" class="th-answer">수정</th>
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
							<td><select name="order_seq" id="${bannerVO.bseq}"
								onChange="change_order('${bannerVO.bseq}');">
									<c:forEach var="cnt" begin="1" end="5" varStatus="status">
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
										<c:when test="${bannerVO.order_seq==8}">
											<option value="8" selected>사용안함</option>
										</c:when>
										<c:otherwise>
											<option value="8">사용안함</option>
										</c:otherwise>
									</c:choose>
							</select></td>
							<td>${bannerVO.useyn}</td>
							<td width="150"><fmt:formatDate value="${bannerVO.indate}" /></td>
							<td><input type="button" value="수정"
								onClick="location.href='editBannerForm?bseq=${bannerVO.bseq}'"></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		</div>
	</div>
</section>
</form>




<%@ include file="../../admin/footer.jsp"%>