<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../layout/head.jsp" %>
    <link rel="stylesheet" href="../css/main.css"/>
    <script>
        $(function () {
            getSiList();

            $("#si").change(function () {
                getGuList($("#si").value);
            });

            $("#gu").change(function () {
                getDongList($("#gu").value);
            });

            $("#dong").change(function () {
                getTradeList($("#si").val(), $("#gu").val(), $("#dong").val());
            });
        });

        function getTradeList(si, gu, dong) {
            $.ajax({
                url: "http://localhost:8080/trade/list?si=" + si + "&gu=" + gu + "&dong=" + dong,
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

        function getSiList() {
            $.ajax({
                url: "http://localhost:8080/trade/si",
                type: "get"
            }).done(function (result) {
                let option = '<option value="">시/도</option>';
                for (let value of result) {
                    option += `<option value="${"${value}"}">${"${value}"}</option>`;
                }
                $("#si").empty().append(option);
            }).fail(function (error) {
                alert(JSON.parse(error));
            });
        }

        function getGuList(si) {
            $.ajax({
                url: "http://localhost:8080/trade/gu?si=" + si,
                type: "get"
            }).done(function (result) {
                let option = '<option value="">구/군</option>';
                for (let value of result) {
                    option += `<option value="${"${value}"}">${"${value}"}</option>`;
                }
                $("#gu").empty().append(option);
                $("#dong").empty().append('<option value="">동</option>');
            }).fail(function (error) {
                alert(JSON.parse(error));
            });
        }

        function getDongList(gu) {
            $.ajax({
                url: "http://localhost:8080/trade/dong?gu=" + gu,
                type: "get"
            }).done(function (result) {
                let option = '<option value="">동</option>';
                for (let value of result) {
                    option += `<option value="${"${value}"}">${"${value}"}</option>`;
                }
                $("#dong").empty().append(option);
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
                <select name="si" id="si" onchange="getGuList(this.value)">
                    <option value="">시/도</option>
                </select>
                <select name="gu" id="gu" onchange="getDongList(this.value)">
                    <option value="">구/군</option>
                </select>
                <select name="dong" id="dong" onchange="">
                    <option value="">동</option>
                </select>
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