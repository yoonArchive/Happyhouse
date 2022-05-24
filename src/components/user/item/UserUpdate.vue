<template>
  <section>
    <h2 style="text-align: center">회원 정보 수정</h2>
    <form>
      <div class="row gtr-uniform aln-center">
        <div class="col-6 col-12-xsmall">
          <input
            type="text"
            name="userId"
            id="userId"
            placeholder="Id"
            v-model="userId"
            readonly
          />
        </div>
        <div></div>
        <div class="col-6 col-12-xsmall">
          <input
            type="password"
            name="userPwd"
            id="userPwd"
            v-model="userPwd"
            placeholder="현재 비밀번호"
          />
        </div>
        <div></div>
        <div class="col-6 col-12-xsmall">
          <input
            type="password"
            name="newPwd"
            id="newPwd"
            v-model="newPwd"
            placeholder="새로운 비밀번호"
            @keyup="checkPassword"
          />
        </div>
        <div></div>
        <div class="col-6 col-12-xsmall">
          <input
            type="password"
            name="newPwd-check"
            id="newPwd-check"
            v-model="newPwdCheck"
            placeholder="새로운 비밀번호 (확인)"
            @keyup="checkPassword"
          />
          <div id="passwordResult" class="mb-3">{{ passwordCheckMessage }}</div>
        </div>
        <div></div>
        <div class="col-6 col-12-xsmall">
          <input
            type="text"
            name="userName"
            id="userName"
            v-model="userName"
            placeholder="Name"
          />
        </div>
        <div></div>
        <div class="col-6 col-12-xsmall">
          <input
            type="email"
            name="email"
            id="email"
            v-model="email"
            placeholder="Email"
          />
        </div>
        <div></div>
        <div class="col-6 col-12-xsmall">
          <input
            type="tel"
            name="phone"
            id="phone"
            v-model="phone"
            placeholder="Phone"
          />
        </div>
        <div></div>
        <div class="col-6 col-12-medium">
          <ul class="actions stacked">
            <li>
              <button type="button" class="button" @click="modify">변경</button>
            </li>
          </ul>
        </div>
      </div>
    </form>
  </section>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const userStore = "userStore";

export default {
  data() {
    return {
      userId: null,
      userPwd: "",
      newPwd: "",
      newPwdCheck: "",
      userName: null,
      email: null,
      phone: null,
      isAvailable: false,
      passwordCheckMessage: "",
      isCheckPassword: false,
    };
  },
  computed: {
    ...mapState(userStore, ["user", "userInfo", "isUpdated"]),
  },
  created() {
    this.getDetail();
    this.userId = this.user.userId;
    this.userName = this.user.userName;
    this.email = this.user.email;
    this.phone = this.user.phone;
  },
  methods: {
    ...mapActions(userStore, ["getDetail", "updateUser", "showUserModify"]),
    ...mapMutations(userStore, ["CLEAR_IS_UPDATED"]),
    checkPassword() {
      if (this.newPwdCheck.length === 0 || this.newPwd === this.newPwdCheck) {
        this.passwordCheckMessage = "";
        this.isCheckPassword = true;
      } else {
        this.passwordCheckMessage = "비밀번호가 일치하지 않습니다.";
        this.isCheckPassword = false;
      }
    },
    modify() {
      let regEmail =
        /^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
      let regPhone = /01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}/;

      if (!this.userPwd) {
        this.$swal({
          className: "swal",
          text: "비밀번호를 입력해주세요.",
        });
      } else if (!this.isCheckPassword) {
        this.$swal({
          className: "swal",
          text: "비밀번호가 일치하지 않습니다.",
        });
      } else if (!this.userName) {
        this.$swal({
          className: "swal",
          text: "이름을 입력해주세요.",
        });
      } else if (!this.email) {
        this.$swal({
          className: "swal",
          text: "이메일을 입력해주세요.",
        });
      } else if (!regEmail.test(this.email)) {
        this.$swal({
          className: "swal",
          text: "이메일 형식을 확인해주세요.",
        });
      } else if (!this.phone) {
        this.$swal({
          className: "swal",
          text: "연락처를 입력해주세요.",
        });
      } else if (!regPhone.test(this.phone)) {
        this.$swal({
          className: "swal",
          text: "연락처 형식을 확인해주세요.",
        });
      } else {
        let data = {
          userPwd: this.newPwd,
          name: this.userName,
          email: this.email,
          phone: this.phone,
        };
        this.updateUser(data);
        this.reset();

        /*if (this.isUpdated) {
          this.$swal({
            className: "swal",
            text: "수정 성공",
          });
          this.CLEAR_IS_UPDATED();
        } else {
          this.$swal({
            className: "swal",
            text: "수정 실패",
          });
        }*/
      }
    },
    reset() {
      this.userPwd = "";
      this.newPwd = "";
      this.newPwdCheck = "";
    },
  },
};
</script>

<style scoped>
input {
  font-size: 0.9em;
}
</style>
