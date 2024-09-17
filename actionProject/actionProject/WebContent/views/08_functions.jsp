<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSTL Functions Library</h1>
	
	<!-- "fn"은 JSTL(JSP Standard Tag Library)의 함수 라이브러리(function library) 중 하나인 "fn"을 의미 -->
	<c:set var="str" value="Hello World"/>
	str : ${str} <br>
	
	문자열의 길이 : ${fn:length(str) } 글자 <br>
	문자열의 길이 : ${str.length() } 글자 <br> <!-- 자바 메소드 방식 -->
	
	모두 대문자로 출력 : ${fn:toUpperCase(str) }<br>
	모두 소문자로 출력 : ${fn:toLowerCase(str) }<br>
	
	World의 시작 인덱스 : ${fn:indexOf(str,"World") } <br>
	World를 Bye로 변환 : ${fn:replace(str,"World","Bye") } <br>
	
	str에 World가 포함되어 있나요? : ${fn:contains(str,"World") } <br>
	<c:if test="${fn:contains(str,'World') }">
		<h2>포함되어 있어요</h2>
	</c:if>
	
	
</body>
</html>