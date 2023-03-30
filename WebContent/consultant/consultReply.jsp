<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>견적문의 게시판 답변글쓰기</title>
	<link href="${conPath }/css/consultantWrite.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script>
		$(document).ready(function(){
			$("#ctitle").keyup(function(){
				var ctitle = $(this).val();
				if(!ctitle){
					alert("제목을 꼭 입력해주세요.");
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
			$(".join-form table tr .focusB").focus(function(){
				$(this).css("border","1px solid #000000");
			});
			$(".join-form table tr .focusB").blur(function(){
				$(this).css("border", "1px solid #b8b8b8");
			});
		});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content-form">
		<div class="conBoard-title">${originconBoard.cid }번글의 견적문의 답변글쓰기</div>
		<div class="conBoard-wrap">
			<form action="consultReply.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="cgroup" value="${originconBoard.cgroup }">
			<input type="hidden" name="cstep" value="${originconBoard.cstep }">
			<input type="hidden" name="cindent" value="${originconBoard.cindent }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
				<table>
					<tr>
						<td>작성자</td>
						<td>
							<c:if test="${not empty member}">							
								${member.mname }(${member.mid })
							</c:if>
							<c:if test="${not empty admin }">
								${admin.aname }(${admin.aid })							
							</c:if>
						</td>
					</tr>
					<tr>
						<th><label for="ctitle">제목</label></th>
						<td class="reply-left">
							<c:if test="${not empty admin }">						
								<input type="text" name="ctitle" class="ctitle focusC" id="ctitle" value="${originconBoard.ctitle }" readonly="readonly">
							</c:if>
							<c:if test="${not empty member }">
								<input type="text" name="ctitle" class="ctitle focusC" id="ctitle" value="${originconBoard.ctitle }">
							</c:if>
						</td>
					</tr>
					<tr>
						<th><label for="ccontent">본문</label></th>
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
							<button type="submit" class="btnStyle">답변쓰기</button>
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