$(document).ready(function(){
	$(".join-form table tr .focusB").focus(function(){
		$(this).css("border","1px solid #000000");
	});
	$(".join-form table tr .focusB").blur(function(){
		$(this).css("border", "1px solid #b8b8b8");
	});
	const patternId = /^[a-z]{1}[a-z0-9_-]{2,15}$/;
	const patternPw = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~`!@#$%^&*()-_+=|\\\[\]{}'";:?,.\/]).{8,16}$/; //^[a-zA-Z0-9~`!@#$%^&*()-_+=|\\\[\]{}'";:?,.\/]{8,16}$
	const patternName = /^[a-zA-Z가-힣]{1,}$/;
	const patternSpc = /[~`!@#$%^&*()-_+=|\\\[\]{}'";:?,.\/]/;
	const patterTel = /^\d{2,3}[\-) ]?\d{3,4}[\-]?\d{4}$/;
	const patternYear = /^(19[2-9][0-9]|20[0-2][0-9]{1})$/; // 연도 정규표현식
	const patternMonth = /^([1-9]{1}|0[0-9]|1[0-2])$/; // 월 정규표현식
	const patternDay = /^([1-9]{1}|[0-2][0-9]|3[0-1])$/; // 일 정규표현식
	const patternMail = /^\w+@\w+(\.\w+){1,2}$/; // 이메일 정규표현식
	var today = new Date();
	var yearNow = today.getFullYear(); // 올해 년도
	var mbirthYear, mbirthMonth, mbirthDay;
	if(mbirthMonth<10){
		mbirthMonth = "0" + mbirthMonth;
	}else if(mbirthDay <10){
		mbrithDay = "0" + mbirthDay;
	}
	$(".mbirth").append(mbirthYear + mbirthMonth + mbirthDay);
	$("#mid").keyup(function(){
		var mid = $(this).val();
		if(!mid){
			$(".midResult").html("<p>필수 정보입니다.</p>");
		}else if(!mid.match(patternId)){
			$(".midResult").html("<p>2~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.</p>");
		}else{
			$.ajax({
				url : 'midConfirm.do',
				type :  'get',
				data : 'mid='+mid,
				dataType : 'html',
				success : function(data){
					$(".midResult").html(data);
				},
			});
		};
	}); // mid-keyup이벤트
	var mpw, mpwChk, mtel, mname, memail, maddress;
	$("#mpw").keyup(function(){
		mpw = $(this).val();
		if(!mpw){
			$(".mpwResult").html("<p>필수 정보입니다.</p>");
		}else if(mpw.length < 8 || !mpw.match(patternPw)){
			$(".mpwResult").html("<p>8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.</p>");
		}else{
			$(".mpwResult").html("");
		};
	}); //mpw-keyup 이벤트
	$("#mpwChk").keyup(function(){
		mpwChk = $(this).val();
		if(!mpwChk){
			$(".mpwChkResult").html("<p>필수 정보입니다.</p>");
		}else if(mpwChk.length <8 || !mpwChk.match(patternPw)){
			$(".mpwChkResult").html("<p>8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.</p>");
		}else{
			if(mpw==mpwChk){
				$(".mpwChkResult").html("");
			}else{
				$(".mpwChkResult").html("<p>비밀번호가 일치하지 않습니다.</p>");
			};
		};
	}); //mpwChk-keyup 이벤트
	$("#mname").keyup(function(){
		mname = $(this).val();
		if(!mname){
			$(".mnameResult").html("<p>필수 정보입니다.</p>");
		}else if(mname.length < 0 || !mname.match(patternName)){
			$(".mnameResult").html("<p>한글과 영문 대 소문자를 사용하세요.(특수기호, 공백 사용 불가)</p>");
			if(mname.match(patternSpc)){
				mname = mname.replace(patternSpc, '');				
				$(this).val(mname);
			}
		}else{
			$(".mnameResult").html("");
		}
	}); //mname-keyup 이벤트
	$("#mtel").keyup(function(){
		mtel = $(this).val();
		if(!mtel){
			$(".mtelResult").html("<p>필수 정보입니다.</p>");
		}else if(!mtel.match(patterTel)){
			$(".mtelResult").html("<p>형식에 맞지 않는 번호입니다.</p>");
		}else{
			$(".mtelResult").html("");
		}
	}); //mtel-keyup 이벤트
	/*
	$(".mbirthYear").keyup(function(){
		var mbirthYear = $(this).val();
		if(mbirthYear.length >= 1 && mbirthYear > yearNow){
			$(".mbirthResult").html("<p>생년월일이 미래로 입력되었습니다.</p>");
		}else if(mbirthYear.length >= 1 && !mbirthYear.match(patternYear)){
			$(".mbirthResult").html("<p>생년월일 4자리를 정확하게 입력해주세요.</p>");
		}else{
			$(".mbirthResult").html("");
		}
	}); //mbirthYear-keyup 이벤트
	/*
	$(".mbirthMonth").keyup(function(){
		var mbirthMonth = $(this).val();
		if(!mbirthMonth.match(patternMonth)){
			$(".mbirthResult").html("<p>태어난 월을 정확하게 입력해주세요.</p>");
		}else{
			$(".mbirthResult").html("");
		};
	}); // mbirthMonth-keyup 이벤트
	$(".mbirthDay").keyup(function(){
		var mbirthDay = $(this).val();
		if(mbirthDay.length >= 1 && !mbirthDay.match(patternDay) || mbirthDay == "00"){
			$(".mbirthResult").html("<p>태어난 일을 정확하게 입력해주세요.</p>");
		}else{
			$(".mbirthResult").html("");
		};
	}); // mibrthDa-keyup 이벤트
	 */
	$(".mbirth").keyup(function(){
		mbirthYear = $(".mbirthYear").val();
		mbirthMonth = $(".mbirthMonth").val();
		mbirthDay = $(".mbirthDay").val();
		if(mbirthYear.length >= 1 && mbirthYear > yearNow){
			$(".mbirthResult").html("<p>생년월일이 미래로 입력되었습니다.</p>");
		}else if(mbirthYear.length >= 1 && !mbirthYear.match(patternYear)){
			$(".mbirthResult").html("<p>생년월일 4자리를 정확하게 입력해주세요.</p>");
		}else if(mbirthMonth.length >= 1 && !mbirthMonth.match(patternMonth) || mbirthMonth == "00"){
			$(".mbirthResult").html("<p>태어난 월을 정확하게 입력해주세요.</p>");
		}else if(mbirthDay.length >= 1 && !mbirthDay.match(patternDay) || mbirthDay == "00"){
			$(".mbirthResult").html("<p>태어난 일을 정확하게 입력해주세요.</p>");
		}else{
			$(".mbirthResult").html("");
		}
	});// mibrthDa-keyup 이벤트
	$("#memail").keyup(function(){
		memail = $(this).val();
		if(!memail){
			$(".memailResult").html("<p>필수 정보입니다.</p>");
		}else if(!memail.match(patternMail)){
			$(".memailResult").html("<p>이메일 형식으로 입력해 주세요. </p>");
		}else{
			$(".memailResult").html("");
		}
	}); // memail-keyup 이벤트
	$("#maddress").keyup(function(){
		maddress = $(this).val();
		if(!maddress){
			$(".maddressResult").html("<p>필수 정보입니다.</p>");
		}else{
			$(".maddressResult").html("");
		}
	}); // maddress-keyup 이벤트
	$("form").submit(function(){
		
		var midResult = $(".midResult").text().trim();
		/*
		
		var mnameResult = $(".mnameResult").text().trim();
		var mtelResult = $(".mtelResult").text().trim();
		var memailResult = $(".memailResult").text().trim();
		var maddressResult = $(".maddressResult").text().trim();*/
		
		if(midResult != "사용가능한 아이디입니다."){
			alert("사용가능한 아이디인지 확인 요망");
			$("#mid").focus();
			return false;
		}else if(!mpw){
			alert("비밀번호를 확인하세요.");
			$("#mpw").focus();
			return false;
		}else if(!mpwChk){
			alert("비밀번호가 맞지 않습니다.");
			return false;
		}else if(!mname){
			alert("이름을 확인하세요.")
			$("#mname").focus();
			return false;
		}else if(!mtel){
			alert("전화번호를 확인하세요.");
			$("#mtel").focus();
			return false;
		}else if(!memail){
			alert("이메일을 확인하세요.");
			$("#memail").focus();
			return false;
		}else if(!maddress){
			alert("주소를 확인하세요.");
			$("#maddress").focus();
			return false;
		}
	}); // submit 제한
});