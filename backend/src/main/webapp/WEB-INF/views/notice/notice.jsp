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
                <span class="main-top__text">행복한 우리 집</span>
            </div>
        </div>
        <div class="main-top__background"><b>공지사항</b></div>
    </section>
    <section class="container form-save">
        <div class="form">
            <form class="register-form">
                <div class="notice-title">
                    <p>제목</p>
                    <input id="title" name="title" type="text" placeholder="제목을 입력하세요."/>
                </div>
                <div class="notice-title">
                    <p>작성자</p>
                    <input id="author" name="author" type="text" readonly/>
                </div>
                <div class="notice-title">
                    <p>작성일자</p>
                    <input id="date" name="date" type="text" readonly/>
                </div>
                <div class="notice-content">
                    <p>본문</p>
                    <textarea id="content" name="content" placeholder="내용을 입력하세요."/></textarea>
                </div>
                <div class="notice-form-button">
                    <button id="btn-update" class="btn-primary btn-sm" onclick="update()" type="button">수정</button>
                    <button id="btn-delete" class="btn-primary btn-sm" onclick="remove()" type="button">삭제</button>
                    <button id="btn-back" class="btn-default btn-sm" onclick="history.back()" type="button">뒤로가기</button>
                </div>
            </form>
        </div>
    </section>
</main>
<%@ include file="../layout/footer.jsp" %>
</body>
</html>