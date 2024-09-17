<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>EL 연산</h1>
	
	<h3>1. 산술연산</h3>
	<p>
		*기존방식 <br>
		10 + 3 = <%=(int)request.getAttribute("big") + (int)request.getAttribute("small") %>
	</p>
	
	<p>
		*EL 연산 <br>
		10 + 3 = ${big+small} <br>
		10 - 3 = ${big-small} <br>
		10 x 3 = ${big*small} <br>
		10 / 3 = ${big/small} 또는 ${big div small} <br>
		10 % 3 = ${big%small} 또는 ${big mod small}
	</p>
	
	<hr>
	
	<h3>2.숫자간 대소 비교 연산</h3>
	<p>
		*기존방식 <br>
		10 > 3 : <%=(int)request.getAttribute("big") > (int)request.getAttribute("small") %>
	</p>
	
	<p>
		*EL 연산방식 <br>
		10 > 3 : ${big > small } 또는 ${big gt small } <br>
		10 < 3 : ${big < small } 또는 ${big lt small } <br>
		10 >= 3 : ${big >= small } 또는 ${big ge small }<br>
		10 <= 3 : ${big <= small } 또는 ${big le small } <br>
	</p>

	<br>
	
	<h3>3.동등 비교 연산</h3>
	<p>
		*기존 방식 <br>
		10과 3이 일치합니까? : <%=(int)request.getAttribute("big")==(int)request.getAttribute("small") %><br>
		sOne과 sTwo가 일치합니까? :<%=request.getAttribute("sOne").equals(request.getAttribute("sTwo")) %><br>
		sOne과 sTwo가 일치합니까? :<%=request.getAttribute("sOne") == request.getAttribute("sTwo") %>
	</p>
	
	<p>
		*EL연산 방식<br>
		10과 3이 일치합니까? : ${big == small } 또는 ${big eq small}<br>
		big에 담긴 값이 10과 일치합니까? : ${big == 10 } 또는 ${big eq 10 } <br>
		
		sOne과 sTwo가 일치합니까? : ${sOne == sTwo } 또는 ${sOne eq sTwo }<br>
		<!-- EL에서의 == 비교는 자바에서의 equals()메소드의 기능을 한다. -->
		sOne과 sTwo가 일치하지 않습니까? ${sOne != sTwo } 또는 ${sOne ne sTwo }<br> 
		
		sOne에 담긴 값과 "안녕" 이 일치합니까? ${sOne == "안녕"} 또는 ${sOne eq '안녕'}<br>
		<!-- EL에서 문자열 리터럴 제시시 홑따음표 쌍따음표 둘다 가능 -->
	</p>
	
	<h3>4.객체가 null인지 또는 list가 비어있는지 확인하는 연산</h3>
	<p>
		*EL연산 <br>
		pOne이 null입니까 ? : ${pOne == null } 또는 ${ empty pOne } <br>
		pTwo이 null입니까 ? : ${pTwo == null } 또는 ${ empty pTwo } <br>
		pOne이 null이 아닙니까? : ${pOne != null } 또는 ${not empty pOne }<br>
		
		lOne이 비어있습니까? :  ${empty lOne } <br>
		lTwo가 비어있습니까? :  ${empty lTwo } <br>
	</p>
	
	<hr>
	
	<h3>5.논리 연산자</h3>
	<p>
		*EL연산 <br>
		AND 연산 ${true && true } 또는 ${true and true }<br>
		OR 연산 ${true || false } 또는 ${true or false }<br>
	</p>

	<hr>
	
	<p>
	 * EL 연산에서 배운 키워드로만 사용해볼것 <br>
	 big이 small보다 크고 lOne이 비어있습니까? : ${big gt small and empty lOne } <br>
	 big과 small의 곱은 4의 배수입니까? : ${big*small mod 4 eq 0 }<br>
	 lTwo가 비어있지 않거나 또는 sOne에 담긴 값이 "안녕하세요"와 일치합니까? ${not empty lTwo or sOne eq '안녕하세요'} <br>
	</p>
	
	<br>
	<a href="/actionProject">인덱스로</a>
</body>
</html>