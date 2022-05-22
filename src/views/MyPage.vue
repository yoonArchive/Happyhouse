<template>
  <article id="main">
    <section class="wrapper style5">
      <div class="inner">
        <div class="row">
          <div style="width: 30%">
            <my-page-menu />
          </div>
          <div style="width: 60%"><user-update /></div>
        </div>
      </div>
    </section>
  </article>
</template>

<script>
import MyPageMenu from "@/components/user/item/MyPageMenu.vue";
import UserUpdate from "@/components/user/item/UserUpdate.vue";
export default {
  components: {
    MyPageMenu,
    UserUpdate,
  },
};
</script>

<style scoped>
section {
  min-height: 550px;
}
</style>
