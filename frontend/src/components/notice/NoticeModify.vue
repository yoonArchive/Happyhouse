<template>
  <article id="main">
    <header>
      <h2>Notice</h2>
      <p>공지사항 수정</p>
    </header>
    <section class="wrapper style5">
      <div class="inner">
        <h4>Form</h4>
        <form @submit.prevent="updateNotice">
          <div class="row gtr-uniform aln-center">
            <div class="col-12 col-12-xsmall">
              <input
                type="text"
                name="title"
                id="title"
                placeholder="Enter Title"
                v-model="updateForm.title"
                required
              />
            </div>
            <div class="col-12">
              <input
                type="text"
                name="category"
                id="category"
                v-model="updateForm.category"
                readonly
              />
            </div>
            <div class="col-12">
              <textarea
                name="content"
                id="content"
                placeholder="Enter Content"
                rows="10"
                v-model="updateForm.content"
                required
              ></textarea>
            </div>
            <div></div>
            <div class="col-6">
              <ul class="actions">
                <li>
                  <button class="primary" type="submit">수정</button>
                </li>
                <li><button type="button" @click="reset">초기화</button></li>
                <li>
                  <button type="button" @click="goDetail">취소</button>
                </li>
              </ul>
            </div>
          </div>
        </form>
      </div>
    </section>
  </article>
</template>

<script>
export default {
  name: "NoticeModify",
  data() {
    return {
      updateForm: {
        noticeId: 0,
        title: "",
        category: "",
        content: "",
        author: "", // 나중에 수정
        createDate: "",
      },
    };
  },
  created() {
    this.$axios
      .get(`/notice/${this.$route.params.noticeId}`)
      .then(({ data }) => {
        this.updateForm = data;
      });
  },
  methods: {
    updateNotice() {
      let noticeInfo = {
        noticeId: this.updateForm.noticeId,
        title: this.updateForm.title,
        category: this.updateForm.category,
        content: this.updateForm.content,
        author: this.updateForm.author,
        createDate: this.updateForm.createDate,
      };
      this.$axios
        .put(`/notice/${this.updateForm.noticeId}`, noticeInfo)
        .then(() => {
          this.sweetAlert("success");
          this.goDetail();
        })
        .catch(() => {
          this.sweetAlert("fail");
        });
    },
    goDetail() {
      this.$router.push({
        name: "NoticeDetail",
        params: this.updateForm.noticeId,
      });
    },
    reset() {
      this.updateForm.title = "";
      this.updateForm.content = "";
    },
    sweetAlert(type) {
      if (type === "success")
        this.$swal(type, "수정이 완료되었습니다.", "success");
      else this.$swal(type, "수정 중 문제가 발생하였습니다.", "error");
    },
  },
};
</script>

<style></style>
