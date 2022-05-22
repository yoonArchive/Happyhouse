<template>
  <header id="header">
    <h1><router-link to="/">Happy House</router-link></h1>
    <nav class="navbar navbar-expand-sm bg-dark">
      <ul class="navbar-nav">
        <li class="nav-item active mr-2">
          <router-link to="/trade">실거래가 조회</router-link>
        </li>
        <li class="nav-item active mr-2">
          <router-link to="/notice">공지사항</router-link>
        </li>
        <li class="nav-item mr-2">
          <router-link to="/qna">Q&A</router-link>
        </li>
        <li class="nav-item mr-2">
          <router-link to="/user/login">Login | SignUp</router-link>
        </li>
        <li class="nav-item mr-2">
          <router-link to="/myPage">myPage</router-link>
        </li>
      </ul>
    </nav>
    <!--<nav id="nav">
      <ul>
        <li class="special">
          <a href="#menu" class="menuToggle" @click="showMenu"
            ><span>Menu</span></a
          >
          <div id="menu">
            <ul>
              <li><a href="#">Home</a></li>
              <li><a href="#">Generic</a></li>
              <li><a href="#">Elements</a></li>
              <li><a href="#">Notice</a></li>
              <li><a href="#">Sign Up</a></li>
              <li><a href="#">Log In</a></li>
            </ul>
          </div>
        </li>
      </ul>
    </nav>-->
  </header>
</template>

<script>
export default {
  methods: {
    showMenu() {
      console.log("menu");
    },
  },
};
</script>

<style></style>
