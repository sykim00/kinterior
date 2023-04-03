<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지사항 게시판 글쓰기</title>
	<link href="${conPath }/css/noticeWrite.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script>
		$(document).ready(function(){
			$(".btn2").mouseover(function(){
				$(this).css("background","#e9e9e9").css("color","#000").css("border","1px solid #dbdbdb");
			});
			$(".btn2").mouseleave(function(){
				$(this).css("color","#000").css("border","1px solid #dbdbdb").css("background","#fff");
			});
			$(".notice-wrap table tr td .focusB").focus(function(){
				$(this).css("border","1px solid #000000");
			});
			$(".notice-wrap table tr td .focusB").blur(function(){
				$(this).css("border", "1px solid #b8b8b8");
			});
			$("form").submit(function(){
				var ntitle = $(".ntitle").val();
				var ncontent = $(".ncontent").val();
				if(ntitle==""){
					alert("공지사항 제목을 입력해주세요.");
					$("#title").focus();
					return false;
				}else if(ncontent==""){
					alert("공지사항 본문을 입력해주세요.");
					$("#content").focus();
					return false;
				}
			});
		});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="notice-form">
		<div class="notice-title">공지사항 글 작성</div>
		<div class="notice-wrap">
			<form action="noticeWrite.do" method="post">
				<table>
					<tr>
						<th><label for="title">제목</label></th>
						<td class="left">
							<input type="text" name="ntitle" class="ntitle focusB" id="title">
						</td>
					</tr>
					<tr>
						<th><label for="content">본문</label></th>
						<td>
							<textarea name="ncontent" id="content" rows="8" cols="90" class="ncontent focusB"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit" class="btnStyle">글쓰기</button>
							<button type="reset" class="btnStyle btn2">취소</button>
							<button type="button" class="btnStyle btn2" onclick="location.href='${conPath}/noticeList.do'">목록</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>