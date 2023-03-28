<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>견적문의 게시판 글쓰기</title>
	<link href="${conPath }/css/font.css" rel="stylesheet" type="text/css">
	<link href="${conPath }/css/consultantWrite.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script>
		$(document).ready(function(){
			$("#ctitle").keyup(function(){
				var ctitle = $(this).val();
				if(!ctitle){
					alert("견적문의제목을 꼭 입력해주세요.");
					ctitle.focus();
					return false;
				}
			});
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
 	<c:if test="${empty member}">
		<script>
			alert("글쓰기는 회원만 가능합니다.");
			location.href="${conPath}/loginView.do?next=consultWriteView.do";
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content-form">
		<div class="conBoard-title">견적문의</div>
		<div class="conBoard-wrap">
			<form action="consultWrite.do" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<th><label for="ctitle">견적문의제목</label></th>
						<td class="left">
							<input type="text" name="ctitle" class="ctitle" id="ctitle">
						</td>
					</tr>
					<tr>
						<th><label for="ccontent">요청사항</label></th>
						<td>
							<textarea name="ccontent" id="ccontent" rows="8" cols="90"></textarea>
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td class="file-left">
							<input type="file" name="cfilename">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit" class="btnStyle">글쓰기</button>
							<button type="reset" class="btnStyle btn2">취소</button>
							<button type="button" class="btnStyle btn2" onclick="location.href='${conPath}/consultList.do'">목록</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>