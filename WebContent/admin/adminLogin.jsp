<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>login</title>
	<link href="${conPath }/css/login.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script src="${conPath }/js/login.js"></script>
</head>
<body>
	<c:if test="${not empty admin }">
		<script>
			alert("이미 관리자모드로 로그인 했습니다.");
			location.href="${conPath}/main.do";
		</script>
	</c:if>
	<c:if test="${not empty member }">
		<script>
			alert("관리자만 이용할 수 있습니다.");
			history.back();
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content-form">
		<div class="login-title">관리자 로그인</div>
		<div class="login-wrap">
			<form action="adminLogin.do" method="post">
				<table>
					<tr>
						<td><input type="text" name="aid" class="aid focusB" placeholder="아이디를 입력해주세요."></td>
					</tr>
					<tr>
						<td>
							<input type="password" name="apw" class="apw focusB" placeholder="비밀번호를 입력해주세요.">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit" class="btnStyle">로그인</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>