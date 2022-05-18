<template>
  <article id="main">
    <header>
      <h2>Q&A</h2>
      <p>문의 수정</p>
    </header>
    <section class="wrapper style5">
      <div class="inner">
        <h4>Form</h4>
        <form @submit.prevent="checkValue" @reset="reset">
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
  name: "QnaModify",
  data() {
    return {
      updateForm: {
        questionId: 0,
        title: "",
        content: "",
        author: "", // 나중에 수정
        createDate: "",
        status: "",
      },
    };
  },
  created() {
    this.$axios
      .get(`/qnas/${this.$route.params.questionId}`)
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
      else this.updateQuestion();
    },
    updateQuestion() {
      let questionInfo = {
        questionId: this.updateForm.questionId,
        title: this.updateForm.title,
        content: this.updateForm.content,
        author: this.updateForm.author,
        createDate: this.updateForm.createDate,
        status: this.updateForm.status,
      };
      console.log(questionInfo);
      this.$axios
        .put(`/qnas/qusetion/${this.updateForm.questionId}`, questionInfo)
        .then(() => {
          alert("수정 성공");
          this.goDetail();
        })
        .catch(() => {
          alert("수정 실패");
        });
    },
    goDetail() {
      this.$router.push({
        name: "QuestionDetail",
        params: this.updateForm.questionId,
      });
    },
    reset() {
      this.updateForm.title = "";
      this.updateForm.content = "";
    },
  },
};
</script>

<style></style>
