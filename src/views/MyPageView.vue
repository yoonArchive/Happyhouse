<template>
  <article id="main">
    <section class="wrapper style5">
      <div class="inner">
        <div class="row">
          <div style="width: 20%">
            <my-page-menu />
          </div>
          <div v-if="clickMyArea" style="width: 80%">
            <my-area-list />
          </div>
          <div v-else-if="clickUserModify" style="width: 80%">
            <user-update />
          </div>
          <div v-else-if="clickUserDelete" style="width: 80%">
            <user-delete />
          </div>
        </div>
      </div>
    </section>
  </article>
</template>

<script>
import { mapState } from "vuex";
import MyPageMenu from "@/components/user/item/MyPageMenu.vue";
import UserUpdate from "@/components/user/item/UserUpdate.vue";
import MyAreaList from "@/components/user/item/HouseWishList.vue";
import UserDelete from "@/components/user/item/UserDelete.vue";

const userStore = "userStore";

export default {
  components: {
    MyPageMenu,
    UserUpdate,
    MyAreaList,
    UserDelete,
  },
  computed: {
    ...mapState(userStore, [
      "clickMyArea",
      "clickUserModify",
      "clickUserDelete",
    ]),
  },
};
</script>

<style scoped>
section {
  min-height: 550px;
}
</style>
