<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>join-form</title>
	<link href="${conPath }/css/font.css" rel="stylesheet" type="text/css">
	<link href="${conPath }/css/join.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script src="${conPath }/js/join.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content-form">
		<div class="join-title">회원가입</div>
		<div class="join-wrap">
			<div class="join-form-line">
				<span class="point">*</span>필수입력사항
			</div>
			<div class="join-form">
				<form action="join.do" method="post">
					<table>
						<tr>
							<th><label for="mid">아이디<span class="point">*</span></label></th>
							<td>
								<input type="text" name="mid" id="mid" class="mid">
								<div class="midResult"></div>
							</td>
						</tr>
						<tr>
							<th><label for="mpw">비밀번호<span class="point">*</span></label></th>
							<td>
								<input type="password" name="mpw" id="mpw" class="mpw">
								<div class="mpwResult"></div>
							</td>
						</tr>
						<tr>
							<th><label for="mpwChk">비밀번호확인<span class="point">*</span></label></th>
							<td>
								<input type="password" name="mpwChk" id="mpwChk" class="mpwChk">
								<div class="mpwChkResult"></div>
							</td>
						</tr>
						<tr>
							<th><label for="mname">이름<span class="point">*</span></label></th>
							<td>
								<input type="text" name="mname" id="mname" class="mname">
								<div class="mnameResult"></div>
							</td>
						</tr>
						<tr>
							<th><label for="mtel">전화번호<span class="point">*</span></label></th>
							<td>
								<input type="text" name="mtel" id="mtel" class="mtel">
								<div class="mtelResult"></div>
							</td>
						</tr>
						<tr>
							<th><label>생년월일</label></th>
							<td>
								<input type="text" name="mbirthYear" class="mbirthYear">/
								<input type="text" name="mbirthMonth" class="mbirthMonth">/
								<input type="text" name="mbirthDay" class="mbirthDay">
							</td>
						</tr>
						<tr>
							<th><label for="memail">이메일<span class="point">*</span></label></th>
							<td>
								<input type="text" name="memail" id="memail" class="memail">
								<div class="memailResult"></div>
							</td>
						</tr>
						<tr>
							<th>성별</th>
							<td>
								<input type="radio" name="mgender" value="m" class="radio_btn" checked="checked">남자
								<input type="radio" name="mgender" value="f" class="radio_btn">여자
								<input type="radio" name="mgender" value="n" class="radio_btn">선택안함
							</td>
						</tr>
						<tr>
							<th><label for="maddress">주소<span class="point">*</span></label></th>
							<td><input type="text" name="maddress" id="maddress" class="maddress"></td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="submit" value="가입하기">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>