<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>modify</title>
	<link href="${conPath }/css/font.css" rel="stylesheet" type="text/css">
	<link href="${conPath }/css/join.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<!-- <script src="${conPath }/js/modify.js"></script> -->
	<script>
	$(document).ready(function(){
		$(".join-form table tr .focusB").focus(function(){
		$(this).css("border","1px solid #000000");
	});
	$(".join-form table tr .focusB").blur(function(){
		$(this).css("border", "1px solid #b8b8b8");
	});
	const patternPw = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~`!@#$%^&*()-_+=|\\\[\]{}'";:?,.\/]).{8,16}$/; //^[a-zA-Z0-9~`!@#$%^&*()-_+=|\\\[\]{}'";:?,.\/]{8,16}$
	const patterTel = /^\d{2,3}[\-) ]?\d{3,4}[\-]?\d{4}$/;
	const patternYear = /^(19[2-9][0-9]|20[0-2][0-9]{1})$/; // 연도 정규표현식
	const patternMonth = /^([1-9]{1}|0[0-9]|1[0-2])$/; // 월 정규표현식
	const patternDay = /^([1-9]{1}|[0-2][0-9]|3[0-1])$/; // 일 정규표현식
	const patternMail = /^\w+@\w+(\.\w+){1,2}$/; // 이메일 정규표현식
	var today = new Date();
	var yearNow = today.getFullYear(); // 올해 년도
	$("#oldmpw").keyup(function(){
		var oldmpw = $(this).val();
		if(!oldmpw){
			$(".oldmpwResult").html("<p>필수 정보입니다.</p>");
		}/*else if(!oldmpw.match(patternPw)){
			$(".oldmpwResult").html("<p>8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.</p>");
		}*/else{
			$(".oldmpwResult").html("");
		};
	}); //mpwChk-keyup 이벤트
	$("#mpw").keyup(function(){
		var mpw = $(this).val();
		if(mpw.length > 1 && !mpw.match(patternPw)){
			$(".mpwResult").html("<p>8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.</p>");
		}else{
			$(".mpwResult").html("");
		};
	}); //mpw-keyup 이벤트
	$("#mtel").keyup(function(){
		var mtel = $(this).val();
		if(!mtel){
			$(".mtelResult").html("<p>필수 정보입니다.</p>");
		}else if(!mtel.match(patterTel)){
			$(".mtelResult").html("<p>형식에 맞지 않는 번호입니다.</p>");
		}else{
			$(".mtelResult").html("");
		}
	}); //mtel-keyup 이벤트
	$(".mbirth").keyup(function(){
		var mbirthYear = $(".mbirthYear").val();
		var mbirthMonth = $(".mbirthMonth").val();
		var mbirthDay = $(".mbirthDay").val();
		if(mbirthYear.length >= 1 && mbirthYear > yearNow){
			$(".mbirthResult").html("<p>생년월일이 미래로 입력되었습니다.</p>");
		}else if(mbirthYear.length >= 1 && !mbirthYear.match(patternYear)){
			$(".mbirthResult").html("<p>생년월일 4자리를 정확하게 입력해주세요.</p>");
		}else if(mbirthMonth.length >= 1 && !mbirthMonth.match(patternMonth)){
			$(".mbirthResult").html("<p>태어난 월을 정확하게 입력해주세요.</p>");
		}else if(mbirthDay.length >= 1 && !mbirthDay.match(patternDay) || mbirthDay == "00"){
			$(".mbirthResult").html("<p>태어난 일을 정확하게 입력해주세요.</p>");
		}else{
			$(".mbirthResult").html("");
		}
	});// mibrthDa-keyup 이벤트
	$("#memail").keyup(function(){
		var memail = $(this).val();
		if(!memail){
			$(".memailResult").html("<p>필수 정보입니다.</p>");
		}else if(!memail.match(patternMail)){
			$(".memailResult").html("<p>이메일 형식으로 입력해 주세요. </p>");
		}else{
			$(".memailResult").html("");
		}
	}); // memail-keyup 이벤트
	$("#maddress").keyup(function(){
		var maddress = $(this).val();
		if(!maddress || maddress.length < 11){
			$(".maddressResult").html("<p>필수 정보입니다.</p>");
		}else{
			$(".maddressResult").html("");
		}
	}); // maddress-keyup 이벤트
	$("form").submit(function(){
		var oldmpw = $("#oldmpw").val();
		var mtelResult = $(".mtelResult").text().trim();
		var memailResult = $(".memailResult").text().trim();
		var maddressResult = $(".maddressResult").text().trim();
		if(oldmpw != "${member.mpw}"){
			alert("현 비밀번호를 확인하세요.");
			$("#oldmpw").focus();
			return false;
		}else if(mtelResult != ""){
			alert("전화번호를 확인하세요.");
			$("#mtel").focsu();
			return false;
		}else if(memailResult!=""){
			alert("이메일을 확인하세요.");
			$("#memail").focus();
			return false;
		}else if(maddressResult != ""){
			alert("주소를 확인하세요.");
			$("#maddress").focus();
			return false;
		}
	}); // submit 제한
});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content-form">
		<div class="join-title">정보수정</div>
		<div class="join-wrap">
			<div class="join-form-line">
				<span class="point">*</span>필수입력사항
			</div>
			<div class="join-form">
				<form action="modify.do" method="post">
				<input type="hidden" name="dbMpw" value="${member.mpw}">
					<table>
						<tr>
							<th><label for="mid">아이디<span class="point">*</span></label></th>
							<td>
								<input type="text" name="mid" id="mid" class="mid focusB" value="${member.mid }" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th><label for="oldmpw">현 비밀번호<span class="point">*</span></label></th>
							<td>
								<input type="password" name="oldmpw" id="oldmpw" class="oldmpw focusB">
								<div class="oldmpwResult bottom"></div>
							</td>
						</tr>
						<tr>
							<th><label for="mpw">새 비밀번호</label></th>
							<td>
								<input type="password" name="mpw" id="mpw" class="mpw focusB">
								<div class="mpwResult bottom"></div>
							</td>
						</tr>
						<tr>
							<th><label for="mname">이름<span class="point">*</span></label></th>
							<td>
								<input type="text" name="mname" id="mname" class="mname focusB" value="${member.mname }" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th><label for="mtel">전화번호<span class="point">*</span></label></th>
							<td>
								<input type="text" name="mtel" id="mtel" class="mtel focusB" value="${member.mtel }">
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
								<input type="text" name="memail" id="memail" class="memail focusB" value="${member.memail}">
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
								<input type="text" name="maddress" id="maddress" class="maddress focusB" value="${member.maddress }">
								<div class="maddressResult bottom"></div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<button type="submit" class="btnStyle">수정하기</button>
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