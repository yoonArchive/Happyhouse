<template>
  <div>
    <h2 style="text-align: center">
      Happy House 탈퇴 전 안내사항을 꼭 확인해주세요.
    </h2>
    <blockquote style="font-size: 15px">
      탈퇴 후 회원님의 이용정보가 삭제되어 복구 불가능합니다. <br /><br />
      회원정보 및 개인 이용기록은 모두 삭제되며, 삭제된 데이터는 복구
      불가능합니다.<br /><br />
      탈퇴 후 회원님의 Happy House 이용정보는 삭제됩니다. <br /><br />
      단, 게시판형 서비스에 등록한 게시글은 탈퇴 후 본인의 게시물임을 확인할
      방법이 없어 임의로 삭제해 드릴 수 없습니다.
    </blockquote>
    <div class="col-6 col-12-small">
      <input type="checkbox" id="check" name="check" checked />
      <label for="check">안내사항을 모두 확인하였으며, 이에 동의합니다 </label>
    </div>
    <br />
    <button type="button" class="button" @click="submit">탈퇴</button>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";

const userStore = "userStore";

export default {
  computed: {
    ...mapState(userStore, ["userInfo"])
  },
  methods:{
    ...mapActions(userStore, ["deleteUser"]),
    submit() {
      this.deleteUser();
    }
  }
};
</script>

<style></style>
