<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>시공사례</title>
	<link href="${conPath }/css/interior.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script>
		$(document).ready(function(){
			$("tr").click(function(){
				var pid = Number($(this).children().eq(0).text());
				if(!isNaN(pid)){
					location.href="${conPath}/interiorContent.do?pid="+pid+"&pageNum=${pageNum}";
				}
			})
		});
	</script>
</head>
<body>
	<c:if test="${not empty consultantWriteResult }">
		<script>
			alert("${consultantWriteResult}");
		</script>
	</c:if>
	<c:if test="${not empty interiorWriteResult }">
		<script>
			alert("${interiorWriteResult}");
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="interior-wrap">
		<div class="interior-top">
			<div class="interiorTotCnt">총 <b>${interiorTotCnt }</b>개</div>
		</div>
		<div class="interior-content">
			<table>
				<tr style="display:none;">
					<td>글번호</td>
				</tr>
				<c:if test="${interiorTotCnt eq 0 }">
					<tr><td>등록된 글이 없습니다.</td></tr>
				</c:if>
				<c:if test="${interiorTotCnt != 0 }">
				<c:forEach var="interior" items="${interiorList }">
					<tr>
						<td style="display:none;">${interior.pid }</td>
						<td>
							<div class="interior-row">
								<div class="interior_view">
									<div class="pfilename">
										<img src="${conPath }/interiorFileUp/${interior.pfilename}" alt="${interior.pfilename }사진">
									</div>
									<div class="ptitle">
										<h3>${interior.ptitle}</h3>
									</div>
									<div class="pname">
										<fmt:formatDate value="${interior.prdate }" type="date" pattern="yyyy.MM.dd"/>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
				</c:if>
			</table>
			<div class="paging">
				<a href="${conPath }/interiorList.do?pageNum=1" class="first"></a>
				<c:if test="${BLOCKSIZE < startPage}">
					<a href="${conPath }/interiorList.do?pageNum=${startPage-1}" class="prev"></a>
				</c:if>
				<c:if test="${BLOCKSIZE >= startPage }">
					<a class="prev"></a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum }">
						<a class="now">${i }</a>
					</c:if>
					<c:if test="${i != pageNum }">
						<a href="${conPath }/interiorList.do?pageNum=${i}" class="move">${i }</a>
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageTotCnt }">
					<a href="${conPath }/interiorList.do?pageNum=${endPage+1}" class="next"></a>
				</c:if>
				<c:if test="${pageTotCnt eq endPage}">
					<a class="next"></a>
				</c:if>
					<a href="${conPath }/interiorList.do?pageNum=${pageTotCnt}" class="last"></a>
			</div>
		</div>
		<div class="write-btn">
			<c:if test="${not empty admin }">
				<button onclick="location.href='${conPath}/interiorWriteView.do'" class="btn-style">글쓰기</button>
			</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>