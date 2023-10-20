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
/*****폰트설정*****/
@font-face {
    font-family: 'lam-font';
    src: url('${conPath}/font/font.woff2');
}
*{
   
    font-family: 'lam-font';
}
header .container, .header{
	padding: 0 1.5rem;
}
header .container{
	align-items: center;
    display: flex;
    height: 68px;
    position: relative;
}
header .logo_img{
    height: 50px;
    overflow-clip-margin: content-box;
    overflow: clip;
    border-style: none;
    /*margin-top:250px;*/
    margin-left:100px;
}
header .gnb {
	height: 90px;
    width: 100%;
    display: flex;
    align-items: center;
    display: flex;
    list-style: none;
    margin: 0 auto;
    
    min-width:915px;
    overflow:hidden;
    line-height:30px;
    border-bottom:1px solid gray;
    border-right: 10px;
    border-width: 2px;
}

header .gnb ul li{
    float:left;
    width:70px;
    text-align:center;
    list-style: none;
    margin-right: 50px;
}
header a{
    justify-content: space-between;
    padding: 0.75rem 0;
    width: 100%;
    align-items: center;
    cursor: pointer;
    display: flex;
    font-size: 1rem;
    font-weight: 700;
    line-height: 1.5rem;
    margin: 0;
    padding: 0 0.75rem;
    position: relative;
    text-decoration: none;
    color: black;
}
</style>
</head>
<body>
<header>
	<c:if test="${empty member and empty admin}"> <%-- 로그인 전 화면 --%>
	<nav class="container">
		<div class="logo" onclick="location.href='${conPath}/main.do'">
			<img class="logo_img" onclick="location.href='${conPath}/boardList.do'" src="${conPath}/image/lambo.png">
		</div>
		<div class="gnb">
			<ul>
				<li><a href="${conPath }/searchCar.do">차량검색</a></li>
				<li><a href="${conPath }/joinView.do">회원가입</a></li>
				<li><a href="${conPath }/loginView.do">로그인</a></li>
			</ul>
		</div>
	</nav>
	</c:if>
	<c:if test="${not empty member and empty admin}"> <%-- 사용자 모드 로그인 화면--%>
	<nav class="container">
		<div class="logo" onclick="location.href='${conPath}/main.do'">
			<img class="logo_img" onclick="location.href='${conPath}/boardList.do'" src="${conPath}/image/lambo.png">
		</div>
		<div class="gnb">
			<ul>
				<li><a>${member.mname }님 </a></li>
				<li><a href="${conPath }/modifyView.do">정보수정</a></li>
				<li><a href="${conPath }/logout.do">로그아웃</a></li>
				<li><a href="${conPath }/boardList.do">차량후기</a></li>
				<li><a href="${conPath }/testCarApp.do">시승신청</a></li>
			</ul>
		</div>
	</nav>
	</c:if>
	<c:if test="${empty member and not empty admin}"> <%-- 관리자 모드 로그인 화면--%>
	<nav class="container">
		<div class="logo" onclick="location.href='${conPath}/main.do'">
			<img class="logo_img" onclick="location.href='${conPath}/boardList.do'" src="${conPath}/image/lambo.png">
		</div>
		<div class="gnb">
			<ul>
				<li><a>${admin.aname }님 ▶</a></li>
				<li><a href="${conPath }/boardList.do">후기게시판</a></li>
				<li><a href="${conPath }/logout.do">관리자모드나가기</a></li>
				<li><a href="${conPath }/applyList.do">시승신청목록</a></li>
					
			</ul>
		</div>
	</nav>
	</c:if>
</header>
</body>
</html>