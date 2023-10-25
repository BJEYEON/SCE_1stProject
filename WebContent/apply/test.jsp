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
<style>
body {
    transition: background-color 0.5s; /* 색상 전환을 부드럽게 만듭니다. */
}

.button-clicked {
    background-color: #3498db; /* 클릭 시 배경색으로 사용할 색상 지정 */
}
</style>
<script>
const changeColorButton = document.getElementById('changeColorButton');
const body = document.body;

changeColorButton.addEventListener('click', function() {
    // 'button-clicked' 클래스를 body에 추가하여 배경색을 변경합니다.
    body.classList.toggle('button-clicked');
});
</script>
  <title>배경색 변경 예제</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <button id="changeColorButton">색상 변경</button>
</body>
<script src="script.js"></script>
</body>
</html>