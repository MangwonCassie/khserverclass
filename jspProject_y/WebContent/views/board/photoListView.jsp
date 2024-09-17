<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList,com.kh.board.model.vo.Board, com.kh.board.model.vo.Attachment " %>
    
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	ArrayList<Attachment> aList = (ArrayList<Attachment>)request.getAttribute("alist");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	.list-area{
		width: 760px;
		margin: auto;
	}

	.thumbnail{
		border: 1px solid white;
		width: 220px;
		display: inline-block;
		margin: 14px;
		
	}
	
	.thumbnail:hover{
		cursor: pointer;
		opacity: 0.7;
	}
	
</style>
<!-- inline-block을 해야 옆에 나열됨 -->


</head>
<body>


	<%@include file="../common/menubar.jsp" %>
	
	<div class="outer">
	
		<h2 align="center">사진 게시판</h2>

		<br><br>
		
		<%if(loginUser != null ) {%>
		
		<div align="center">
		
			<a href="<%=contextPath%>/insert.ph" class="btn btn-info">글작성</a>
			<br><br>
		</div>
		
		<%} %>
		
		<%if(list.isEmpty()){ %>
		<div><span>존재하는 사진게시물이 없습니다.</span></div>
		
		<%} else{ %>
		
	
			
		
				<div class="list-area">
				
				<!-- 썸네일을 반복하는거였는데..FOR문을 LIST-AREA 바깥으로 함 그래서 페이지가 뒤틀리는거임 썸네일을 반복할 수 있도록  -->
				<% for(Board b: list) {%>
				
					<div class="thumbnail" align="center">
					
					<!-- 위치 그냥 여기서 하면 되잖아.. -->
					<input type=hidden name="bno"value="<%=b.getBoardNo()%>">
					
					<!-- 여기 막 슬래쉬를 넣고..슬래쉬가 아니라 +임 자바니까  -->
					<img src="<%=contextPath + b.getTitleImg() %>" width="200px" height="150px" >
				
					<!-- /jsp -->
					<!-- b에 담아서 해야지!!! -->
						<p><%=b.getBoardNo()%><br>
							<%=b.getCount() %>
							
						</p>
					
					</div>
				
				
				
				<%} %>
				
				</div>
				
			<%} %>
		
	</div>
	

	
		<script>
		$(function(){
		    $(".thumbnail").click(function(){
		    	
		    	//이걸 못해서..2시간..name값...잘 하기 
		    	//console.log($(this).children("input[name=bno]").val());
		    	
		    	
		    	//var bno=  $(this).children("input").val();
		        //var bno = $(this).children("input").val();
		        //글번호 추출
		        //console.log($(this).children().eq(0).text());		
		        //var bno = $(this).children().eq(0).text();
		        
		        //iput 히든은 콘솔에 안 찍힘.
		        location.href='<%=contextPath%>/detail.ph?bno='+$(this).children("input[name=bno]").val();
		    
		    });
		});
		</script>
		

	
</body>
</html>