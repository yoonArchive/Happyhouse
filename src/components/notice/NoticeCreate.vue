<template>
  <article id="main">
    <header>
      <h2>Notice</h2>
      <p>공지사항 등록</p>
    </header>
    <section class="wrapper style5">
      <div class="inner">
        <h4>Form</h4>
        <form @submit.prevent="registNotice" @reset="reset">
          <div class="row gtr-uniform aln-center">
            <div class="col-12 col-12-xsmall">
              <input
                type="text"
                name="title"
                id="title"
                placeholder="Enter Title"
                v-model="registForm.title"
                required
              />
            </div>
            <div class="col-12">
              <select
                name="category"
                id="category"
                v-model="registForm.category"
              >
                <option value="">- Category -</option>
                <option value="업데이트">업데이트</option>
                <option value="알림">알림</option>
                <option value="버그개선">버그개선</option>
              </select>
            </div>
            <div class="col-12">
              <textarea
                name="content"
                id="content"
                placeholder="Enter Content"
                rows="10"
                v-model="registForm.content"
                required
              ></textarea>
            </div>
            <div></div>
            <div class="col-6">
              <ul class="actions">
                <li>
                  <button class="primary" type="submit">등록</button>
                </li>
                <li><input type="reset" value="초기화" /></li>
                <li>
                  <button type="button" @click="goList">취소</button>
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
  name: "NoticeCreate",
  data() {
    return {
      registForm: {
        title: "",
        category: "",
        content: "",
        author: "admin", // 나중에 수정
      },
    };
  },
  methods: {
    registNotice() {
      let noticeInfo = {
        title: this.registForm.title,
        category: this.registForm.category,
        content: this.registForm.content,
        author: this.registForm.author,
      };
      this.$axios
        .post("/notice", noticeInfo)
        .then(() => {
          this.sweetAlert("success");
          this.goList();
        })
        .catch(() => {
          this.sweetAlert("fail");
        });
    },
    goList() {
      this.$router.push("/notice");
    },
    reset() {
      this.registForm.title = "";
      this.registForm.content = "";
      this.registForm.category = "";
    },
    sweetAlert(type) {
      if (type === "success")
        this.$swal(type, "공지사항이 등록되었습니다.", "success");
      else this.$swal(type, "등록 중 문제가 발생하였습니다.", "error");
    },
  },
};
</script>

<style></style>
