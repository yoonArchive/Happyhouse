<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
        <div class="collapse navbar-collapse">
            <a class="navbar-brand" href="/" id="logo">Happy House</a>
            <ul class="navbar-nav nav-menu">
                <li class="nav-item"><a href="/notice/list" class="nav-link menu-item">공지사항</a></li>
                <li class="nav-item"><a href="/trade/info" class="nav-link menu-item">실거래가 조회</a></li>
                <li class="nav-item"><a href="/trade/search" class="nav-link menu-item">검색</a></li>
            </ul>
        </div>
        <div class="collapse navbar-collapse justify-content-end">
            <ul class="navbar-nav nav-user">
                <c:choose>
                    <c:when test="${empty userInfo}">
                        <li class="nav-item user-login">
                            <a href="${root}/login" id="login" class="nav-link menu-item">로그인</a>
                        </li>
                        <li class="nav-item user-signup">
                            <a href="${root}/signup" id="signup" class="nav-link menu-item">회원가입</a>
                        </li>

                    </c:when>
                    <c:otherwise>
                        <li class="nav-item user-logout">
                            <a href="${root}/user/logout" id="logout" class="nav-link menu-item" >로그아웃</a>
                        </li>
                        <li class="nav-item user-modify">
                            <a href="${root}/user/update" id="user-modify" class="nav-link menu-item">마이페이지</a>
                        </li>

                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
</header>