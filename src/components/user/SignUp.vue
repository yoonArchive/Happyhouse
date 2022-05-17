<template>
  <section>
    <h2 style="text-align: center">Sign Up</h2>
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
          <div id="idResult" class="mb-3">{{ msg }}</div>
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
        <div class="col-6 col-12-xsmall">
          <input
            type="password"
            name="userPwd-check"
            id="userPwd-check"
            v-model="userPwdCheck"
            placeholder="reconfirm password"
          />
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
          <ul class="actions">
            <li></li>
            <li>
              <button type="button" class="primary" @click="checkValue">
                sign up
              </button>
            </li>
            <li><input type="reset" class="button" value="Reset" /></li>
            <li>
              <button type="button" class="button" @click="goHome">Home</button>
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
      userPwdCheck: null,
      userName: null,
      email: null,
      phone: null,
      isAvailable: false,
      msg: "",
    };
  },
};
</script>

<style></style>
