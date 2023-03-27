$(document).ready(function(){
	$(".login-wrap table tr .focusB").focus(function(){
		$(this).css("border","1px solid #000000");
	});
	$(".login-wrap table tr .focusB").blur(function(){
		$(this).css("border", "1px solid #b8b8b8");
	});
});