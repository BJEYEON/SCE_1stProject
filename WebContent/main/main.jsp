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
*{
	border:0;
	margin:0;
}
#content_form {
	width: 100%; height:100%; line-height:500px;
	margin: 0 auto; text-align: center;
    background-image: url("${conPath}/image/mainLam.jpg");
	background-size: cover;
}
div .text{
	font-size: 3rem;
    font-weight: 300;
    line-height: 5rem;
    text-align: center;
    /*vertical-align: bottom;*/
}
header .logo_img{
    margin-top:250px;
}
</style>
</head>
<body>
<c:if test="${not empty next }">
		<script>
			location.href = '${conPath}/${next}';
		</script>
	</c:if>
	<c:if test="${not empty adminLoginResult }">
		<script>
			alert('${adminLoginResult}');
		</script>
	</c:if>
	<c:if test="${not empty adminLoginErrorMsg }">
		<script>
			alert('${adminLoginErrorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty loginErrorMsg }">
		<script>
			alert('${loginErrorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty withdrawalResult }">
		<script>
			alert('${withdrawalResult}');
		</script>
	</c:if>
	<div id="content_form">
	<jsp:include page="../main/header.jsp"/>
		&nbsp;
	</div>
	<div>
		<h2 class="text">지금 바로 Lamborghini를 경험해보세요.</h2>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>