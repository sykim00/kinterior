<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>견적문의 글 상세 보기</title>
	<link href="${conPath }/css/font.css" rel="stylesheet" type="text/css">
	<link href="${conPath }/css/consultantWrite.css" rel="stylesheet" type="text/css">
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
	${readOk }
	<c:set var="consults" value="${consultantContent }"/>
	<c:if test="${readOk != 'success' }">
		<script>
			alert("질문자 본인만 확인가능합니다.");
			history.back();
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content-form">
		<div class="conBoard-title">견적문의</div>
		<div class="conBoard-wrap">
			<table>
				<tr>
					<th>작성자</th>
					<td class="left">
							${consults.mname}
							${consults.aname }
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td class="left">
							${consults.mid}
							${consults.aid }
					</td>
				</tr>
				<tr>
					<th>견적문의제목</th>
					<td class="left">
						${consults.ctitle }
					</td>
				</tr>
				<tr>
					<th>견적문의내용</th>
					<td class="left">
						${consults.ccontent }
					</td>
				</tr>
				<tr>
					<th class="">첨부파일</th>
					<td class="left">
						<c:if test="${not empty consults.cfilename }">
							<a href="${conPath }/consultantBoardFileUp/${consults.cfilename}" target="_blank">${consults.cfilename }</a>
						</c:if>
						<c:if test="${empty consults.cfilename }">
							첨부파일 없음
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<c:if test="${member.mid eq  consults.mid || admin.aid eq consults.aid}">
							<button type="button" class="btn2" onclick="location.href='${conPath}/consultModifyView.do?cid=${consults.cid}&pageNum=${param.pageNum }'">수정</button>
							<button type="reset" class="btn2" onclick="location.href='${conPath}/consultDelete.do?cgroup=${consults.cgroup }&cstep=${consults.cstep }&cindent=${consults.cindent }&pageNum=${param.pageNum }'">삭제</button>													
						</c:if>
							<button type="button" class="btn2" onclick="location.href='${conPath}/consultReplyView.do?cid=${consults.cid }&pageNum=${param.pageNum }'">답변</button>						
							<button type="button" class="btn2" onclick="location.href='${conPath}/consultList.do?pageNum=${param.pageNum}'">목록</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>