<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>견적문의 게시판</title>
	<link href="${conPath }/css/consultantList.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="consultant-wrap">
		<div>견적문의</div>
		<div class="consultant-content">
			<table>
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>작성일</td>
				</tr>
				<c:if test="${conTotCnt eq 0 }">
					<tr><td colspan="4">등록된 글이 없습니다.</td></tr>
				</c:if>
				<c:if test="${conTotCnt != 0 }">
					<c:forEach items="${consulantList }" var="conBoard">
						<tr>
							<td>${conBoard.cid}</td>
							<td>
								<img src="${conPath }/img/lock_icon.png">
								<c:forEach var="i" begin="1" end="${conBoard.cindent}">
									<c:if test="${i eq conBoard.findent}">
										L<img src="${conPath }/img/re_img.jpg" alt="답변글 이미지">
									</c:if>
									<c:if test="${i != conBoard.findent}">
										&nbsp; &nbsp;
									</c:if>
								</c:forEach>
								${conBoard.ctitle}
								<c:if test="${not empty conBoard.cfilename}">
									<img src="${conPath }/img/file.png" alt="파일첨부이미지">
								</c:if>
							</td>
							<td>${conBoard.mname}</td>
							<td>
								<fmt:formatDate value="${conBoard.crdate }" type="data" dateStyle="RR.MM.dd" />
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<div class="paging">
				<a href="${conPath }/consultList.do?pageNum=1">&lt;&lt;</a>
				<c:if test="${startPage > BLOCKSIZE }">
					<a href="${conPath }/consultList.do?pageNum=${startPage-1}">&lt;</a>
				</c:if>
				<c:if test="${BLOCKSIZE >= startPage }">
					&lt;
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum }">
						<b> ${i} </b>
					</c:if>
					<c:if test="${i != pageNum }">
						<a href="${conPath }/consultList.do?pageNum=${i}"> ${i } </a>
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageTotCnt }">
					<a href="${conPath }/consultList.do?pageNum=${endPage+1}">&gt;</a>
				</c:if>
				<c:if test="${endPage eq pageTotCnt}">
					<a href="${conPath }/consultList.do?pageNum=${pageTotCnt}">&gt; &gt;</a>
				</c:if>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>