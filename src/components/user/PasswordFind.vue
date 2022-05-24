<template>
  <section>
    <br /><br /><br /><br />
    <h2 style="text-align: center">비밀번호 찾기</h2>
    <h5 style="text-align: center">회원가입시 입력한 정보를 넣어 주세요.</h5>
    <form>
      <div class="row gtr-uniform aln-center">
        <div class="col-6 col-12-xsmall">
          <input
            type="text"
            name="userId"
            id="userId"
            placeholder="Id"
            v-model="userId"
          />
        </div>
        <div></div>
        <div class="col-6 col-12-xsmall">
          <input
            type="text"
            name="userName"
            id="userName"
            placeholder="Name"
            v-model="userName"
          />
        </div>
        <div></div>
        <div class="col-6 col-12-xsmall">
          <input
            type="tel"
            name="phone"
            id="phone"
            placeholder="PHONE"
            v-model="userPhone"
          />
        </div>
        <div></div>
        <div class="col-6">
          <ul class="actions">
            <li></li>
            <li>
              <button
                type="button"
                class="primary"
                id="findPwdBtn"
                @click="getPw"
              >
                find pw
              </button>
            </li>
            <li><input type="reset" class="button" value="Reset" /></li>
            <li>
              <button type="button" class="button" @click="goLogin">
                login
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
      userName: null,
      userPhone: null,
    };
  },
  methods: {
    getPw() {
      let regPhone = /01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}/;

      if (!this.userId) {
        this.$swal({
          className: "swal",
          text: "아이디를 입력해주세요.",
        });
      } else if (!this.userName) {
        this.$swal({
          className: "swal",
          text: "이름을 입력해주세요.",
        });
      } else if (!this.userPhone) {
        this.$swal({
          className: "swal",
          text: "연락처를 입력해주세요.",
        });
      } else if (!regPhone.test(this.userPhone)) {
        this.$swal({
          className: "swal",
          text: "연락처 형식을 확인해주세요.",
        });
      } else {
        this.$axios
          .get(`/user/findPwd`, {
            params: {
              userId: this.userId,
              userName: this.userName,
              phone: this.userPhone,
            },
          })
          .then(({ data }) => {
            this.$swal
              .fire({
                icon: "success",
                html: `고객님의 정보와 일치하는 비밀번호는 <br> ${data} 입니다.`,
              })
              .then(() => {
                this.goLogin();
              });
          })
          .catch(() => {
            this.$swal.fire({
              icon: "error",
              html: `고객님의 정보와 일치하는 회원정보가 없습니다.`,
            });
          });
      }
    },
    goLogin() {
      this.$router.push("/user/login");
    },
  },
};
</script>

<style></style>
