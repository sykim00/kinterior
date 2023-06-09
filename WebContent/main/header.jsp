<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>header</title>
	<link href="${conPath }/css/font.css" rel="stylesheet" type="text/css">
	<link href="${conPath }/css/header.css" rel="stylesheet" type="text/css">
	<script src="${conPath }/js/header.js"></script>
</head>
<body>
	<div id="header">
		<div class="headerin">
			<h1><a href="${conPath }/main.do">로고</a></h1>
			<div class="gnb">
				<ul>
					<li><a href="${conPath }/interiorList.do">시공사례</a></li>
					<li><a href="${conPath }/rboardList.do">고객후기</a></li>
					<li><a href="${conPath }/noticeList.do">공지사항</a></li>
					<li><a href="${conPath }/consultList.do">견적문의</a></li>
				</ul>
			</div>
			<c:if test="${empty member and empty admin }">
				<div class="side">
					<a href="${conPath }/joinView.do" class="btn_join">회원가입</a>
					<a href="${conPath }/loginView.do" class="btn_login">로그인</a>
				</div>
			</c:if>
			<c:if test="${not empty member and empty admin }">
				<div class="side">
					<a href="${conPath }/mypageView.do" class="btn_mypage">마이페이지</a>
					<a href="${conPath }/logout.do" class="btn_logout">로그아웃</a>
				</div>
			</c:if>
			<c:if test="${not empty admin and empty member }">
				<div class="side">
					<a href="${conPath }/allView.do" class="btn_members">회원관리</a>
					<a href="${conPath }/logout.do" class="btn_adminLogout">관리자나가기</a>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>