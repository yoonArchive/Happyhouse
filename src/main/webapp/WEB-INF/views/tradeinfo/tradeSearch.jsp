<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../layout/head.jsp" %>
    <link rel="stylesheet" href="../css/main.css"/>
    <script>
        function search() {
            let action = $("#action").val();
            let keyword = $("#search").val();
            let url = "http://localhost:8080/trade/search";
            if (action === "selectByAptName") {
                url += "/apt?aptName=" + keyword;
            } else {
                url += "/dong?dongName=" + keyword;
            }
            getList(url);
        }

        function getList(url) {
            $.ajax({
                url: url,
                type: "get"
            }).done(function (result) {
                let list = '';
                for (let apt of result) {
                    list += `
                    <tr>
                        <td class="aptName">${"${apt.aptName}"}</td>
                        <td class="dongName">${"${apt.dongName}"}</td>
                        <td class="buildYear">${"${apt.buildYear}"}</td>
                        <td class="area">${"${apt.area}"}</td>
                        <td class="floor">${"${apt.floor}"}</td>
                        <td class="dealAmount">${"${apt.dealAmount}"}</td>
                        <td class="type">${"${apt.type}"}</td>
                    </tr>
                    `;
                }
                $("#aptinfo").empty().append(list);
            }).fail(function (error) {
                alert(JSON.parse(error));
            });
        }
    </script>
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
        <!-- 리스트박스 -->
        <div class="main-top__search">
            <div class="main-top__search-wrapper">
                <div>
                    <select name="action" id="action">
                        <option value="selectByDongName">동 검색</option>
                        <option value="selectByAptName">아파트 이름 검색</option>
                    </select>
                    <input type="text" id="search" name="search" placeholder="검색어를 입력하세요.">
                    <input type="submit" value="검색" onclick="search()">
                </div>
            </div>
        </div>
    </section>

    <section class="main-data container table-responsive" style="height: 800px">
        <table class="main-table table table-hover">
            <thead class="thead-strong">
            <tr class="main-table__header">
                <th>아파트</th>
                <th>동</th>
                <th>건축년도</th>
                <th>전용면적</th>
                <th>층</th>
                <th>거래금액</th>
                <th>거래유형</th>
            </tr>
            </thead>
            <tbody id="aptinfo" class="main-table__body"></tbody>
        </table>
    </section>


</main>

<%@ include file="../layout/footer.jsp" %>
</body>
</html>