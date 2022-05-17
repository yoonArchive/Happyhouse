<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../layout/head.jsp" %>
</head>
<body>
<%@ include file="../layout/header.jsp" %>
<main>
    <section class="main-top">
        <div class="main-top__header">
            <div class="main-top__header-wrapper">
                <h1 class="main-top__title">HAPPY HOUSE</h1>
                <!-- <hr /> -->
                <span class="main-top__text">행복한 우리 집</span>
            </div>
        </div>
        <div class="main-top__background"><b>공지사항 등록</b></div>
    </section>
    <section class="container form-save">
        <div class="form">
            <form class="register-form">
                <div class="notice-title">
                    <p>제목</p>
                    <input v-model="title" name="title" type="text" placeholder="제목을 입력하세요."/>
                </div>
                <div class="notice-content">
                    <p>본문</p>
                    <textarea v-model="content" name="content" placeholder="내용을 입력하세요."/></textarea>
                </div>
                <div class="notice-form-button">
                    <button class="btn-primary btn-sm" @click="save">저장</button>
                    <button class="btn-default btn-sm" onclick="history.back()" type="button">취소</button>
                </div>
            </form>
        </div>
    </section>
</main>
<script>
    new Vue({
        el: "main",
        data: {
            title: "",
            content: ""
        },
        methods: {
            save() {
                let data = {
                    title: this.title,
                    content: this.content,
                    author: `${userInfo.userName}`
                }
                console.log(data);
                $.ajax({
                    url: "http://localhost:8080/notice",
                    type: "post",
                    data: JSON.stringify(data),
                    contentType: "application/json",
                    success: function () {
                        alert("등록 성공");
                        location.href = "http://localhost:8080/notice/list";
                    },
                    error: function () {
                        alert("등록 실패");
                    }
                })
            }
        }
    })
</script>
<%@ include file="../layout/footer.jsp" %>
</body>
</html>