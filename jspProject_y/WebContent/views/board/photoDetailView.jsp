<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList, com.kh.board.model.vo.* "%>
    
<%
Board b = (Board)request.getAttribute("b");
ArrayList<Attachment> list = (ArrayList<Attachment>)request.getAttribute("list");
System.out.println("/jsp"+list.get(0).getFilePath()+list.get(0).getChangeName());
%>    
    
    
<!-- 여울 -->
    
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
	
	<!-- form태그없애고 div로 바꿔  -->
	<div class="outer" >
		<br>
		<h2 align="center">사진 게시글 상세보기</h2>
		<br>
		
		<div>
		<!-- 로그인됐었을 떄만 글작성가능 -->
		
			<input type="hidden" name="userNo" value="<%=loginUser.getUserNo()%>">
	
			<table align="center">
				<tr>
					<th width="100">제목</th>
					<td colspan="3"><%=b.getBoardTitle() %></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<!-- none옆에 ; -->
					<td colspan="3"><p><%=b.getBoardContent() %></p></td>
				</tr>
				
				<tr>
					<th>작성자</th>
					<td><%=b.getBoardWriter() %></td>
					<th>작성일</th>
					<td><%=b.getCreateDate()%></td>
				
				</tr>
				
				
				<tr>
					<th>대표이미지</th>
					<td colspan="3" align="center">
						<img src="<%=contextPath+list.get(0).getFilePath()+list.get(0).getChangeName()%>" width="250" height="170" id="titleImg">
					</td>
				</tr>
				
				
				<tr>
					<th>상세이미지</th>
					<!-- 반복문 통해서 담기 -->
					<%for(int i =1; i<list.size(); i++){ %>
						<td><img src="<%=contextPath+list.get(i).getFilePath()+list.get(i).getChangeName() %>" id="contentImg1" width="150" height="120"></td>
					<%} %>
				</tr>
			
			</table>
			<br><br><br>
			
		
			
			
		</div>
		
		
		
	</div>
</body>
</html>