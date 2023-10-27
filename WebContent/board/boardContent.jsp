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
	width: 600px;
	height: 450px;
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
		$(document).ready(function(){
			
		});
	</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<table>
			<caption>${board.bnum }글 상세보기</caption>
			<tr><td>작성자</td><td>${board.mname} (${board.mid}) 님</td>	</tr>
			<tr><td>제목</td>	 <td>${board.btitle }</td></tr>
			<tr><td>본문</td>	 <td><pre>${board.bcontent}</pre></td></tr>
			<tr><th>조회수</th><td>${board.bhit }</td></tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<c:if test="${not empty board.bimage }">
						<a href="${conPath }/boardUp/${board.bimage}" target="_blank">${board.bimage}</a>
					</c:if>
					<c:if test="${empty board.bimage }">
						첨부파일없음
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<c:if test="${member.mid eq board.mid }">
				 		<button class="btn" onclick="location='${conPath}/boardModifyView.do?bnum=${board.bnum }&pageNum=${param.pageNum }'">수정</button>
				 	</c:if>
				 	<c:if test="${member.mid eq board.mid or not empty admin}">
						<button class="btn" onclick="location='${conPath}/boardDelete.do?bgroup=${board.bgroup }&bstep=${board.bstep }&bindent=${board.bindent }&pageNum=${param.pageNum }'">삭제</button>
			 		</c:if>
				 	<c:if test="${not empty member }">
				 		<button class="btn" onclick="location='${conPath}/boardReplyView.do?bnum=${board.bnum }&pageNum=${param.pageNum }'">답변</button>
				 	</c:if>
				 	<button class="btn" onclick="location='${conPath}/boardList.do?pageNum=${param.pageNum }'">목록</button>
		</table>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>