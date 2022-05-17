<template>
  <section>
    <h2 style="text-align: center">Find Password</h2>
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
          <input
            type="tel"
            name="phone"
            id="phone"
            placeholder="010-0000-0000"
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
  methods: {
    getPw() {},
    goLogin() {
      this.$router.push("/user/login");
    },
  },
};
</script>

<style></style>
