<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>





<!-- 이게 cdn 바뀐 거임 -->
<script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>







<style>
.outer {
	background-color: black;
	color: white;
	width: 1000px;
	margin: auto;
	margin-top: 50px;
}

#enroll-form table {
	margin: auto;
}

#enroll-form input {
	margin: 5px;
}
</style>
</head>
<body>
	
	<%@ include file="../common/menubar.jsp" %>
	<!-- ../ : 상위폴더로(현재 폴더벗어나기-상위로 한번) -->
	<div class="outer">
	
		<br>
		<h2 align="center">회원 가입</h2>
		<!-- menubar에 선언해놓은 변수 사용 가능  -->
		<form action="<%=contextPath %>/insert.me" method="post" id="enroll-form">

			<!-- table>(tr>td*3)*8 -->
			<table>
				<tr>
					<td>* 아이디</td>
					<td><input type="text" name="userId" maxlength="12" required></td>
					<td><button type="button" onclick="idCheck();">중복확인</button></td>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" name="userPwd" maxlength="15"
						required></td>
					<td></td>
				</tr>
				<tr>
					<td>* 비밀번호 확인</td>
					<td><input type="password" id="chkPwd" maxlength="15" required></td>
					<td></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="userName" required></td>
					<td></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="phone" placeholder="-포함해서 입력"></td>
					<td></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email"></td>
					<td></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="address"></td>
					<td></td>
				</tr>
				<tr>
					<td>관심분야</td>
					<td colspan="2">
						<!-- (input[type=checkbox name=interest id= value=]+label)*6 --> <input
						type="checkbox" name="interest" id="sports" value="운동"> <label
						for="sports">운동</label> <input type="checkbox" name="interest"
						id="movies" value="영화감상"> <label for="movies">영화감상</label>
						<input type="checkbox" name="interest" id="board" value="보드타기">
						<label for="board">보드타기</label> <br> <input type="checkbox"
						name="interest" id="cook" value="요리"> <label for="cook">요리</label>
						<input type="checkbox" name="interest" id="game" value="게임">
						<label for="game">게임</label> <input type="checkbox"
						name="interest" id="book" value="독서"> <label for="book">독서</label>
					</td>
				</tr>
			</table>

			<br>
			<br>

			<div align="center">
				<button type="submit" disabled>회원가입</button>
				<button type="reset">초기화</button>
			</div>

		</form>
		
		<script>
			//name값이 name=userId 
			function idCheck(){
				//아이디에 입력한 값을 데이터베이스에 저장된 아이디와 비교하여 중복인지 판별하기
				//console.log($("#enroll-form input[name=userId]").val());
				
				var $checkId = $("#enroll-form input[name=userId]");
				
				//응답데이터를 NNNNN : 사용할 수 없음 |NNNNY : 사용 가능 
				
				//사용가능할때는 회원가입버튼 활성화 시켜주기 
				
				//MemberIdCheckController.java 여기로 연결
				$.ajax({
					
					url:"idCheck.me", 
					data:{
						checkId: $checkId.val()}, 
					type:"get", 
					//결과값을 서블릿에서 받을 거니까 매개변수 result 미리넣기
					success: function(result){
						
						//result를 서블릿에서 받았으니까 그에 맞게 조건문을 통해 판별하면 되는거임.
						
						if(result=="NNNNN"){
							//사용불가
							alert("이미 존재하거나탈퇴한 유저의 아이디입니다.")
							$checkId.focus(); //focus or select 쓰기
						} else{
							//confirm 같은 거 넣어서 
							if(confirm("사용 가능한 아이디입니다. 사용하시겠습니까?")){
								$("#enroll-form button[type=submit]").removeAttr("disabled");//diabled 풀어주는 작업
								$checkId.attr("readonly",true); //그 다음 아이디값을 변경하지못하도록
							} else{
								$checkId.focus();
							}
						} 
						
					},
					error: function(){
						console.log("ajax통신 실패");
					}, 
					complete: function(){
						console.log("성공실패 여부에 상관없이 실행됨");
					}
					
					
				});
				
				
			};
		</script>


		<br>
		<br>
		<br>
	</div>
</body>
</html>