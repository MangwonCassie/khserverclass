<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	#enroll-form>table{
		border: 1px solid white;
	}
	
	
	#enroll-form input, textarea{
		width: 100%;
		box-sizing: border-box;
	}
</style>


</head>
<body>

	<%@include file="../common/menubar.jsp" %>
	
	
	<div class="outer" >
		<br>
		<h2 align="center">사진 게시글 작성하기</h2>
		<br>
		
		<form action="<%=contextPath%>/insert.ph" method="post" id="enroll-form" enctype="multipart/form-data">
		
		<!-- 로그인됐었을 떄만 글작성가능 -->
		
			<input type="hidden" name="userNo" value="<%=loginUser.getUserNo()%>">
	
			<table align="center">
				<tr>
					<th width="100">제목</th>
					<td colspan="3"><input type="text" name="title" required></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<!-- none옆에 ; -->
					<td colspan="3"><textarea name="content" rows="10" cols="30" required style="resize:none;"></textarea></td>
				</tr>
				
				
				<tr>
					<th>대표이미지</th>
					<td colspan="3" align="center">
						<img width="250" height="170" id="titleImg">
					</td>
				</tr>
				
				
				<tr>
					<th>상세이미지</th>
					<td><img id="contentImg1" width="150" height="120"></td>
					<td><img id="contentImg2" width="150" height="120"></td>
					<td><img id="contentImg3" width="150" height="120"></td>
				</tr>
			
			</table>
			
	
			<br><br>
			<!-- onchange는 변경됐을때 이벤트 발생 -->
			
			
			<div align="center">
				<button type="submit">작성하기</button>		
			
			</div>
				<br><br>
		</form>
		
	
		
		
	</div>
</body>
</html>