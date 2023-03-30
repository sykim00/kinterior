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
								<input type="text" name="mid" id="mid" class="mid focusB" placeholder="아이디를 입력해주세요.">
								<div class="midResult bottom"></div>
							</td>
						</tr>
						<tr>
							<th><label for="mpw">비밀번호<span class="point">*</span></label></th>
							<td>
								<input type="password" name="mpw" id="mpw" class="mpw focusB" placeholder="비밀번호를 입력해주세요.">
								<div class="mpwResult bottom"></div>
							</td>
						</tr>
						<tr>
							<th><label for="mpwChk">비밀번호확인<span class="point">*</span></label></th>
							<td>
								<input type="password" name="mpwChk" id="mpwChk" class="mpwChk focusB" placeholder="비밀번호를 다시 한번 입력해주세요.">
								<div class="mpwChkResult bottom"></div>
							</td>
						</tr>
						<tr>
							<th><label for="mname">이름<span class="point">*</span></label></th>
							<td>
								<input type="text" name="mname" id="mname" class="mname focusB" placeholder="이름을 입력해주세요.">
								<div class="mnameResult bottom"></div>
							</td>
						</tr>
						<tr>
							<th><label for="mtel">전화번호<span class="point">*</span></label></th>
							<td>
								<input type="text" name="mtel" id="mtel" class="mtel focusB" placeholder="예 : 02-000-0000 또는 010-0000-0000">
								<div class="mtelResult bottom"></div>
							</td>
						</tr>
						<tr>
							<th><label>생년월일</label></th>
							<td class="mbirth-line">
								<div>
									<input type="text" name="mbirthYear" class="mbirthYear mbirth" placeholder="YYYY" maxlength='4'>
									<span class="mbirth-slash"></span>
									<input type="text" name="mbirthMonth" class="mbirthMonth mbirth" placeholder="MM" maxlength='2'>
									<span class="mbirth-slash"></span>
									<input type="text" name="mbirthDay" class="mbirthDay mbirth" placeholder="DD" maxlength='2'>
								</div>
								<div class="mbirthResult bottom" style="clear:both;padding:0;"></div>
							</td>
						</tr>
						<tr>
							<th><label for="memail">이메일<span class="point">*</span></label></th>
							<td>
								<input type="text" name="memail" id="memail" class="memail focusB" placeholder="예 : kinterior@interior.com">
								<div class="memailResult bottom"></div>
							</td>
						</tr>
						<tr>
							<th>성별</th>
							<td class="mgender-line">
								<input type="radio" name="mgender" value="m" id="mgender-m" class="radio_btn"><label for="mgender-m">남자</label>		
								<input type="radio" name="mgender" value="f" id="mgender-f" class="radio_btn"><label for="mgender-f">여자</label>
								<input type="radio" name="mgender" value="n" id="mgender-n" class="radio_btn" checked="checked"><label for="mgender-n">선택안함</label>
							</td>
						</tr>
						<tr>
							<th><label for="maddress">주소<span class="point">*</span></label></th>
							<td>
								<input type="text" name="maddress" id="maddress" class="maddress focusB" placeholder="주소를 입력해주세요.">
								<div class="maddressResult bottom"></div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<button type="submit" class="btnStyle">가입하기</button>
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