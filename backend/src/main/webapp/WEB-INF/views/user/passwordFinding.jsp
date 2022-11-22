<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../layout/head.jsp" %>
    <script>
		$(function(){
			// 비밀번호 찾기 버튼 클릭 이벤트 리스너
			$("#findPwdBtn").click(function(){	
				let userId=$("#userId").val();
				let userName=$("#userName").val();
				let phone=$("#phone").val();
				$.ajax({
					url:"http://localhost:8080/user/findPwd?userId="+userId+"&userName="+userName+"&phone="+phone,
					type:"GET",
					success:function(result){
						alert("비밀번호는 "+result+" 입니다.")
						location.href = "${root}/login";
					},
					error:function(xhr, status, error){
						alert("비밀번호 찾기 실패")
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
        <h1>비밀번호 찾기</h1>
        <div class="form">
            <form class="register-form">
                아이디<input id="userId" name="userId" type="text" placeholder="id"/>
                이름<input id="userName" name="userName" type="text" placeholder="name"/>
                연락처<input id="phone" name="phone" type="tel" placeholder="010-0000-0000"/>
                <button type="button" id="findPwdBtn" class="btn btn-outline-primary">비밀번호 찾기</button>
                <button onclick="location.href='/login'" type="button">뒤로가기</button>
            </form>
        </div>
    </div>
</main>
<%@ include file="../layout/footer.jsp" %>
</body>
</html>
