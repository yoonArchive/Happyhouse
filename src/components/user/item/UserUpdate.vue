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
          />
        </div>
        <div></div>
        <div class="col-6 col-12-xsmall">
          <input
            type="password"
            name="userPwd-check"
            id="userPwd-check"
            v-model="newPwdCheck"
            placeholder="새로운 비밀번호 (확인)"
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
        <div class="col-6 col-12-medium">
          <ul class="actions stacked">
            <li></li>
            <li>
              <button type="button" class="button">변경</button>
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
      newPwd: null,
      newPwdCheck: null,
      userName: null,
      email: null,
      phone: null,
      isAvailable: false,
      msg: "",
    };
  },
};
</script>

<style scoped>
input {
  font-size: 0.9em;
}
</style>
