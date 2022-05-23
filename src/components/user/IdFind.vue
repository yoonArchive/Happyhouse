<template>
  <section>
    <br /><br /><br /><br />
    <h2 style="text-align: center">아이디 찾기</h2>
    <h5 style="text-align: center">회원가입시 입력한 정보를 넣어 주세요.</h5>
    <form>
      <div class="row gtr-uniform aln-center">
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
                id="findIdBtn"
                @click="getID"
              >
                find ID
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
      userName: null,
      userPhone: null,
    };
  },
  methods: {
    getID() {
      this.$axios
        .get(`/user/findId`, {
          params: {
            userName: this.userName,
            phone: this.userPhone,
          },
        })
        .then(({ data }) => {
          this.$swal.fire({
            icon: "success",
            html: `고객님의 정보와 일치하는 아이디는 <br>${data} 입니다.`,
          });
        })
        .catch(() => {
          this.$swal.fire({
            icon: "error",
            html: `고객님의 정보와 일치하는 회원정보가 없습니다.`,
          });
        });
    },
    goLogin() {
      this.$router.push("/user/login");
    },
  },
};
</script>

<style></style>
