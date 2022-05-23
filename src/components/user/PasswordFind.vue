<template>
  <section>
    <br /><br /><br /><br />
    <h2 style="text-align: center">비밀번호 찾기</h2>
    <h5 style="text-align: center">회원가입시 입력한 정보를 넣어 주세요.</h5>
    <form>
      <div class="row gtr-uniform aln-center">
        <div class="col-6 col-12-xsmall">
          <input type="text" name="userId" id="userId" placeholder="Id" />
        </div>
        <div></div>
        <div class="col-6 col-12-xsmall">
          <input type="text" name="userName" id="userName" placeholder="Name" />
        </div>
        <div></div>
        <div class="col-6 col-12-xsmall">
          <input type="tel" name="phone" id="phone" placeholder="PHONE" />
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
  methods: {
    getPw() {},
    goLogin() {
      this.$router.push("/user/login");
    },
  },
};
</script>

<style></style>
