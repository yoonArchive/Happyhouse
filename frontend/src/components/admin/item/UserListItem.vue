<template>
  <tr>
    <td>{{ index }}</td>
    <td>{{ userId }}</td>
    <td>***</td>
    <td>{{ userName }}</td>
    <td>{{ email }}</td>
    <td>{{ phone }}</td>
    <td>{{ joinDate }}</td>
    <td>
      <select
          v-model="updatedAuthority"
          class="array-select form-control form-select"
      >
        <option value="사용자">사용자</option>
        <option value="관리자">관리자</option>
      </select>
    </td>
    <td class="col-12-xsmall">
      <button class="col-6" @click="updateUser">수정</button>
      <button class="col-6" @click="deleteUser">삭제</button>
    </td>
  </tr>
</template>

<script>
import moment from "moment";
import {mapActions} from "vuex";
import axios from "@/api/http";

const userStore = "userStore";

export default {
  name: "UserListItem",
  props: {
    index: Number,
    userId: String,
    userPwd: String,
    userName: String,
    email: String,
    phone: String,
    authority: String,
    joinDate: String
  },
  filters: {
    dateFormat(joinDate) {
      return moment(new Date(joinDate)).format("YY.MM.DD");
    },
  },
  data() {
    return {
      updatedAuthority: this.authority
    }
  },
  methods: {
    ...mapActions(userStore, ["updateUserAuthority"]),
    updateUser() {
      this.$emit("update", this.userId, this.updatedAuthority);
    },
    deleteUser() {
      axios.delete(`user/${this.userId}`)
          .then( async () => {
            alert("회원 삭제가 완료되었습니다.");
            await this.$emit("refresh");
          })
          .catch(() => {
            alert("회원 탈퇴 중 문제가 발생했습니다.");
          });
    }
  }
};
</script>

<style scoped>
select {
  padding-right: -50px;
  width: 90px;
  font-size: small;
}

button {
  width: 50px;
  padding-left: 10px;
  padding-right: 10px;
}
</style>