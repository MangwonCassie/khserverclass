<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.board.model.vo.*"%>
<%
	Board b = (Board)request.getAttribute("board");
	Attachment at = (Attachment)request.getAttribute("attachment");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

    .outer table {border-color:white;}
</style>
</head>
<body>
	<%@ include file ="../common/menubar.jsp" %>

    <div class="outer">
        <br>
            <h2 align="center">일반게시판 상세보기</h2>
        <br>

        <table id="detail-area" align="center" border="1">

            <!-- (tr>th+td+th+td)*4 -->
            <tr>
                <th width="70">카테고리</th>
                <td width="70"><%=b.getCategory() %></td>
                <th width="70">제목</th>
                <td width="350"><%=b.getBoardTitle() %></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><%=b.getBoardWriter() %></td>
                <th>작성일</th>
                <td><%=b.getCreateDate() %></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p style="height:200px;"><%=b.getBoardContent() %></p>
                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">
                    	<!-- 첨부파일이 없을 경우 : 첨부파일이 없습니다. -->
                    	<%if(at == null) {%>
                    		첨부파일이 없습니다.
                    	<%}else{ %>
	                    	<!-- 첨부파일이 있을 경우 -->
	                    	<a href="<%=contextPath + at.getFilePath()+"/"+at.getChangeName()%>" download="<%=at.getOriginName()%>"><%=at.getOriginName() %></a>
                    	<%} %>
                </td>
                
            </tr>

        </table>
        <br>
        <br>
        <%if(loginUser != null && loginUser.getUserId().equals(b.getBoardWriter())){ %>
	        <div align="center">
	        	<button onclick="location.href='<%=contextPath%>/update.bo?bno=<%=b.getBoardNo()%>'" class="btn btn-info">수정하기</button>
	        	<button onclick="location.href='<%=contextPath%>/delete.bo?bno=<%=b.getBoardNo()%>'" class="btn btn-danger">삭제하기</button>
	        </div>
        <%} %>
        
        
        <br>
        <div id="reply-area">
        	<table border="1" align="center">
				<thead>
				<%if(loginUser != null){ %>
					<tr>
						<th>댓글작성</th>
						<td><textarea id="replyContent" rows="3" cols="50" style="resize:none"></textarea></td>
						<td><button onclick="insertReply();">댓글등록</button></td>
					</tr>
				<%}else{ %>
					<tr>
						<th>댓글작성</th>	
						<td>
							<textarea  rows="3" cols="50" style="resize:none" readonly>로그인 후 이용 가능한 서비스입니다.</textarea>
						</td>					
						<td><button onclick="insertReply();">댓글등록</button></td>
					</tr>
				<%} %>	
				</thead>
				<tbody>
<!-- 					<tr> -->
<!-- 						<td>admin</td> -->
<!-- 						<td>강쥐가 귀엽네요 납치하고싶어요</td> -->
<!-- 						<td>2023/04/13</td> -->
<!-- 					</tr> -->
				</tbody>
        	</table>
        	<br><br>
        	
        	<script>
        		$(function(){
        			selectReplyList();
        		});
        	
        		function insertReply(){
        			//댓글 삽입 
        			//게시글 번호 필요 
        			//성공시에는 댓글 리스트 조회함수 실행 후 textarea 비워주기 
        			
        			$.ajax({
        				url : "insertReply.bo",
        				data : {
        					content : $("#replyContent").val(),
        					bno : <%=b.getBoardNo()%>
        				},
        				type : "post",
        				success : function(result){
        					if(result>0){
        						alert("댓글 작성 완료");
        						selectReplyList(); //댓글리스트 갱신
        						$("#replyContent").val("");
        					}
        				},
        				error : function(){
        					console.log("댓글 작성 통신 실패");
        				}
        			});
        		}
        		
        		function selectReplyList(){
        			//댓글 목록 조회 
        			//조회해온 데이터를 tbody에 tr로 출력해주기
        			$.ajax({
        				url : "selectReply.bo",
        				data : {bno : <%=b.getBoardNo()%>},
        				success : function(rlist){
        					
        					var result = "";
        					
        					for(var i in rlist){
        						result += "<tr>"
        								+"<td>"+rlist[i].replyWriter+"</td>"
        								+"<td>"+rlist[i].replyContent+"</td>"
        								+"<td>"+rlist[i].createDate+"</td>"
        								+"</tr>";
        					}
        					
        					$("#reply-area tbody").html(result);
        					
        				},
        				error : function(){
        					console.log("댓글조회 실패")
        				}
        			});
        			
        			
        			
        		}
        	
        	</script>
        	
        	
        
        </div>
        
        
        

    </div>
</body>
</html>