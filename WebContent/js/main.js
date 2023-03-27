const overImg = ["img/4.jpg","img/5.jpg","img/6.jpg"];
const outImg = ["img/1.jpg", "img/2.jpg", "img/3.jpg"];
$(document).ready(function(){
	$('.flexslider').flexslider({
		animation: "slide",
		controlsContainer: $(".custom-controls-container"),
		customDirectionNav: $(".custom-navigation a")
	});
	$(".interior img").each(function(idx, item){
		$(this).mouseover(function(){
		$(this).attr("src", overImg[idx]);
		 });
		$(this).mouseout(function(){
		$(this).attr("src", outImg[idx]); 
		});
	});	
});