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
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
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
    padding-left: 5px;
    padding-color: blcak;
    list-style-type: none;
    float: left;
    text-align:center;
    width:255px; height: 100px; 
    margin:2px;
    user-select: none;
    -o-user-select: none;
    position: relative;
    cursor: pointer;
    background-color: #8e8e8e;
    border: 2px solid #8e8e8e;
    overflow: hidden;
    display: inline-block; /* 리스트 아이템을 수평으로 표시 */
}
.row category{
	width: 1128px;
	margin: 0 auto;
}

.subscribe{
	height: 1000px;
	width:1100px;
	margin: 0 auto;
}
div{
	display: block;
}

.categoryName{
	position: absolute;
    left: 10px;
    bottom: 5px;
    vertical-align: bottom;
    font-size: 15px;
}
.carList{
	text-align: center;
}




.form-container {
    width: 400px;
    margin: 20px auto;
    padding: 20px;
    background-color: #f8f8f8;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}
}

.param {
    font-weight: bold;
    margin-bottom: 10px;
}

.input-group {
    margin: 10px 0;
}
form {
	width: 280px;
	margin: 0 auto;
}
label {
    display: inline-block;
    width: 100px;
}

form input[type="text"],
form input[type="radio"],
form select {
    width: 250px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin: 5px 0;
}

form .submit {
    text-align: center;
    margin-top: 20px;
}

form input[type="submit"],
form input[type="reset"] {
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
    margin: 5px;
}

form input[type="submit"]:hover,
form input[type="reset"]:hover {
    background-color: #0056b3;
}
form .radio-group {
    display: flex;
}

form .radio-group label {
    margin-right: 10px;
}

form input[type="radio"] {
    width: 90px;
}
</style>


<script>
	
	$(document).ready(function() {
		$('.item').mouseover(function(){
			$(this).css("background-color", "white");
		  });
		$('.item').mouseout(function(){
		    $(this).css("background-color", "#8e8e8e");
		  });
		$('.item').click(function(){
			$(this).css("background-color", "white");
			var str_imgName = '${conPath}/image/' + $(this).attr('id');
			$('input[name=carname]').attr("value", $(this).attr('data'));
			$('img.car').attr('src', str_imgName);
		});
	});
</script>
</head>
<body>
<c:if test="${not empty applyResult }">
		<script>
			alert('${applyResult}');
		</script>
	</c:if>
	
	<c:if test="${not empty applyErrorMsg }">
		<script>
			alert('${applyErrorMsg}');
			history.back();
		</script>
	</c:if>
<jsp:include page="../main/header.jsp"/>
<div class="subscribe">
	<h1 class="title">lamborghni</h1>
	<div class="category">
		<ul class="row category">
			<c:forEach items="${carList }" var="list">
				<li class="item" id="${list.cimage }" data="${list.cnum }"> <!-- var="list" -->
					<span class="categoryName">${list.cname }</span>
				</li>
			</c:forEach>
		</ul>
		<div class="carList">
			<img src="${conPath}/image/레부엘토.JPG" class="car">
		</div>
	</div>
	<form class="form" name="form" action="${conPath }/testCarApp.do" method="post">
		<h2 class="param">필수 입력 정보</h2>
		<div class="th">
			<input type="hidden" name="carname" value=1>
		</div>
		<div class="tr">이름</div>
			<div class="td">
				<input type="text" class="tname" name="tname" placeholder="이름을 입력해주세요."/>
			</div>
		<div class="tr">전화번호</div>
			<div class="td">
				<input type="text" class="ttel" name="ttel" >
			</div>
		<div class="tr">성별</div>
			<div class="td">
				<input type="radio" class="tgender" name="tgender" value="m" checked="checked"/>남자
				<input type="radio" class="tgender" name="tgender" value="f"/>여자
			</div>
		<div class="tr">지역</div>
			<select class="td" name="tarea">
				<option value="서울">서울</option>
				<option>경기</option>
				<option>충청도</option>
				<option>전라도</option>
				<option>경상도</option>
				<option>제주도</option>
			</select>
		<div class="tr">전시장</div>
			<select class="td" name="thall">
				<option value="서울 전시장">서울 전시장</option>
				<option>고양 전시장</option>
				<option>대전 전시장</option>
				<option>광주 전시장</option>
				<option>부산 전시장</option>
			</select>
		<div class="tr">구매의향</div>
			<select class="td" name="tselmind">
				<option value="3개월 이내">3개월 이내</option>
				<option>6개월 이내</option>
				<option>1년 이내</option>
				<option>1년 이후</option>
				<option>계획없음</option>
			</select>
		<div class="tr">이메일</div>
			<div class="td">
				<input type="text" class="temail" name="temail" placeholder="이메일 주소를 입력해주세요."/>
			</div>
		<div class="submit">
			<input type="submit" value="신청하기"/>
			<input type="reset" value="취소"/>
		</div>
	</form>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>