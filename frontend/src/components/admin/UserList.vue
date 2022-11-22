<template>
  <section>
    <h4>회원 목록</h4>
    <div class="table-wrapper">
      <table>
        <colgroup>
          <col style="width: 4%" />
          <col style="width: 11%" />
          <col style="width: 8%" />
          <col style="width: 8%" />
          <col style="width: 17%" />
          <col style="width: 13%" />
          <col style="width: 17%" />
          <col style="width: 7%" />
          <col style="width: 13%" />
        </colgroup>
        <thead>
          <tr>
            <th>번호</th>
            <th>아이디</th>
            <th>비밀번호</th>
            <th>이름</th>
            <th>이메일</th>
            <th>연락처</th>
            <th>가입날짜</th>
            <th>권한</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
        <user-list-item
            v-for="(user, index) in users"
            :key="user.userId"
            v-bind="user"
            :index="index+1"
            @refresh="initialize"
            @update="updateUser"
        />
        </tbody>
      </table>
    </div>
  </section>
</template>

<script>
import http from "@/api/http.js";
import UserListItem from "@/components/admin/item/UserListItem.vue";
import {mapActions} from "vuex";

const userStore = "userStore";

export default {
  name: "Admin",
  components: {
    UserListItem
  },
  data() {
    return {
      users: []
    };
  },
  created() {
    this.initialize();
  },
  methods:{
    ...mapActions(userStore, ["updateUserAuthority"]),
    initialize() {
      http.get("/user/admin")
          .then(({data}) => {
            this.users = data;
          })
          .catch(({error}) => {
            this.$swal("error", error, "error");
          });
    },
    updateUser(userId, authority) {
      let data = {
        userId: userId,
        authority: authority
      };
      this.updateUserAuthority(data);
    }
  }
};
</script>

<style scoped>
table {
  font-size: small;
}
</style>
