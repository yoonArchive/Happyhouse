<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../layout/head.jsp" %>
    <script>
    	$(function(){
    		var isAvailableId = false;
        	// 아이디 중복검사
        	$("#userId").keyup(function () {
        		let userId = $("#userId").val();
        		if(userId.length < 5 || userId.length > 20) {
        			$("#idResult").text("아이디는 5자이상 20자이하입니다.").removeClass('text-primary').removeClass('text-danger').addClass('text-dark');
        			isAvailableId = false;
        		} else {
	                $.ajax({
	                	url: 'http://localhost:8080/user/idCheck',
	                	data: {'userId': userId},
	                  	type: 'GET',
	                  	success: function () {
	                  		$("#idResult").text("사용 가능한 아이디입니다.").removeClass('text-dark').removeClass('text-danger').addClass('text-primary');
	                  		isAvailableId = true;                  	
	                  	}, 
	                  	error: function() {
	                  		$("#idResult").text("이미 사용중인 아이디입니다.").removeClass('text-dark').removeClass('text-primary').addClass('text-danger');
                    		isAvailableId = false;
	                  	}
					});
        		}
			}); 
    			
    		// 회원가입 버튼 클릭 이벤트 리스너
			$("#registBtn").click(function(){	
				if(!$("#userId").val()){
					alert("아이디를 입력해주세요.");
					return;
				}else if(!isAvailableId){
					alert("다른 아이디를 입력해주세요.");
					return;
				}else if(!$("#userPwd").val()){
					alert("비밀번호를 입력해주세요.");
					return;
				}else if ($("#userPwd").val() != $("#userPwd-check").val()) {
                    alert("비밀번호가 일치하지 않습니다.");
                    return;
                }else if(!$("#userName").val()){
					alert("이름을 입력해주세요.");
					return;
				}else if(!$("#email").val()){
					alert("이메일을 입력해주세요.");
					return;
				}else if(!$("#phone").val()){
					alert("연락처를 입력해주세요.");
					return;
				}	
				let userId=$("#userId").val();
				let userPwd=$("#userPwd").val();
				let userName=$("#userName").val();
				let email=$("#email").val();
				let phone=$("#phone").val();	
				let signupInfo= JSON.stringify({
					userId, userPwd, userName, email, phone
				})
				$.ajax({
					url:"http://localhost:8080/user/signup/",
					type:"POST",
					data:signupInfo,
					contentType:"application/json",
					success:function(){
						alert("회원가입 성공")
						location.href = "${root}/login";
					},
					error:function(xhr, status, error){
						alert("회원가입 실패")
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
        <h1>Sign Up</h1>
        <div class="form">
            <form>
                아이디 <input id="userId" name="userId" type="text" placeholder="id"/>
                <div id="idResult" class="mb-3"></div>
                비밀번호 <input id="userPwd" name="userPwd" type="password" placeholder="password"/>
                비밀번호 확인 <input id="userPwd-check" name="userPwd-check" type="password" placeholder="password"/>
                <div id="pwdCheckResult" class="mt-1"></div>
                이름 <input id="userName" name="userName" type="text" placeholder="name"/>
                이메일 <input id="email" name="email" type="email" placeholder="user@happyhouse.com"/>
                연락처<input id="phone" name="phone" type="tel" placeholder="010-0000-0000"/>
                <button type="button" class="btn btn-primary" id="registBtn">회원가입</button>
                <button onclick="location.href='/'" type="button">뒤로가기</button>
            </form>
        </div>
    </div>
</main>

<%@ include file="../layout/footer.jsp" %>
</body>
</html>
