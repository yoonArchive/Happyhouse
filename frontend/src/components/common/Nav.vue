<template>
  <header id="header">
    <h1><router-link to="/">Happy House</router-link></h1>
    <nav class="navbar navbar-expand-sm bg-dark">
      <ul class="navbar-nav">
        <li class="nav-item active mr-2" @click="limitAccess">
          <router-link to="/trade">실거래가 조회</router-link>
        </li>
        <li class="nav-item mr-2">
          <router-link to="/qna">Q&A</router-link>
        </li>
        <li class="nav-item active mr-2">
          <router-link to="/notice">공지사항</router-link>
        </li>
        <li class="nav-item mr-2">
          <router-link to="/user/login" v-if="!userInfo"
            >Login | SignUp</router-link
          >
        </li>
        <li
          class="nav-item mr-2"
          v-if="userInfo && userInfo.authority !== '관리자'"
        >
          <router-link to="/myPage">myPage</router-link>
        </li>
        <li
          class="nav-item mr-2"
          v-if="userInfo && userInfo.authority === '관리자'"
        >
          <router-link to="/admin">admin</router-link>
        </li>
        <li class="nav-item mr-2" v-if="userInfo">
          <a @click="userLogout">Logout</a>
        </li>
      </ul>
    </nav>
  </header>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["logout"]),

    userLogout() {
      this.logout();
    },
    limitAccess() {
      if (!this.userInfo) {
        this.$swal(
          "접근 실패",
          "Happy House 회원만 접근할 수 있습니다.",
          "error"
        );
        this.$router.push("/user/login");
      }
    },
  },
};
</script>

<style></style>
