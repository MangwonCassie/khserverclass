<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>* EL (Expression Language) 표현언어</h1>
	
	<p>
		기존에 사용했던 표현식(출력식) &lt;%= name %&gt; 와 같이 <br>
		JSP 상에서 표현하고자 하는 값을 \${name}의 형식으로 표현해서 작성하는 것
	</p>
	
	<h3>1.EL 기본 구문에 대해 알아보자.</h3>
	<a href="/actionProject/el.do">01_EL</a>
	
	<h3>2.EL 연산자에 대해 알아보자.</h3>
	<a href="/actionProject/operation.do">02_EL의 연산자</a>
	
	<hr>
	
	<!-- 
		JSP를 이루는 구성인자
		1. JSP 스크립팅 원소 : JSP 페이지에서 자바코드를 직접 기술할 수 있게 하는 기술
			ex)선언문,스크립틀릿,표현식
		2. 지시어 : JSP페이지 정보에 대한 내용을 표현한다던지 또 다른 페이지를 포함할 때 사용
		 	ex)page지시어, include지시어,taglib 지시어(라이브러리 추가시)
		3.JSP 액션 태그 : XML기술을 이용하여 기존의 jsp문법을 확장하는 기술을 제공하는 태그
			-표준액션 태그 (Standard Action tag) : JSP 페이지에서 바로 사용가능(별도의 연동필요 X)
					표준액션 태그는 모든 태그명 앞에 jsp: 라는 접두어가 붙는다.
		 	-커스텀 액션 태그 (custom Action tag) : JSP 페이지에서 바로 사용 불가능(별도의 연동 필요)
		 			커스텀액션 태그는 모든 태그명 앞에 jsp: 외의 다른 접두어가 붙는다
		 			제공되고있는 대표적인 라이브러리가 있음 JSTL
	 -->
	<h1> *JSP Action Tag</h1>
	<p>
		XML기술을 이용하여 기존의 JSP 문법을 확장시키는 기술을 제공하는 태그
	</p>
	
	<h3>1. 표준액션 태그</h3>
	<p>
		JSP페이지에서 별도의 라이브러리 연동없이 사용가능하다. <br>
		태그앞에 jsp: 접두어가 붙는다.
	</p>	
	
	<a href="views/03_include.jsp">jsp:include</a> <br>
	<a href="views/04_forward.jsp">jsp:forward</a> <br>
	
	<h3>2. 커스텀액션 태그</h3>
	<a href="views/05_customAction.jsp">JSTL</a> <br>
	
</body>
</html>