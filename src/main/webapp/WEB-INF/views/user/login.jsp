<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../layout/head.jsp" %>
    <link rel="stylesheet" href="../css/main.css"/>
    <script>
		$(function(){
			// 로그인 버튼 클릭 이벤트 리스너
			$("#loginBtn").click(function(){
				if (!$("#userId").val()) {
	                alert("아이디를 입력해주세요.");
	               	return;
				}
				else if (!$("#userPwd").val()){
					alert("비밀번호를 입력해주세요.");
					return;
				}           
				let userId=$("#userId").val();
				let userPwd=$("#userPwd").val();
				let loginInfo= JSON.stringify({
					userId, userPwd
				})
				$.ajax({
					url:"http://localhost:8080/user/login",
					type:"POST",
					data:loginInfo,
					contentType:"application/json",
					success:function(){
						alert("로그인 성공")
						location.href = "/";
					},
					error:function(xhr, status, error){
						alert("아이디나 비밀번호를 다시 확인해주세요.")
						console.log(xhr)
					}
				})
			})		
		});
	</script>
    
</head>
<body>
<%@ include file="../layout/header.jsp" %>
<main class="container">
    <div class="login-page">
        <h1>Login</h1>
        <div class="form">
            <form>
                아이디<input id="userId" name="userId" type="text" placeholder="id"/>
                비밀번호<input id="userPwd" name="userPwd" type="password" placeholder="password"/>
                <button type="button" id="loginBtn" class="btn btn-outline-warning">로그인</button>
                <button type="button" id="findPwdBtn" onclick="location.href='/find/pw'" class="btn btn-outline-warning">비밀번호 찾기</button>
                <button onclick="location.href='/'" type="button">뒤로가기</button>
            </form>
        </div>
    </div>
</main>
<%@ include file="../layout/footer.jsp" %>
</body>
</html>