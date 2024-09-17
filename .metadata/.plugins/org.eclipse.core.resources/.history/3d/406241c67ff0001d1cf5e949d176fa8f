<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>1.formatNumber</h1>
	<p>
		숫자데이터의 포맷(형식) 지정 <br>
		-표현하고자 하는 숫자 데이터의 형식을 통화기호,%등 원하는 쓰임에 맞게 지정하는 태그 <br><br>
		
		&lt;fmt:formatNumber value="출력할값" groupingUsed="true/false"
				type="percent/currency" currencySymbol="$" /&gt; <br>
		
		(groupingUsed,type,currentSymbol들은 생략 가능한 속성)
	</p>
	
	<c:set var="num1" value="123456789"/>
	<c:set var="num2" value="0.75"/>
	<c:set var="num3" value="50000"/>
	
	그냥 출력 : ${num1} <br>
	세자리마다 구분하여 출력 : <fmt:formatNumber value="${num1}"/> <br>
	숫자 그대로 출력 : <fmt:formatNumber value="${num1}" groupingUsed="false"/><br>
	<!-- 
		formatNumber : 3자리마다 ,가 찍힌다 
		groupingUsed : 기본값은 true (,구분자 찍히게)
	 -->
	
	percent : <fmt:formatNumber value="${num2}" type="percent"/><br>
	<!-- 소수점을 백분율로 보여준다  -->
	
	currency : <fmt:formatNumber value="${num3}" type="currency"/> <br>
	<!--  
		통화(돈) 단위로 보여진다. 지금 사용하고 있는 컴퓨터의 로컬정보에 의해서 정해진다.
		groupingUsed 기본값이 true이기 때문에 3자리마다 ,구분도 되어있다.
	 -->
	
	currency $ : <fmt:formatNumber value="${num3}" type="currency"
											 currencySymbol="$"/>
	<!-- 
		currencySymbol="$" : 통화기호 문자의 종류 지정
	 -->				
	
	<h3>2. formatDate</h3>
	<p>
		날짜 및 시간 데이터의 포멧(형식) 지정<br>
		단 java.util.Date 객체를 사용해야한다. <br>	
	</p>
	
	<c:set var="current" value="<%=new java.util.Date()%>"/>
	
	그냥 출력 : ${current} <br>
	
	<ul>
		<li>
			현재 날짜 : <fmt:formatDate value="${current}" type="date"/>
			<!-- type 속성은 생략가능,생략시 기본값이 "date" 날짜를 출력 -->
		</li>
		<li>
			현재 시간 : <fmt:formatDate value="${current}" type="time"/>
			<!-- type :time 시간을 출력-->
		</li>
		<li>
			현재 날짜 및 시간 : <fmt:formatDate value="${current}" type="both"/>
			<!-- type : both 날짜,시간을 모두 출력-->
		</li>
		<li>
			medium : <fmt:formatDate value="${current}" 
						type="both" dateStyle="medium" timeStyle="medium"/>
			<!-- 기본 길이 형식 -->
		</li>
		<li>
			long : <fmt:formatDate value="${current}" 
						type="both" dateStyle="long" timeStyle="long"/>
		
		</li>
		<li>
			full : <fmt:formatDate value="${current}" 
						type="both" dateStyle="full" timeStyle="full"/>
		</li>
		<li>
			short : <fmt:formatDate value="${current}" 
						type="both" dateStyle="short" timeStyle="short"/>
		</li>
		<li>
			customizing : <fmt:formatDate value="${current}" 
						type="both" pattern="yyyy-MM-dd(E) a HH:mm:ss"/>
		</li> <!-- pattern 속성을 통해 형식을 지정할 수 있음 -->
	</ul>
	<!-- 
		-dateStyle과 timeStyle을 동일하게 맞출 필요 없음 
		-customizing에서 형식
		yyyy : 년도
		MM : 월
		dd : 일
		E : 요일
		a : 오전 / 오후
		HH : 시
		mm : 분
		ss : 초
	
	 -->
	<br>
	<a href="/actionProject">인덱스로</a>
	
</body>
</html>