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
	<link href="${conPath }/css/review.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script>
		$(document).ready(function(){
			$("tr").click(function(){
				var nid = Number($(this).children().eq(0).text());
				if(!isNaN(nid)){
					location.href="${conPath}/noticeContent.do?nid="+nid+"&pageNum=${pageNum}";
				}
			})
		});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="review-wrap">
		<div class="review-top">
			<div class="reviewTotCnt">총 <b>${reviewTotCnt }</b>개의 고객 후기</div>
		</div>
		<div class="review-content">
			<table>
				<c:if test="${reviewTotCnt eq 0 }">
					<tr><td>등록된 글이 없습니다.</td></tr>
				</c:if>
				<c:if test="${reviewTotCnt != 0 }">
				<c:forEach var="review" items="${reviewList }">
					<tr>
						<td colspan="2">
							<div class="review-row">
								<div class="review-left">
									<div class="rtitle">
										<h3>${review.rtitle}</h3>
									</div>
									<div class="rcontent">${review.rcontent }</div>
									<div class="rname">
										${review.mname } | <fmt:formatDate value="${review.rdate }" type="date" pattern="yyyy.MM.dd"/>
									</div>
								</div>
								<div class="rphoto">
									<img src="${conPath }/reviewPhotoUp/${review.rphoto}" alt="${review.rphoto }사진">
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
				</c:if>
			</table>
			<div class="paging">
				<a href="${conPath }/rboardList.do?pageNum=1" class="first">1</a>
				<c:if test="${BLOCKSIZE < startPage}">
					<a href="${conPath }/rboardList.do?pageNum=${startPage-1}" class="prev">1</a>
				</c:if>
				<c:if test="${BLOCKSIZE >= startPage }">
					<a class="prev">1</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum }">
						<a class="now">${i }</a>
					</c:if>
					<c:if test="${i != pageNum }">
						<a href="${conPath }/rboardList.do?pageNum=${i}" class="move">${i }</a>
					</c:if>
				</c:forEach>
				<c:if test="${endPage < nTotPageCnt }">
					<a href="${conPath }/rboardList.do?pageNum=${endPage+1}" class="next"></a>
				</c:if>
				<c:if test="${pageTotCnt eq endPage}">
					<a class="next"></a>
				</c:if>
					<a href="${conPath }/rboardList.do?pageNum=${pageTotCnt}" class="last"></a>
			</div>
		</div>
		<div class="write-btn">
			<c:if test="${not empty member }">
				<button onclick="location.href='${conPath}/rboardWriteView.do'" class="btn-style">글쓰기</button>
			</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>