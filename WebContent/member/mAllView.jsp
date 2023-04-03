<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원리스트</title>
	<link href="${conPath }/css/font.css" rel="stylesheet" type="text/css">
	<link href="${conPath }/css/mallview.css" rel="stylesheet" type="text/css">
	<style>
		
	</style>
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script>
		$(document).ready(function(){
			$("tr").click(function(){
				var cid = Number($(this).children().eq(0).text());
				var cgroup = Number($(this).children().eq(1).text());
				if(!isNaN(cid)){
					location.href="${conPath}/consultContent.do?cid="+cid+"&pageNum=${pageNum}";
				}
			});
		});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content-form">
		<div class="memberList">회원리스트</div>
		<div class="memberList-page">
			<table>
				<tr>
					<td>회원 아이디</td>
					<td>회원 이름</td>
					<td>회원 이메일</td>
					<td>가입일</td>
				</tr>
				<c:if test="${memberTotcnt eq 0 }">
					<tr><td colspan="4">등록된 회원이 없습니다.</td></tr>
				</c:if>
				<c:if test="${memberTotcnt != 0 }">
					<c:forEach items="${members }" var="members">
						<tr>
							<td>${members.mid}</td>
							<td>${members.mname }</td>
							<td>${members.memail }</td>
							<td>
								<fmt:formatDate value="${members.mrdate}" type="date" pattern="yyyy.MM.dd" />
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<div class="paging">
				<a href="${conPath }/allView.do?pageNum=1">&lt;&lt;</a>
				<c:if test="${BLOCKSIZE < startPage }">
					<a href="${conPath }/allView.do?pageNum=${startPage-1}">&lt;</a>
				</c:if>
				<c:if test="${BLOCKSIZE >= startPage }">
					<a>&lt;</a>
				</c:if>
				&nbsp; &nbsp;
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum }">
						<b>${i }</b>
					</c:if>
					<c:if test="${i != pageNum }">
						<a href="${conPath }/allView.do?pageNum=${i}">${i }</a>
					</c:if>
				</c:forEach>
				&nbsp; &nbsp;
				<c:if test="${totPageCnt > endPage }">
					<a href="${conPath }/allView.do?pageNum=${endPage + 1}">&gt;</a>
				</c:if>
				<c:if test="${totPageCnt eq endPage }">
					<a>&gt;</a>
				</c:if>
				<a href="${conPath }/allView.do?pageNum=${totPageCnt}">&gt;&gt;</a>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>