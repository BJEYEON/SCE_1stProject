<%@page import="com.lec.sce.dto.BoardDto"%>
<%@page import="com.lec.sce.dao.BoardDao"%>
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
</head>
<body>
<%
	BoardDao dao = BoardDao.getInstance();
	BoardDto dto = new BoardDto();
	for(int i=0 ; i<70 ; i++){
		dto.setBtitle("좋은 정보 " + i);
		dto.setBcontent(i + "번째 본문");
		dto.setBip("192.168.10."+i);
		if(i%5!=0){
			dto.setMid("go");
			dto.setBimage(null);
			dao.writeBoard(dto);			
		}else if(i%5==0){
			dto.setMid("gico");
			dto.setBimage("a.docx");
			dao.writeBoard(dto);		
		}	
	}
	response.sendRedirect("../boardList.do");
%>
</body>
</html>