<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath}/css/style.css" rel="stylesheet">
<style>
#content_form {
	margin: 100px auto 0px;
	width: 600px;
}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/boradModify.do" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="bnum" value="${board.bnum }"> <input
				type="hidden" name="bimage" value="${board.bimage }">
			<table>
				<caption>${board.bnum }번글수정</caption>
				<tr>
					<th>작성자</th>
					<td><input type="text" required="required"
						value="${board.mname }(${board.mid })" readonly="readonly"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="btitle" required="required"
						value="${board.btitle }"></td>
				</tr>
				<tr>
					<th>본문</th>
					<td><textarea rows="5" name="bcontent">${board.bcontent }</textarea></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="bimage" class="btn">
						원첨부파일: <c:if test="${not empty board.bimage}">
							<a href="${conPath }/boardUp/${board.bimage}" target="_blank">${board.bimage}</a>
						</c:if> <c:if test="${empty board.bimage }">
						 		첨부파일없음
						 	</c:if></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="수정" class="btn">
						<input type="reset" value="이전" class="btn"
						onclick="history.back()"> <input type="button" value="목록"
						class="btn"
						onclick="location='${conPath}/boardList.do?pageNum=${param.pageNum }'">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>