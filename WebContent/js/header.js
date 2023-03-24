$(window).scroll(function(){
	var scroll = $(this).scrollTop();
	if(scroll > 1){
		$("#header").css("border-bottom-width","1px").css("border-bottom-style","solid").css("border-bottom-color","#ccc");
	}else{
		$("#header").css("border-bottom","none");
	}
});
