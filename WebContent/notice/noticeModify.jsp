<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지사항 게시판 수정하기</title>
	<link href="${conPath }/css/noticeWrite.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script>
		$(document).ready(function(){
			$("form").submit(function(){
				var ntitle = $(".ntitle").val();
				if(ntitle == ""){
					alert("공지사항 제목을 꼭 입력해주세요.");
					$("#title").focus();
					return false;
			}
		});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="notice-form">
		<div class="notice-title">공지사항</div>
		<div class="notice-wrap">
			<form action="noticeModify.do" method="post">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="nid" value="${noticeModify.nid }">
				<table>
					<tr>
						<th><label for="title">제목</label></th>
						<td class="left">
							<input type="text" name="ntitle" class="ntitle focusB" id="title" value="${noticeModify.ntitle }">
						</td>
					</tr>
					<tr>
						<th><label for="content">본문</label></th>
						<td>
							<textarea name="ncontent" id="content" rows="8" cols="90" class="focusB">${noticeModify.ncontent }</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit" class="btnStyle">수정하기</button>
							<button type="reset" class="btnStyle btn2">취소</button>
							<button type="button" class="btnStyle btn2" onclick="location.href='${conPath}/noticeList.do?pageNum=${param.pageNum }'">목록</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>