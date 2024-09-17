<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.board.model.vo.*"%>
<%
	Board b = (Board)request.getAttribute("board");
	System.out.println("test중 category: "+b.getCategory());
	Attachment at = (Attachment)request.getAttribute("attachment");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 이게 cdn 바뀐 거임 -->
<script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>




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
	        	<!-- get방식으로 처리 -->
	        </div>
        <%} %>
        
        <div id="reply-area">
        	<table align="center">
        		<thead>
        		<%if(loginUser != null) {%>
        			<tr>
        				<th>댓글작성</th>
        				<td><textarea id="reply" rows="3" cols="50" style="resize:none"></textarea></td>
        				<td><button onclick="insertReply();">댓글등록</button></td>
        			</tr>
        		<%} else{ %>
        		
        			<tr>
        				<th>댓글작성</th>
        				<td><textarea rows="3" cols="50" style="resize:none" disabled>로그인 후 이용 가능한 서비스입니다.</textarea></td>
        				<td><button onclick="insertReply()">로그인했다치는버튼</button></td>
        			</tr>
        		
        		
        		<%} %>
        		</thead>
        		<tbody  id="replyList">
        			<tr >
        			<!--  댓글 목록이 여기에 추가됨 -->
        				<td>admin</td>
        				<td>강쥐가 귀엽네요</td>
        				<td>2023/04/13</td>
        						
        			</tr>
        			
        		
        		</tbody>
        	</table>
        	
        	<br><br>
        	
        	<Script>
        		function insertReply(){
        			//댓글 삽입
        			//게시글 번호 (들고가야함)
        			//성공시에는 댓글 리스트 조회함수 실행 후 textarea비워주기
					//insert근데 새로고침해야함.
        			$.ajax({
        			
        				url: "insert.rp", 
        				data: {
        					currentBoardNo:  <%=b.getBoardNo()%>,
        					replyContent :$("#reply").val(),
        					replyWriter: "<%=b.getBoardWriter()%>"
       
        					
        				},
        				type:"get", 
        				success: function(list){
        					
        					console.log(list);
        					
        					 // 댓글 리스트 조회 함수 실행 후 textarea 비우기
        				    // selectReplyList();
        				    // $("#reply").val("");
        					
        				}, 
        				error: function(){
        					console.log("통신실패")
        				}
        			});
        		
        		};
        		
        		

                $(document).ready(function() {
           		 function selectReplyList(){
                //댓글 목록 조회
                //조회해온 데이터를 tdody에 tr로 출력해주기(반복문)
                
                <%if(loginUser != null ){%> 
                  $("#replyList").show();
                <%} else{%>
                  $("#replyList").hide();
                <%}%>
                
                $.ajax({
                    url:"select.rp",
                    data:{currentBoardNo:  <%=b.getBoardNo()%>},
                    type:"get",
                    success: function(list){
                        //console.log(list);
                        
                        var replyList = $("#replyList");
                        var str = "";
                        //댓글목록 순회하며 HTML태그 추가
                        //size로 하면 안됨 
                       	for( var i =0; i<list.length; i++){
                        	str+=
                        	"<tr>"
                        	+"<td>"+list[i].replyWriterS+"</td>"
                        	+"<td>"+list[i].replyContent+"</td>"
                        	+"<td>"+list[i].createDate+"</td>"
                        	+"</tr>";
                        }
                        $("#replyList").html(str);
                        
                    },
                    error:function(){
                        console.log("통신실패")
                    }
                });
            }
            
            // 페이지 로딩 시 댓글 목록 보여주기
            	selectReplyList();
        		});

        		
        	</Script>
        
        </div>
        
      
        

    </div>
</body>
</html>