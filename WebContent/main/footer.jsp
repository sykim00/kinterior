<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>footer</title>
	<link href="${conPath }/css/font.css" rel="stylesheet" type="text/css">
	<link href="${conPath }/css/footer.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="footer">
		<div class="footer-in">
			<p class="footer-left"><span>K I N T E R I O R ⓒ 2023</span></p>
			<div class="footer-content">
				<p>
					<span>TEL : 010-6893-6029</span> 
					<span>FAX : 02-2222-2222</span> 
					<span>tiffany456@naver.com</span>
				</p>
				<p>
					<span>서울특별시 서대문구 신촌로 220-2 신촌빌딩 3층</span>
					<span class="admin"><b><a href="${conPath }/adminLoginView.do">관리자모드</a></b></span>
					<span>사업자 등록번호 998-11-32156</span>
				</p>
				<p class="footer-right">개인정보처리방침</p>
			</div>
		</div>	
	</div>
</body>
</html>