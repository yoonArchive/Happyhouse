<template>
  <section>
    <h2 style="text-align: center">login</h2>
    <form>
      <div class="row gtr-uniform aln-center">
        <div class="col-6 col-12-xsmall">
          <input
            type="text"
            name="userId"
            id="userId"
            v-model="userId"
            placeholder="Id"
          />
        </div>
        <div></div>
        <div class="col-6 col-12-xsmall">
          <input
            type="password"
            name="userPwd"
            id="userPwd"
            v-model="userPwd"
            placeholder="password"
          />
        </div>
        <div></div>
        <div class="col-6">
          <ul class="actions">
            <li>
              <button
                type="button"
                class="primary"
                id="loginBtn"
                @click="checkValue"
              >
                login
              </button>
            </li>
            <li>
              <button
                type="button"
                class="button"
                id="findIdBtn"
                @click="goFindId"
              >
                find Id
              </button>
            </li>
            <li>
              <button
                type="button"
                class="button"
                id="findPwdBtn"
                @click="goFindPw"
              >
                find pw
              </button>
            </li>
          </ul>
        </div>
      </div>
    </form>
  </section>
</template>

<script>
export default {
  data() {
    return {
      userId: null,
      userPwd: null,
    };
  },
  methods: {
    checkValue() {
      if (!this.userId) {
        alert("아이디를 입력해주세요.");
        return;
      } else if (!this.userPwd) {
        alert("비밀번호를 입력해주세요.");
        return;
      }
      let loginInfo = {
        userId: this.userId,
        userPwd: this.userPwd,
      };
      this.$axios
        .post("/user/login", loginInfo)
        .then(() => {
          alert("로그인 성공");
          this.moveHome();
        })
        .catch(() => {
          alert("아이디 혹은 비밀번호를 다시 확인해주세요.");
        });
    },
    goFindId() {
      this.$router.push("/user/findid");
    },
    goFindPw() {
      this.$router.push("/user/findpw");
    },
    moveHome() {
      this.$router.push("/");
    },
  },
};
</script>

<style></style>
