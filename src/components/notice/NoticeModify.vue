<template>
  <article id="main">
    <header>
      <h2>Notice</h2>
      <p>공지사항 수정</p>
    </header>
    <section class="wrapper style5">
      <div class="inner">
        <h4>Form</h4>
        <form @submit.prevent="checkValue">
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
              <select name="category" id="category" readonly>
                <option value="">- Category -</option>
                <option value="1">업데이트</option>
                <option value="1">알림</option>
                <option value="1">버그 개선</option>
              </select>
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
                <li><input type="reset" value="초기화" /></li>
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
        console.log(this.updateForm);
      });
  },
  methods: {
    checkValue() {
      let err = true;
      let msg = "";
      !this.updateForm.title && ((msg = "제목을 입력해주세요"), (err = false));
      err &&
        !this.updateForm.content &&
        ((msg = "내용을 입력해주세요"), (err = false));
      if (!err) alert(msg);
      else this.updateNotice();
    },
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
          alert("수정 성공");
          this.goDetail();
        })
        .catch(() => {
          alert("등록 실패");
        });
    },
    goDetail() {
      this.$router.push({
        name: "NoticeDetail",
        params: this.updateForm.noticeId,
      });
    },
  },
};
</script>

<style></style>
