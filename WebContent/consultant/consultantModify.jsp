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
	<jsp:include page="../main/header.jsp"/>
	<div id="content-form">
		<div class="conBoard-title">견적문의 글 수정</div>
		<div class="conBoard-wrap">
			<form action="consultModify.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="pageNum" value=${param.pageNum }>
			<input type="hidden" name="cid" value="${conBoard.cid}">
			<input type="hidden" name="dbcfilename" value="${conBoard.cfilename }">
				<table>
					<tr>
						<td><label for="ctitle">견적문의제목</label></td>
						<td class="left">
							<input type="text" name="ctitle" class="ctitle" id="ctitle" value="${conBoard.ctitle }">
						</td>
					</tr>
					<tr>
						<td><label for="ccontent">요청사항</label></td>
						<td>
							<textarea name="ccontent" id="ccontent" rows="8" cols="90">${conBoard.ccontent }</textarea>
						</td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td class="file-left">
							<input type="file" name="cfilename"> 원 첨부파일 : 
							<c:if test="${not empty conBoard.cfilename }">
								<a href="${conPath }/consultantBoardFileUp/${conBoard.cfilename}" target="_blank" style="display:inline-block;">${conBoard.cfilename }</a>
							</c:if>
							<c:if test="${empty conBoard.cfilename }">
								첨부파일 없음
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit" class="btnStyle">수정</button>
							<button type="reset" class="btnStyle btn2" onclick="history.back()">이전</button>
							<button type="button" class="btnStyle btn2" onclick="location.href='${conPath}/consultList.do?pageNum=${param.pageNum }'">목록</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>