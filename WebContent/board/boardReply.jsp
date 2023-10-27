<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
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
	margin: 20px auto;
	background-color: #f8f8f8;
	border-radius: 10px;
	padding: 20px;
	text-align: center;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

#content_form input:not(.btn), #content_form textarea {
	width: 90%;
	padding: 10px;
	border-radius: 5px;
	margin: 5px 0;
}

#content_form .gender-label {
	text-align: center;
	margin: 10px;
}

#content_form .gender-label label {
	margin-right: 5px;
	width: 20px;
}

#content_form .gender-input {
	display: flex;
	align-items: center;
}

#content_form .gender-input input {
	margin: 0 5px;
}

#content_form .btn {
	background-color: #e74c3c; /* 배경색 변경 */
	color: #fff; /* 글자색 변경 */
	border: none;
	border-radius: 5px;
	padding: 10px 20px; /* 여백 조정 */
	font-size: 16px;
	cursor: pointer;
}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<c:if test="${empty member }">
		<!-- 로그인 후에만 해당 답글을 달 수 있음 -->
		<script>
			location.href = '${conPath}/loginView.do?next=boardReplyView.do?bnum=${param.bnum }&pageNum=${param.pageNum }';
		</script>
	</c:if>
<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/boardReply.do" method="post"
			enctype="multipart/form-data">
			<!-- reply.do시 필요한 원글 정보 : bGroup, bStep, bIndent
			                              지금 입력할 답변글 : bName, bTitle, bContent
			                       pageNum -->
			<input type="hidden" name="bgroup" value="${originBoard.bgroup }">
			<input type="hidden" name="bstep" value="${originBoard.bstep }">
			<input type="hidden" name="bindent" value="${originBoard.bindent }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table>
				<caption>${originBoard.bnum }번글의답변쓰기 폼</caption>
				<tr>
					<td>작성자</td>
					<td>${member.mname }(${member.mid })</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="btitle" required="required"
						value="[답]${originBoard.btitle }"></td>
				</tr>
				<tr>
					<td>본문</td>
					<td><textarea name="bContent" rows="3" cols="3"></textarea></td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<td><input type="file" name="bimage"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="답변쓰기" class="btn">
						<input type="reset" value="취소" class="btn"> 
						<input type="button" value="목록" class="btn"
						onclick="location.href='${conPath}/boardList.do?pageNum=${param.pageNum }'">
			</table>
		</form>
	</div>
<jsp:include page="../main/footer.jsp" />
</body>
</html>