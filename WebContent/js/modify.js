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