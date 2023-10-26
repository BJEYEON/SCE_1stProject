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
      #jb-container {
        width: 900px;
        margin: 0px auto;
        padding: 20px;
      }
      #jb-header {
        padding: 20px;
        margin-bottom: 20px;
      }
      #jb-content {
        width: 450px;
        padding: 20px;
        margin-bottom: 20px;
        float: left;
        
      }
      #jb-sidebar {
        width: 300px;
        padding: 20px;
        margin-bottom: 20px;
        float: right;
      }
      #jb-footer {
        clear: both;
        padding: 20px;
      }
      #div img {
      margin-right:20px;
      	width: 200px;
      	height: 20px;
      }
</style>
</head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<body>
<jsp:include page="../main/header.jsp"/>
<div id="jb-container">
      <div id="jb-header">
        <h1>${carList.cname }</h1>
      </div>
      <div id="jb-content">
        <h2></h2>
        <img src="${conPath}/image/${carList.cimage}" class="car">
      </div>
      <div id="jb-sidebar">
        <h2>기본정보</h2>
        <p>연료: ${carList.coil }</p>
        <p>엔진: ${carList.cengine }</p>
        <p>변속기: ${carList.ctrance }</p>
        <p>출력: ${carList.chp }</p>
        <p>배기량: ${carList.ccc }</p>
        <p>연식: ${carList.cyear }</p>
      </div>
      <div id="jb-footer">
        <p></p>
      </div>
    </div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>