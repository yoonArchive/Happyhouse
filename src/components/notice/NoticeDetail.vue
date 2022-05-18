<template>
  <article id="main">
    <header>
      <h2>Notice</h2>
      <p>공지사항 조회</p>
    </header>
    <section class="wrapper style5">
      <div class="inner">
        <header>
          <h2 class="s">{{ notice.category }}</h2>
          <h2>{{ notice.title }}</h2>
        </header>
        <p>{{ notice.author }} | {{ notice.createDate }}</p>
        <hr />
        <pre>{{ notice.content }}</pre>
        <div class="row gtr-uniform aln-center">
          <div></div>
          <div class="col-6">
            <ul class="actions">
              <li>
                <button
                  type="button"
                  class="primary"
                  id="modifyBtn"
                  @click="goModifyNotice"
                >
                  수정
                </button>
              </li>
              <li>
                <button
                  type="button"
                  class="button"
                  id="deleteNoticeBtn"
                  @click="deleteNotice"
                >
                  삭제
                </button>
              </li>
              <li>
                <button
                  type="button"
                  class="button"
                  id="goListBtn"
                  @click="goList"
                >
                  목록
                </button>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </section>
  </article>
</template>

<script>
export default {
  data() {
    return {
      notice: {},
    };
  },
  created() {
    this.$axios
      .get(`/notice/${this.$route.params.noticeId}`)
      .then(({ data }) => {
        this.notice = data;
      });
  },
  methods: {
    goModifyNotice() {
      this.$router.push({
        name: "NoticeModify",
        params: this.$route.params.noticeId,
      });
    },
    deleteNotice() {
      if (confirm("정말 삭제하시겠습니까?")) {
        this.$axios
          .delete(`/notice/${this.$route.params.noticeId}`)
          .then(() => {
            alert("삭제되었습니다.");
            this.goList();
          });
      }
    },
    goList() {
      this.$router.push("/notice");
    },
  },
};
</script>

<style></style>
