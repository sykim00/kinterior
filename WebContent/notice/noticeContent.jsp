<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지사항 글 상세 보기</title>
	<link href="${conPath }/css/noticeContent.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="notice-form">
		<div class="notice-title">공지사항</div>
		<div class="notice-wrap">
			<table>
				<tr>
					<td class="ntitle">
						${noticeContent.ntitle }
						<p class="notice-date"><fmt:formatDate value="${noticeContent.nrdate }" type="date" pattern="yyyy-MM-dd hh:mm"/></p>
					</td>
				</tr>
				<tr>
					<td class="ncontent">
						<pre>${noticeContent.ncontent }</pre>
					</td>
				</tr>
				<tr>
					<td class="btn-left">
						<c:if test="${not empty admin }">
							<button class="btn2" onclick="location.href='${conPath}/noticeModifyView.do?nid=${noticeContent.nid}&pageNum=${param.pageNum }'">수정</button>
							<button class="btn2" onclick="location.href='${conPath}/noticeDelete.do?nid=${noticeContent.nid }&pageNum=${param.pageNum }'">삭제</button>
						</c:if>
						<button class="btn2" onclick="location.href='${conPath}/noticeList.do?pageNum=${param.pageNum}'">목록</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>