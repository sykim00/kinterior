<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>견적문의 게시판</title>
	<link href="${conPath }/css/consultantList.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script>
		$(document).ready(function(){
			$("tr").click(function(){
				var cid = Number($(this).children().eq(0).text());
				if(!isNaN(cid)){
					location.href="${conPath}/consultContent.do?cid="+cid+"&pageNum=${pageNum}";
				}
			});
		});
	</script>
</head>
<body>
	<c:if test="${not empty consultantWriteResult }">
		<script>
		alert("${consultantWriteResult}");
		</script>
	</c:if>
	<c:if test="${not empty conModifyResult}">
		<script>
			alert("${conModifyResult }");
		</script>		
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="consultant-wrap">
		<div class="consultant-title">견적문의 게시판</div>
		<div class="consultant-content">
			<table>
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>작성일</td>
				</tr>
				<c:if test="${conTotCnt eq 0 }">
					<tr><td colspan="4">등록된 글이 없습니다.</td></tr>
				</c:if>
				<c:if test="${conTotCnt != 0 }">
					<c:forEach items="${consultantList }" var="conBoard">
						<tr>
							<td>${conBoard.cid}</td>
							<td style="display: none;">${conBoard.cgroup }</td>
							<td class="left">
								<c:forEach var="i" begin="${conBoard.cindent}" end="0">
									<c:if test="${i eq conBoard.cindent}">
										<img src="${conPath }/img/lock_icon.png">
									</c:if>
								</c:forEach>
								<c:forEach var="i" begin="1" end="${conBoard.cindent}">
									<c:if test="${i == conBoard.cindent}">
										<c:if test="${not empty conBoard.aid }">
										&nbsp;&nbsp;&nbsp;
											<img src="${conPath }/img/indent_img.png">
											<img src="${conPath }/img/re_img.jpg" alt="답변글 이미지">
										</c:if>
										<c:if test="${not empty conBoard.mid }">
										&nbsp;
											<img src="${conPath }/img/indent_img.png">											
										</c:if>
										<img src="${conPath }/img/lock_icon.png">
									</c:if>
									<c:if test="${i != conBoard.cindent}">
									&nbsp;
										<c:if test="${not empty conBoard.mid }">
											&nbsp; &nbsp; &nbsp;
											<%-- <img src="${conPath }/img/indent_img.png">	 --%>										
										</c:if>
									</c:if>
								</c:forEach>
								${conBoard.ctitle}
								<c:if test="${not empty conBoard.cfilename}">
									<img src="${conPath }/img/file.png" alt="파일첨부이미지" class="fileimg">
								</c:if>
							</td>
							<td>
									${conBoard.mname }
									${conBoard.aname }
							</td>
							<td>
								<fmt:formatDate value="${conBoard.crdate}" type="date" pattern="yyyy.MM.dd" />
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<div class="paging">
				<a href="${conPath }/consultList.do?pageNum=1" class="first">1</a>
				<c:if test="${startPage > BLOCKSIZE }">
					<a href="${conPath }/consultList.do?pageNum=${startPage-1}" class="prev">1</a>
				</c:if>
				<c:if test="${BLOCKSIZE >= startPage }">
					<a class="prev">1</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum }">
						<a class="now">${i }</a>
					</c:if>
					<c:if test="${i != pageNum }">
						<a href="${conPath }/consultList.do?pageNum=${i}" class="move"> ${i} </a>
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageTotCnt }">
					<a href="${conPath }/consultList.do?pageNum=${endPage+1}" class="next">1</a>
				</c:if>
				<c:if test="${endPage eq pageTotCnt}">
					<a class="next">1</a>
				</c:if>
					<a href="${conPath }/consultList.do?pageNum=${pageTotCnt}" class="last">1</a>
			</div>
		</div>
		<div class="write-btn">
			<c:if test="${not empty member || empty admin }">			
				<button type="button" onclick="location.href='${conPath}/consultWriteView.do'">문의하기</button>
			</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>