<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath }/css/interiorContent.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script>
		$(document).ready(function(){
			$(".btn2").mouseover(function(){
				$(this).css("background","#e9e9e9").css("color","#000").css("border","1px solid #dbdbdb");
			});
			$(".btn2").mouseleave(function(){
				$(this).css("color","#000").css("border","1px solid #dbdbdb").css("background","#fff");
			});
		});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
		<div>
			<div id="interior-form">
				<div class="interior-wrap">
					<table>
						<tr>
							<td class="pfilenameView">
								<c:if test="${not empty interior.pfilename }">
									<img src="${conPath }/interiorFileUp/${interior.pfilename}" alt="메인이미지">
								</c:if>
							</td>
						</tr> 
						<tr>
							<td class="title">
								<span>${interior.ptitle }</span>
							</td>
						</tr>
						<tr>
							<td class="name"> 
								<fmt:formatDate value="${interior.prdate }" type="date" pattern="yyyy.MM.dd"/>
							</td>
						</tr>
						<tr>
							<td class="content">
								${interior.pcontent }
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center;">
								<c:if test="${admin.aid eq interior.aid}">
									<button type="button" class="btnStyle" onclick="location.href='${conPath}/interiorModifyView.do?pid=${interior.pid }&pageNum=${param.pageNum }'">수정</button>
									<button type="button" class="btnStyle btn2" onclick="location.href='${conPath}/interiorDelete.do?pid=${interior.pid }&pageNum=${param.pageNum }'">삭제</button>
								</c:if>
								<button type="button" class="btnStyle btn2" onclick="location.href='${conPath}/interiorList.do?pageNum=${param.pageNum }'">목록</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>