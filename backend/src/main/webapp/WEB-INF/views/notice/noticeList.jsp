<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../layout/head.jsp" %>
</head>
<body>
<%@ include file="../layout/header.jsp" %>

<main>
    <!-- 해피 하우스 -->
    <section class="main-top">
        <div class="main-top__header">
            <div class="main-top__header-wrapper">
                <h1 class="main-top__title">HAPPY HOUSE</h1>
                <!-- <hr /> -->
                <span class="main-top__text">행복한 우리 집</span>
            </div>
        </div>
    </section>

    <section class="main-data container table-responsive" style="height: 800px">
        <div class="btn-notice-save">
            <c:if test="${not empty userInfo}">
                <button class="btn-primary btn-sm" style="float: right"
                        onclick="location.href='http://localhost:8080/notice/regist'">글쓰기
                </button>
            </c:if>
        </div>
        <!-- 목록 출력 영역 -->
        <div class="table-responsive">
            <table class="table table-hover">
                <thead class="thead-strong">
                <tr class="main-table__header">
                    <th>게시글번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(notice, index) in notices">
                    <td>{{ index + 1 }}</td>
                    <td>{{ notice.title }}</td>
                    <td>{{ notice.author }}</td>
                    <td>{{ notice.createDate }}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </section>
</main>
<script>
    new Vue({
        el: "main",
        data: {
            notices: []
        },
        created: function () {
            $.ajax({
                url: "http://localhost:8080/notice/list/1",
                type: "get",
                success: (result) => {
                    this.notices = result;
                },
                error: function () {
                    alert("불러오기 실패");
                }
            })
        }
    })
</script>

<%@ include file="../layout/footer.jsp" %>
</body>
</html>