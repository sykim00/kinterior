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
			$(".conBoard-wrap table tr td .focusB").focus(function(){
				$(this).css("border","1px solid #000000");
			});
			$(".conBoard-wrap table tr td .focusB").blur(function(){
				$(this).css("border", "1px solid #b8b8b8");
			});
			$("form").submit(function(){
				var ctitle = $(".ctitle").val();
				if(ctitle == ""){
					alert("견적문의 제목을 꼭 입력해주세요.");
					$("#title").focus();
					return false;
				}
			});
			/*파일첨부하면 바로 보이게 주는 효과
			$(".cfilename").change(function(){
				setImagFromFile(this, "#preview");
			});
			function setImagFromFile(input, expression){
				if(input.files && input.files[0]){
					var reader = new FileReader();
					reader.onload = function(e){
						$(expression).attr("src", e.target.result);
						$(expression).css("width", "100%").css("height","100%");
						$("img.image").css("display", "none");
						$(".upload_file").css("border", "none");
					}
					reader.readAsDataURL(input.files[0]);
				}
			} // file첨부
			*/
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
	<c:if test=""></c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content-form">
		<div class="conBoard-title">견적문의</div>
		<div class="conBoard-wrap">
			<form action="consultWrite.do" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<th><label for="title">견적문의제목</label></th>
						<td class="left">
							<input type="text" name="ctitle" class="ctitle focusB" id="title">
						</td>
					</tr>
					<tr>
						<th><label for="content">요청사항</label></th>
						<td>
							<textarea name="ccontent" id="content" rows="12" cols="90">하단 내용을 적어주시면 빠른 상담을 진행 할 수 있습니다.&#10;: If you write down the information below, we can proceed with a quick consultation.&#10;&#10;&#10;*업종 : Type of business&#10;&#10;&#10;*현장주소 : Site address&#10;&#10;&#10;*연락처 : Contact&#10;&#10;&#10;*면적 : Area&#10;&#10;&#10;공사시기 : Period&#10;&#10;&#10;*예산 : Budget&#10;&#10;&#10;*평면도 : Floor plan&#10;&#10;&#10;*원하시는 방향(디자인 무드) : Design mood&#10;&#10;&#10;</textarea>
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td class="file-left">
							<input type="file" name="cfilename" class="cfilename">
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;">
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