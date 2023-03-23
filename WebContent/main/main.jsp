<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="shortcut icon" href="${conPath}/img/favicon.ico" type="image/x-icon">
	<link rel="icon" href="${conPath}/img/favicon.ico" type="image/x-icon">
	<link href="${conPath }/css/font.css" rel="stylesheet" type="text/css">
	<link href="${conPath }/css/main.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="${conPath }/css/flexslider.css" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script src="${conPath }/js/jquery.flexslider.js"></script>
	<script>
		$(document).ready(function(){
			  $('.flexslider').flexslider({
				    animation: "slide",
				    controlsContainer: $(".custom-controls-container"),
				    customDirectionNav: $(".custom-navigation a")
				  });
			});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="main">
		<div class="mainWrap">
			<div class="flexslider">
			  <ul class="slides">
				<li>
					<img src="${conPath }/img/main1.jpg" />
				</li>
				<li>
				      <img src="${conPath }/img/main2.jpg" />
				</li>
				<li>
				      <img src="${conPath }/img/main3.png" />
				</li>
				<li>
				      <img src="${conPath }/img/main4.png" />
				</li>
				<li>
				      <img src="${conPath }/img/main5.jpg" />
				</li>
			</ul>
			</div>
			<div class="custom-navigation">
				<a href="#" class="flex-prev">prev</a>
				  	<div class="custom-controls-container"></div>
				<a href="#" class="flex-next">next</a>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>