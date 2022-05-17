<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../layout/head.jsp" %>
    <script>
		$(function(){
			$("#userUpdateBtn").hide();		
			// 수정하기 버튼 클릭 이벤트 리스너
			$("#goUserUpdateBtn").click(function(){
				$("#goUserUpdateBtn").hide();
				$("#userUpdateBtn").show();
				$('#userPwd').attr('readonly', false);
				$('#userPwd-check').attr('readonly', false);
				$('#userName').attr('readonly', false);
				$('#email').attr('readonly', false);
				$('#phone').attr('readonly', false);
			})
			
			// 수정완료 버튼 클릭 이벤트 리스너
			$("#userUpdateBtn").click(function(){
				if ($("#userPwd").val() != $("#userPwd-check").val()) {
                    alert("비밀번호가 일치하지 않습니다.");
                    return;
                }			
				let userId=$("#userId").val();
				let userPwd=$("#userPwd").val();
				let userName=$("#userName").val();
				let email=$("#email").val();
				let phone=$("#phone").val();			
				let updateInfo= JSON.stringify({
					userId, userPwd, userName, email, phone
				})				
				$.ajax({
					url:"http://localhost:8080/user",
					type:"PUT",
					data:updateInfo,
					contentType:"application/json",
					success:function(){
						alert("회원정보 수정 성공")
						$("#userId").val("${userInfo.userId}"); 
						$("#userPwd").val("${userInfo.userPwd}"); 
						$("#userName").val("${userInfo.userName}"); 
						$("#email").val("${userInfo.email}"); 
						$("#phone").val("${userInfo.phone}"); 
						location.reload();
						$("#userUpdateBtn").hide();
						$("#goUserUpdateBtn").show();
					},
					error:function(xhr, status, error){
						alert("회원정보 수정 실패")
						console.log(xhr)
					}
				})
			})		
			//탈퇴 버튼 이벤트 등록
			$("#userDeleteBtn").click(function(){
              console.log("탈퇴 버튼 클릭");     
              let userId=$("#userId").val();
              
              if(confirm("정말 탈퇴하시겠습니까?")){
                  $.ajax({
                      url:"http://localhost:8080/user/"+userId,
                      type:"DELETE",
                      success:function(){
                          alert("탈퇴 완료");
                          location.href = "/";
                      },
                      error:function(){
                          alert("탈퇴 실패")
                      }
                  })
              }
          })		
		});		 
	</script>
</head>
<body>
<%@ include file="../layout/header.jsp" %>
<main class="container">
    <div class="login-page">
        <h1>MyPage</h1>
        <div class="form">
            <form>
                아이디<input id="userId" name="userId" type="text" value="${userInfo.userId}" readonly/>
                비밀번호 <input id="userPwd" name="userPwd" type="password" value="${userInfo.userPwd}" readonly/>
                비밀번호 확인 <input id="userPwd-check" name="userPwd-check" type="password" placeholder="password" readonly/>
                이름<input id="userName" name="userName" type="text"  value="${userInfo.userName}" readonly/>
                이메일<input id="email" name="email" type="email"  value="${userInfo.email}" readonly/>
                연락처<input id="phone" name="phone" type="tel"  value="${userInfo.phone}" readonly/>
                <button type="button" id="goUserUpdateBtn" class="btn btn-outline-warning">회원정보 수정</button>
                <button type="button" id="userUpdateBtn" class="btn btn-outline-warning" >수정 완료</button>
                <button type="button" id="userDeleteBtn" class="btn btn-outline-warning" >회원 탈퇴</button>
                <button onclick="location.href='/'" type="button">뒤로가기</button>
            </form>
        </div>
    </div>
</main>
<%@ include file="../layout/footer.jsp" %>
</body>
</html>
