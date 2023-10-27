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
<%-- <link href="${conPath}/css/style.css" rel="stylesheet"> --%>
<style>
#content_form {
	height: 600px;
	margin: 30px auto 0px;
	width: 70%;
	margin: 20px auto;
}

#content_form table tr {
	height: 10px;
}

/* 테이블 스타일 */
#content_form table {
	width: 90%;
	margin-top: 20px;
}

#content_form table th {
	background-color: black;
	color: #fff;
	text-align: center;
	padding: 10px;
}

#content_form table td, #content_form table th {
	padding: 10px;
}

#content_form table tr {
	height: 40px;
}

#content_form table tr:hover {
	background-color: #f0f0f0;
	cursor: pointer;
}

/* 게시물 제목 스타일 (링크로 변경) */
#content_form table td.left a {
	text-decoration: none;
	color: #333;
}

/* 페이지 번호 스타일 */
#content_form .paging {
	text-align: center;
	margin-top: 20px;
}

#content_form .paging a, #content_form .paging b {
	text-decoration: none;
	color: #333;
	margin: 0 5px;
}

#content_form .paging a:hover {
	background-color: #333;
	color: #fff;
}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>

</head>
<body>
<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<br>
		<table>
			<tr>
				<th>신청번호</th>
				<th>신청차량</th>
				<th>신청자</th>
				<th>전화번호</th>
				<th>성별</th>
				<th>지역</th>
				<th>전시장</th>
				<th>구매의향</th>
				<th>이메일</th>
			</tr>
			<c:if test="${totCnt==0 }">
				<tr>
					<td colspan="9">등록된 시승이 없습니다</td>
				</tr>
			</c:if>
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${applyList }" var="board">
					<!-- 밑에있는<td>${board.tnum }</td> -->
					<tr>
						<td>${board.tnum }</td>
						<td>${board.cname }</td>
						<td>${board.tname }</td>
						<td>${board.ttel }</td>
						<td>${board.tgender }</td>
						<td>${board.tarea }</td>
						<td>${board.thall }</td>
						<td>${board.tselmind }</td>
						<td>${board.temail }</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
				[ <a href="${conPath }/boardList.do?pageNum=${startPage-1}"> 이전
				</a> ]
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b> [ ${i } ] </b>
				</c:if>
				<c:if test="${i != pageNum }">
					[ <a href="${conPath }/boardList.do?pageNum=${i}"> ${i } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
			  [ <a href="${conPath }/boardList.do?pageNum=${endPage+1}"> 다음 </a> ]
			</c:if>
		</div>
	</div>
<jsp:include page="../main/footer.jsp" />
</body>
</html>