<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지사항</title>
	<link href="${conPath }/css/notice.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script>
		$(document).ready(function(){
			$("tr").click(function(){
				var nid = Number($(this).children().eq(0).text());
				if(!isNaN(nid)){
					location.href="${conPath}/noticeContent.do?nid="+nid+"&pageNum=${pageNum}";
				}
			})
		});
	</script>
</head>
<body>
	<c:if test="${not empty nModifyResult }">
		<script>
			alert("${nModifyResult}");
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="notice-wrap">
		<div class="notice-top">
		<div class="notice-title">공지사항</div>
			<div class="noticeCnt">총 <b>${noticeTotCnt }</b>건</div>
		</div>
		<div class="notice-content">
			<table>
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>작성일</td>
				</tr>
				<c:if test="${noticeTotCnt eq 0}">
					<td colspan="4">등록된 글이 없습니다.</td>
				</c:if>
				<c:if test="${noticeTotCnt != 0}">
					<c:forEach items="${NoticeBoard}" var="notice">
						<tr>
							<td>${notice.nid }</td>
							<td class="left">${notice.ntitle }</td>
							<td>${notice.aname }</td>
							<td>
								<fmt:formatDate value="${notice.nrdate }" type="date" pattern="yyyy.MM.dd"/>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<div class="paging">
				<a href="${conPath }/noticeList.do?pageNum=1" class="first">1</a>
				<c:if test="${BLOCKSIZE < startPage}">
					<a href="${conPath }/noticeList.do?pageNum=${startPage-1}" class="prev">1</a>
				</c:if>
				<c:if test="${BLOCKSIZE >= startPage }">
					<a class="prev">1</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum}">
						<a class="now">${i }</a>
					</c:if>
					<c:if test="${i != pageNum }">
						<a href="${conPath }/noticeList.do?pageNum=${i}" class="move">${i }2</a>
					</c:if>
				</c:forEach>
				<c:if test="${endPage < nTotPageCnt }">
					<a href="${conPath }/noticeList.do?pageNum=${endPage+1}" class="next">2</a>
				</c:if>
				<c:if test="${nTotPageCnt eq endPage}">
					<a class="next">2</a>
				</c:if>
					<a href="${conPath }/noticeList.do?pageNum=${nTotPageCnt}" class="last">2</a>
			</div>
		</div>
		<div class="write-btn">
			<c:if test="${not empty admin }">
				<button onclick="location.href='${conPath}/noticeWriteView.do'" class="btn-style">글쓰기</button>
			</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>