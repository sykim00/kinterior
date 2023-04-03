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
	<link href="${conPath }/css/interiorBoard.css" rel="stylesheet" type="text/css">
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	<!-- include summernote css/js-->
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
	<script>
		$(document).ready(function(){
		 	$('#summernote').summernote({	
		        height: 300,
		        minHeight: null,
		        maxHeight: null,
		        lang : 'ko-KR',
		        onImageUpload: function(files, editor, welEditable) {
		                sendFile(files[0], editor, welEditable);
		            }
		    });
			$("form").submit(function(){
				var ptitle = $(".ptitle").val();
				var pcontent = $(".pcontent").val();
				if(ptitle==""){
					alert("시공사례 글 작성시 제목을 입력해주세요.");
					$("#title").focus();
					return false;
				}else if(pcontent==""){
					alert("시공사례 글 작성시 본문을 입력해주세요.");
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
			<div id="interior-form">
				<div class="interior-title">시공사례</div>
				<div class="interior-wrap">
					<form action="interiorModify.do" method="post" enctype="multipart/form-data">
						<input type="hidden" name="pid" value="${interiorModify.pid}">
						<input type="hidden" name="pageNum" value="${param.pageNum }">
						<input type="hidden" name="dbpfilename" value="${interiorModify.pfilename }">	
						<table>
							<tr>
								<th><label for="title">시공제목</label></th>
								<td>
									<input type="text" name="ptitle" class="ptitle focusB" id="title" value="${ interiorModify.ptitle}">
								</td>
							</tr>
							<tr>
								<th><label for="content">시공본문</label></th>
								<td>
									<textarea name="pcontent" id="summernote" rows="12" cols="90" class="rcontent focusB">${interiorModify.pcontent }</textarea>
								</td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td class="file-left">
									<input type="file" name="pfilename" class="pfilename" accept="image/*"> 원 첨부파일 : ${interiorModify.pfilename }
								</td>
							</tr> 
							<tr>
								<td colspan="2" style="text-align: center;">
									<button type="submit" class="btnStyle">수정</button>
									<button type="reset" class="btnStyle btn2" onclick="history.back()">이전</button>
									<button type="button" class="btnStyle btn2" onclick="location.href='${conPath}/interiorList.do?pageNum=${param.pageNum} '">목록</button>
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