<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="layout/head.jsp" %>
    <link rel="stylesheet" href="../../../resources/static/css/main.css"/>

    <!-- 카카오맵api -->
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d04b2199aeb68ea02851013d8441dd0d&libraries=services"></script>

    <script>
        let siInfo = [];
        let guInfo = [];    //구 정보 담을 변수
        let dongInfo = [];

        $(document).ready(function () {
            var mapContainer = document.getElementById('map'); // 지도를 표시할 div
            mapOption = {
                center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                level: 6 // 지도의 확대 레벨
            };

            // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
            var map = new kakao.maps.Map(mapContainer, mapOption);


            $.ajax({
                url: '/assets/법정동코드 전체자료.txt',
                success: function (response) {
                    response = response.split(/\r?\n/).filter((list) => list.indexOf('폐지') === -1);

                    for (let i = 2; i < response.length - 1; i++) {
                        let sicode = [];
                        let gucode = [];
                        let dongcode = [];
                        let splitData = response[i].split(/\s/g);

                        sicode.push(splitData[0].substr(0, 2));
                        sicode.push(splitData[1]);
                        // sicode.push(splitData[2]);
                        siInfo.push(sicode);

                        // console.log(splitData);
                        gucode.push(splitData[0].substr(0, 5));
                        // gucode.push(splitData[1]);
                        gucode.push(splitData[2]);
                        guInfo.push(gucode);

                        dongcode.push(splitData[0]);
                        dongcode.push(splitData[2]);
                        dongcode.push(splitData[3]);
                        dongInfo.push(dongcode);
                    }

                    siInfo = siInfo.reduce(function (acc, current) {
                        if (acc.findIndex((value) => value[0] === current[0]) === -1) {
                            acc.push(current);
                        }
                        return acc;
                    }, []);

                    guInfo = guInfo.reduce(function (acc, current) {
                        if (acc.findIndex((value) => value[0] === current[0]) === -1) {
                            acc.push(current);
                        }
                        return acc;
                    }, []);

                    for (let sicode of siInfo) {
                        $('#si').append(`<option value="${sicode[0]}">${sicode[1]}</option>`);
                    }

                    const selectedSi = $("#si option:selected").val();
                    const guList = guInfo.filter((gu) => gu[0].substr(0, 2) === selectedSi);

                    // console.log(guInfo);
                    for (let gucode of guList) {
                        $("#gu").append(`<option value="${gucode[0]}">${gucode[1]}</option>`)
                    }
                    const selectedGu = $("#gu option:selected").val();
                    const selectedDong = dongInfo.filter((dong) => dong[0].substr(0, 5) === selectedGu);

                    for (let dongcode of selectedDong) {
                        if (dongcode[2] !== '존재') $("#dong").append(`<option value="${dongcode[0]}">${dongcode[2]}</option>`);
                    }
                }
            })

            $('#si').change(function () {
                const selectedSi = $("#si option:selected").val();
                const guList = guInfo.filter((gu) => gu[0].substr(0, 2) === selectedSi);
                //console.log(guList);

                $("#gu").empty();
                $("#dong").empty();

                for (let gucode of guList) {
                    if (gucode[1] !== '존재') $("#gu").append(`<option value="${gucode[0]}">${gucode[1]}</option>`)
                }
                const selectedGu = $("#gu option:selected").val();
                const selectedDong = dongInfo.filter((dong) => dong[0].substr(0, 5) === selectedGu);

                for (let dongcode of selectedDong) {
                    if (dongcode[2] !== '존재') $("#dong").append(`<option value="${dongcode[0]}">${dongcode[2]}</option>`);
                }
            })

            $('#gu').change(function () {
                const selectedGu = $("#gu option:selected").val();
                const dongList = dongInfo.filter((dong) => dong[0].substr(0, 5) === selectedGu);

                $("#dong").empty();

                var ServiceKey = 'AJKhrWIXk1QGI/eHwViuK+D8R12gec5sdxkTT0EajuYmRB207+Drps8B8mXoWvxKwaym1AjbWy7gyzsG3VYsQQ==';
                var pageNo = '1';
                var numOfRows = '30';
                var LAWD_CD = $(this).val();
                var DEAL_YMD = '202202';

                // server에서 넘어온 data
                $.ajax({
                    url: 'http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev?serviceKey=71M3UVKqWbnqm9DQvvfMSyQEUVBbhF6blsBZadrx%2BSZ1Rg3%2Be3DBQD8dZS7WbXoGrDr9%2B7501yRtAf2ZkGISdw%3D%3D&pageNo=1&numOfRows=30&LAWD_CD=11110&DEAL_YMD=201512',
                    type: 'GET',
                    data: {
                        "ServiceKey": ServiceKey,
                        "pageNo": pageNo,
                        "numOfRows": numOfRows,
                        "LAWD_CD": LAWD_CD,
                        "DEAL_YMD": DEAL_YMD
                    },
                    dataType: 'xml',
                    success: function (response) {
                        const data = xmlToJson(response).response.body.items;
                        localStorage.setItem('list', JSON.stringify(data));

                        let selectedDongList = data.item.map((list) => {
                            const dongList = {
                                id: list.법정동읍면동코드['#text'].trim(),
                                name: list.법정동['#text'].trim()
                            }
                            return dongList;
                        });

                        const uniqueList = selectedDongList.reduce((acc, cur) => {
                            if (acc.findIndex(({id}) => id === cur.id) === -1) {
                                acc.push(cur);
                            }
                            return acc;
                        }, []);

                        for (let dong of uniqueList) {
                            //중복된걸 제거한 값을 변수? 배열? 에 넣어
                            $('#dong').append(`<option value="${dong.id}">${dong.name}</option>`)
                        }
                        //반복문 다시돌면서 어펜드
                    },
                    error: function (xhr, status, msg) {
                        console.log('상태값 : ' + status + ' Http에러메시지 : ' + msg);
                    },
                });
            });

            $('#dong').change(function () {
                const lists = JSON.parse(localStorage.getItem('list')).item;
                const selectedDong = $("#dong option:selected").val();

                const selectedList = lists.filter(list => list.법정동읍면동코드['#text'] === selectedDong)
                    .map((list) => {
                        const apartList = {
                            apart: list.아파트['#text'].trim(),
                            money: list.거래금액['#text'].trim(),
                            dong: list.법정동['#text'].trim(),
                            trade: list.거래유형['#text'].trim(),
                            jibun: list.지번['#text'].trim(),
                            doro: list.도로명['#text'].trim(),
                            juso: list.중개사소재지['#text'].trim(),
                        }
                        return apartList;
                    });

                selectedList.map(list => {
                    var address = list.juso + ' ' + list.dong + ' ' + list.doro;

                    console.log(address);

                    var geocoder = new kakao.maps.services.Geocoder();

                    // 주소로 좌표를 검색합니다
                    geocoder.addressSearch(address, function (result, status) {
                        // 정상적으로 검색이 완료됐으면
                        if (status === kakao.maps.services.Status.OK) {
                            console.log("hi");

                            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                            // 결과값으로 받은 위치를 마커로 표시합니다
                            var marker = new kakao.maps.Marker({
                                map: map,
                                position: coords,
                                title: list.apart,
                            });

                            // 인포윈도우로 장소에 대한 설명을 표시합니다
                            var infowindow = new kakao.maps.InfoWindow({
                                content: `<div style="width:150px;text-align:center;padding:6px 0;">${list.apart}</div>`
                            });
                            infowindow.open(map, marker);

                            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                            map.setCenter(coords);
                        }
                    });
                });

                // const dongList = dongInfo.filter((dong) => dong[0].substr(0,5) === selectedGu);
                //동을 고름
                //json 구에 해당하는 데이터들
                //json안의 동 == 내가 선택한 동일때
                //data 를 만들어서 make list에게 넘겨야함
            });
        });

        function xmlToJson(xml) {
            // Create the return object
            var obj = {};

            if (xml.nodeType == 1) { // element
                // do attributes
                if (xml.attributes.length > 0) {
                    obj["@attributes"] = {};
                    for (var j = 0; j < xml.attributes.length; j++) {
                        var attribute = xml.attributes.item(j);
                        obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
                    }
                }
            } else if (xml.nodeType == 3) { // text
                obj = xml.nodeValue;
            }

            // do children
            if (xml.hasChildNodes()) {
                for (var i = 0; i < xml.childNodes.length; i++) {
                    var item = xml.childNodes.item(i);
                    var nodeName = item.nodeName;
                    if (typeof (obj[nodeName]) == "undefined") {
                        obj[nodeName] = xmlToJson(item);
                    } else {
                        if (typeof (obj[nodeName].push) == "undefined") {
                            var old = obj[nodeName];
                            obj[nodeName] = [];
                            obj[nodeName].push(old);
                        }
                        obj[nodeName].push(xmlToJson(item));
                    }
                }
            }
            return obj;
        };

        $(function () {
            getSiList();

            $("#si").change(function () {
                getGuList($("#si").value);
            });

            $("#gu").change(function () {
                getDongList($("#gu").value);
            });

            $("#dong").change(function () {
                // getTradeList($("#si").val(), $("#gu").val(), $("#dong").val());
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
<%@ include file="layout/header.jsp" %>
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

    <!-- 지도 -->
    <div class="container">
        <section class="main-data">
            <div id="map" style="width:100%;height:350px;"></div>
        </section>

        <!-- 푸터 -->
        <section class="row" style="padding: 0 20px 0 20px">
            <div class="main-bottom-block1 col-sm-4">
                <img class="img-banner" src="img/block1.JPG" alt="block1">
            </div>
            <div class="main-bottom-block2 col-sm-4">
                <h3>지혜롭게 내집 마련</h3>
                <hr/>
                <ul>
                    <li>기융 자금확인 및 대출 계획</li>
                    <li>집 종류 및 지역 선택</li>
                    <li>정보수집 & 시세파악</li>
                    <li>부동산 방문 & 집구경</li>
                    <li>계약 및 잔금 치루기</li>
                    <li>소유권 이전동기</li>
                </ul>
            </div>
            <div class="main-bottom-block3 col-sm-4">
                <h3>오늘의 뉴스</h3>
                <hr/>
                <ul>
                    <li><a href="https://www.donga.com/news/Society/article/all/20220311/112284497/1">‘한강벨트’서 승리한 尹…서울도
                        재건축 활성화 기대감</a> 뉴시스
                    </li>
                    <li><a href="https://www.donga.com/news/Economy/article/all/20220311/112287834/1">서울 아파트 매수심리 소폭
                        회복…그래도 17주째 '팔자' 우위</a> 동아일보
                    </li>
                    <li><a href="https://www.yna.co.kr/view/GYH20220310003200044">서울 아파트값 등락률 추이</a> 연합뉴스</li>
                </ul>
            </div>
        </section>
    </div>
</main>

<%@ include file="layout/footer.jsp" %>
</body>
</html>
