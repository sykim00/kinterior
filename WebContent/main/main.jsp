<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>mainPage</title>
	<link href="${conPath }/css/font.css" rel="stylesheet" type="text/css">
	<link href="${conPath }/css/main.css" rel="stylesheet" type="text/css">
	<link href="${conPath }/css/flexslider.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script src="${conPath }/js/main.js"></script>
	<script src="${conPath }/js/jquery.flexslider.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="main">
		<div class="mainWrap">
			<div class="main-slider">
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
					<a href="#" class="flex-prev">&lt;</a>
					  	<div class="custom-controls-container"></div>
					<a href="#" class="flex-next">&gt;</a>
				</div>
			</div>
			<div class="interior">
				<span class="left-line"></span>
				<div class="interior-view">
					<div class="interior-view1 view_img">
						<a href="#"><img src="${conPath }/img/1.jpg"></a>
					</div>
					<div class="interior-view2 view_img">
						<a href="#"><img src="${conPath }/img/2.jpg"></a>
						<span class="top-line"></span>
					</div>
					<div class="interior-view3 view_img">
						<a href="#"><img src="${conPath }/img/3.jpg"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>