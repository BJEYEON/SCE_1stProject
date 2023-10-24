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
<script>
	var color['gray','white'];
	window.onload = function() {
		lis = document.querySelectorAll('li');
		for (let i in lis){
			lis[i].style = 'background-color:' + color[i];
		}
	}
	function on(a){
		document.li.style = 'background-color:' + color[a];
	}
</script>
<style>
/*****폰트설정*****/
@font-face {
    font-family: 'lam-font';
    src: url('${conPath}/font/font.woff2');
}
*{
    font-family: 'lam-font';
    box-sizing: border-box;
}
.title {
	text-align: center;
}
.item{
	padding-right: 5px;
    padding-bottom: 5px;
    list-style-type: none;
    float: left;
    width:280px; height: 100px; margin: 10px auto;
    background-color: gray;
}
.subscribe{
	height: 300px;
}
</style>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
<div class="subscribe">
	<form class="form" name="form" action="acscribe" method="post">
		<h3 class="title">lamborghni</h3>
		<div class="category">
			<ul class="row category">
				<c:forEach items="${carList }" var="list">
				<li conmouseclick="on()" class="item">
					<span class="categoryName">${list.cname }</span>
				</li>
				</c:forEach>
			</ul>
		</div>
	</form>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>