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
        <li class="nav-item mr-2"><a @click="userLogout">Logout</a></li>
        <li class="nav-item mr-2">
          <router-link to="/myPage">myPage</router-link>
        </li>
      </ul>
    </nav>
  </header>
</template>

<script>
import { mapActions } from "vuex";

const userStore = "userStore";

export default {
  methods: {
    ...mapActions(userStore, ["logout"]),
    userLogout() {
      this.logout();
      this.$router.push("/");
    },
  },
};
</script>

<style></style>
