<template>
  <section>
    <div
        class="table-wrapper"
        style="width: 130%; margin-top: 70px; margin-left: -140px"
    >
      <table class="alt">
        <tbody>
        <tr>
          <td>
            <div @click="goMyArea"><a>나의 관심 지역</a></div>
          </td>
        </tr>
        <tr>
          <td>
            <div @click="goModify"><a>회원 정보 수정</a></div>
          </td>
        </tr>
        <tr>
          <td>
            <div @click="goDelete"><a>회원 탈퇴</a></div>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </section>
</template>

<script>
import http from "@/api/http.js";
import {mapActions, mapMutations} from "vuex";

const userStore = "userStore";

export default {
  methods: {
    ...mapActions(userStore, [
      "showUserArea",
      "showUserModify",
      "showUserDelete",
    ]),
    ...mapMutations(userStore, ["CLEAR_CLICK_MENU"]),
    goModify() {
      this.showUserModify();
    },
    goMyArea() {
      this.showUserArea();
    },
    goDelete() {
      this.$swal({
        title: "정말 탈퇴하시겠습니까?",
        className: "swal",
        icon: "warning",
        html: `
        <h5 style="color:black">탈퇴하시려면 비밀번호를 입력해주세요.</h5>
        <input type="password" id="password" class="swal2-input" placeholder="Enter Password" style="font-size:0.8em; width:380px">`,
        preConfirm: () => {
          const password = this.$swal
              .getPopup()
              .querySelector("#password").value;
          if (!password) {
            this.$swal.showValidationMessage(`비밀번호를 입력해주세요.`);
          } else {
            http.get(`/user/pwdCheck?userToken=${sessionStorage.getItem("access-token")}&userPwd=${password}`)
              .then(() => {
                this.showUserDelete();
              })
              .catch(() => {
                this.$swal({
                  text:"비밀번호가 일치하지 않습니다.",
                  className: "swal"
                })
                    .then(() => {
                      this.goDelete();
                    });
              });
          }
          return {password: password};
        },
      });
    },
  },
  created() {
    this.CLEAR_CLICK_MENU();
  },
};
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css);

section {
  font-family: "Nanum Gothic", serif;
}

tr {
  background-color: white;
  font-weight: 600;
  font-size: 0.7em;
  text-align: center;
}

.swal {
  font-size: 12px;
  text-shadow: 0px -1px 0px rgba(0, 0, 0, 0.3);
}
</style>
