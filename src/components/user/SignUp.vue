<template>
  <section>
    <h2 style="text-align: center">Sign Up</h2>
    <form>
      <div class="row gtr-uniform aln-center">
        <div class="col-6 col-12-xsmall">
          <div id="idForm" class="row">
            <input
                type="text"
                name="userId"
                id="userId"
                v-model="userId"
                placeholder="Id"
                ref="userId"
                @keyup="validateUserId"
            />
            <button id="duplicationCheck" type="button" class="button" @click="checkIdDuplication">
              중복 확인
            </button>
          </div>
          <div id="idResult" class="mb-3">{{ idCheckMessage }}</div>
        </div>
        <div></div>
        <div class="col-6 col-12-xsmall">
          <input
              type="password"
              name="userPwd"
              id="userPwd"
              v-model="userPwd"
              placeholder="Password"
          />
        </div>
        <div></div>
        <div class="col-6 col-12-xsmall">
          <input
              type="password"
              name="userPwd-check"
              id="userPwd-check"
              v-model="userPwdCheck"
              placeholder="Reconfirm Password"
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
        <div class="col-6">
          <ul id="buttons" class="row aln-center actions">
            <li>
              <button type="button" class="primary" @click="signup">sign up</button>
            </li>
            <li><input type="reset" class="button" value="Reset" @click="reset"/></li>
            <li>
              <button type="button" class="button" @click="moveHome">
                Home
              </button>
            </li>
          </ul>
        </div>
      </div>
    </form>
  </section>
</template>

<script>
import http from "@/api/http.js";

export default {
  data() {
    return {
      userId: "",
      userPwd: "",
      userPwdCheck: "",
      userName: "",
      email: "",
      phone: "",
      isAvailable: false,
      idCheckMessage: "",
      passwordCheckMessage: "",
      isUsableId: false,
      isCheckId: false,
      isCheckPassword: false
    };
  },
  methods: {
    validateUserId() {
      if (this.userId.length !== 0 && (this.userId.length < 5 || this.userId.length > 12)) {
        this.idCheckMessage = "아이디는 5자 이상 12자 이하여야합니다.";
        this.isUsableId = false;
      } else {
        this.idCheckMessage = "";
        this.isUsableId = true;
      }
    },
    checkIdDuplication() {
      if (this.isUsableId) {
        http.get(`/user/idCheck?userId=${this.userId}`)
            .then(() => {
              this.$swal.fire("사용 가능한 아이디입니다.");
              this.isCheckId = true;
            })
            .catch(() => {
              this.$swal.fire("사용 불가능한 아이디입니다.")
                  .then(() => {
                this.$refs.userId.focus();
              });
            });
      } else {
        this.$swal.fire("아이디를 확인해주세요.").then(() => {
          this.$refs.userId.focus();
        });
      }
    },
    checkPassword() {
      if (this.userPwdCheck.length === 0 || this.userPwd === this.userPwdCheck) {
        this.passwordCheckMessage = "";
        this.isCheckPassword = true;
      } else {
        this.passwordCheckMessage = "비밀번호가 일치하지 않습니다.";
        this.isCheckPassword = false;
      }
    },
    signup() {
      let regEmail = /^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
      let regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;

      if (!this.userId){
        this.$swal("아이디를 입력하세요.");
      } else if (!this.userPwd) {
        this.$swal("비밀번호를 입력하세요.");
      } else if (!this.userName) {
        this.$swal("비밀번호를 입력하세요.");
      } else if (!this.email) {
        this.$swal("이메일을 입력하세요.");
      } else if (!this.phone) {
        this.$swal("연락처를 입력하세요.");
      } else if (!regEmail.test(this.email)) {
        this.$swal("이메일을 확인해주세요.");
      } else if (!regPhone.test(this.phone)) {
        this.$swal("연락처를 확인해주세요.");
      } else if (!this.isCheckId) {
        this.$swal("아이디 중복 검사를 해주세요.");
      } else {
        let data = {
          userId: this.userId,
          userPwd: this.userPwd,
          userName: this.userName,
          email: this.email,
          phone: this.phone,
        };
        http.post(`/user/signup`, data)
            .then(() => {
              this.$swal("회원가입이 완료되었습니다.")
                  .then(() => {
                    this.$router.push("/user/login");
                  });
            })
            .catch(() => {
              this.$swal("회원가입 중 문제가 발생하였습니다.");
            });
      }
    },
    reset() {
      this.userId = "";
      this.userPwd = "";
      this.userPwdCheck = "";
      this.userName = "";
      this.email = "";
      this.phone = "";
      this.isAvailable = false;
      this.idCheckMessage = "";
      this.passwordCheckMessage = "";
    },
    moveHome() {
      this.$router.push("/");
    },
  },
};
</script>

<style scoped>
#idForm {
  margin-left: 0px;
  margin-right: -10px;
}

#idResult, #passwordResult {
  margin-left: 5px;
}

#passwordResult {
  width: 1000px;
}

#duplicationCheck {
  margin-top: 2px;
  margin-left: 10px;
  margin-right: -10px;
}

#userId {
  width: 68%;
}

#buttons {
  margin-right: 24px;
}
</style>
