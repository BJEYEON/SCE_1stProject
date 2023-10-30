<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	footer {height:80px;}
	footer #footer_conts, footer #footer_conts a {
		color:black;
		font-weight: blod;
		font-size:0.8em;
		text-align: center;
	}
	footer #footer_conts a {
		display: inline;
	}
	footer #footer_conts p:first-child {
		font-weight: bold; height: 30px; line-height: 40px;
	}
</style>
</head>
<body>
	<footer>
		<div id="footer_conts">
			<p>(주)람보주식회사</p> 
			<p>서울특별시 은평구 불광로122-10 | <b><a href="${conPath }/adminLoginView.do">관리자 모드</a></b></p>
			<p>Copyright© 2023 tj . All rights reserved.</p>
		</div>
	</footer>
</body>
</html>