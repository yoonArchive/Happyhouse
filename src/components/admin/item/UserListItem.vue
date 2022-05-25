<template>
  <tr>
    <td>{{ index }}</td>
    <td>{{ userId }}</td>
    <td> ***</td>
    <td>{{ userName }}</td>
    <td>{{ email }}</td>
    <td>{{ phone }}</td>
    <td>{{ joinDate }}</td>
    <td>
      <select
          :v-model="authority"
          class="array-select form-control form-select"
          @change="print"
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
  data() {
    return {
      updatedAuthority: ""
    }
  },
  filters: {
    dateFormat(joinDate) {
      return moment(new Date(joinDate)).format("YY.MM.DD");
    },
  },
  methods: {
    ...mapActions(userStore, ["updateUserAuthority", "deleteUser"]),
    updateUser() {
      console.log(this.authority);
      this.updateUserAuthority(this.authority);
    },
    deleteUser() {
      alert("삭제");
    },
    print() {
      console.log(this.authority);
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