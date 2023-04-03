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
	<link href="${conPath }/css/reviewBoard.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script>
		$(document).ready(function(){
			$(".rphoto").change(function(){
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
			$("form").submit(function(){
				var rtitle = $(".rtitle").val();
				var rcontent = $(".rcontent").val();
				if(rtitle==""){
					alert("리뷰작성시 제목을 입력해주세요.");
					$("#title").focus();
					return false;
				}else if(rcontent==""){
					alert("리뷰작성시 본문을 입력해주세요.");
					$("#content").focus();
					return false;
				}
			});
		});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
		<div>
			<div id="review-form">
				<div class="review-title">시공후기</div>
				<div class="review-wrap">
					<form action="rboardWrite.do" method="post" enctype="multipart/form-data">
						<table>
							<tr>
								<th>메인이미지</th>
								<td class="file-left">
										<div class="upload_file">
											<img src="https://img.icons8.com/pastel-glyph/2x/image-file.png" alt="파일 아이콘" class="image">
											<img src="" id="preview">
										</div>
									<input type="file" name="rphoto" class="rphoto" accept="image/*">
								</td>
							</tr> 
							<tr>
								<th><label for="title">후기제목</label></th>
								<td>
									<input type="text" name="rtitle" class="rtitle focusB" id="title">
								</td>
							</tr>
							<tr>
								<th><label for="content">후기본문</label></th>
								<td>
									<pre><textarea name="rcontent" id="content" rows="12" cols="90" class="rcontent focusB"></textarea></pre>
								</td>
							</tr>
							<tr>
								<td colspan="2" style="text-align: center;">
									<button type="submit" class="btnStyle">글쓰기</button>
									<button type="reset" class="btnStyle btn2">취소</button>
									<button type="button" class="btnStyle btn2" onclick="location.href='${conPath}/rboardList.do'">목록</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>