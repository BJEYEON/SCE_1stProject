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
.search {
  position: relative;
  width: 300px;
  display:none;
  left:700px;
  top:20px;
}

.search input {
  width: 100%;
  border: 1px solid #bbb;
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 14px;
}

.search img {
  position : absolute;
  width: 17px;
  top: 10px;
  right: 12px;
  margin: 0;
}

</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
	$(function(){
		$('button').click(function(){
			$('.search').css('display', 'inline-block');
		});
	});
</script>
</head>
<body>
<button>검색</button>
<div class="search">
  <input type="text" placeholder="검색어 입력">
  <img src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png">
</div>

    
</body>
</html>