<template>
  <section>
    <br /><br /><br /><br />
    <h2 style="text-align: center">login</h2>
    <form>
      <div class="row gtr-uniform aln-center">
        <div class="col-6 col-12-xsmall">
          <input
            type="text"
            name="userId"
            id="userId"
            v-model="user.userId"
            placeholder="Id"
          />
        </div>
        <div></div>
        <div class="col-6 col-12-xsmall">
          <input
            type="password"
            name="userPwd"
            id="userPwd"
            v-model="user.userPwd"
            placeholder="password"
            @keyup.enter="confirm"
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
      <br />
      <h5 style="text-align: center">
        happyhouse 회원이 아니신가요?
        <a @click="goSignUp" style="color: gray">지금 가입하세요.</a>
      </h5>
    </form>
  </section>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "userLogin",
  data() {
    return {
      user: {
        userId: null,
        userPwd: null,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(userStore, ["login"]),
    async confirm() {
      await this.login(this.user);
      if (this.isLogin) {
        this.$router.push({ name: "home" });
      }
    },
    checkValue() {
      if (!this.user.userId) {
        this.$swal({
          className: "swal",
          text: "아이디를 입력해주세요.",
        });
        return;
      } else if (!this.user.userPwd) {
        this.$swal({
          className: "swal",
          text: "비밀번호를 입력해주세요.",
        });
        return;
      }
      this.confirm();
      /*let loginInfo = {
        userId: this.user.userId,
        userPwd: this.user.userPwd,
      };
      this.$axios
        .post("/user/login", loginInfo)
        .then(() => {
          this.$swal({
            className: "swal",
            icon: "success",
            text: `${this.user.userId} 님 반갑습니다.`,
          }).then(() => {
            this.moveHome();
          });
        })
        .catch(() => {
          this.$swal({
            className: "swal",
            icon: "warning",
            text: "아이디 혹은 비밀번호를 다시 확인해주세요.",
          });
        });*/
    },
    goFindId() {
      this.$router.push("/user/findid");
    },
    goFindPw() {
      this.$router.push("/user/findpw");
    },
    goSignUp() {
      this.$router.push("/user/signup");
    },
    moveHome() {
      this.$router.push("/");
    },
  },
};
</script>

<style></style>
