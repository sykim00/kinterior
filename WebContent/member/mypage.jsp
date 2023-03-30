<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath }/css/mypage.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
</head>
<body>	
	<c:if test="${empty member }">
		<script>
			location.href="${conPath}/loginView.do?next=mypageView.do";
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content-form">
		<div class="mypage-title">MY PAGE</div>
		<div class="mypage-wrap">
		<div class="muypage-line"></div>
			<div class="mypage-content">
				<div class="user">
					<div class="top">
						<div class="mname">
							<strong>${member.mid } / ${member.mname }</strong> 님
						</div>
						<div class="memail">
							${member.memail }
						</div>
					</div>
					<div class="bottom">
						<button onclick="location.href='${conPath}/modifyView.do'" class="btnStyle">회원 정보 수정 / 회원 탈퇴</button>
					</div>
				</div> <!-- user -->
				<div class="content">
					<div class="content-top">
						<strong>작성글</strong>
					</div>
					<div class="content-bottom">
						<div class="consult-btn">
							<a href="#">견적문의 내역 보러가기</a>		
						</div>
						<div class="review-btn">
							<a href="#">후기작성글 내역 보러가기</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>