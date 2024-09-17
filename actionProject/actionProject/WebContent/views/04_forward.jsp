<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>여기는 04_forward 페이지</h1>
	
	<!-- forward 는 url은 유지한채로 페이지 이동(위임) -->
	<%request.setCharacterEncoding("UTF-8");%>
	<jsp:forward page="footer.jsp">
		<jsp:param value="안녕" name="test"/>
	</jsp:forward>


</body>
</html>