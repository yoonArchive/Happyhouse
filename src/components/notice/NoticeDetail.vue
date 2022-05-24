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
        <p> 관리자 | {{ notice.createDate }}</p>
        <hr />
        <pre>{{ notice.content }}</pre>
        <div class="row gtr-uniform aln-center">
          <div>
            <ul class="actions aln-center">
              <li>
                <button v-show="userInfo.authority==='관리자'"
                  type="button"
                  class="primary"
                  id="modifyBtn"
                  @click="goModifyNotice"
                >
                  수정
                </button>
              </li>
              <li>
                <button v-show="userInfo.authority === '관리자'"
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
import {mapState} from "vuex";

const userStore = "userStore";

export default {
  data() {
    return {
      notice: {},
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"])
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
      this.$swal
        .fire({
          title: "정말 삭제하시겠습니까?",
          text: "삭제를 원하시면 OK를 클릭해주세요.",
          icon: "warning",
          closeOnClickOutSide: false,
          showCancelButton: true,
          confirmButtonColor: "#408fff",
          cancelButtonColor: "#ed4933",
          confirmButtonText: "OK",
          cancelButtonText: "취소",
        })
        .then((result) => {
          if (result.isConfirmed) {
            this.$axios
              .delete(`/notice/${this.$route.params.noticeId}`)
              .then(() => {
                this.sweetAlert("success");
                this.goList();
              })
              .catch(() => this.sweetAlert("fail"));
          }
        });
    },
    goList() {
      this.$router.push("/notice");
    },
    sweetAlert(type) {
      if (type === "success")
        this.$swal(type, "공지사항이 삭제되었습니다.", "success");
      else this.$swal(type, "삭제 중 문제가 발생하였습니다.", "error");
    },
  },
};
</script>

<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css);

pre,
button {
  font-family: "Nanum Gothic", serif;
}
</style>
