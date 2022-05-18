<template>
  <article id="main">
    <header>
      <h2>Q&A</h2>
      <p>문의 등록</p>
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
                v-model="registForm.title"
                required
              />
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
  name: "QuestionCreate",
  data() {
    return {
      registForm: {
        title: "",
        content: "",
        author: "ssafy", // 나중에 수정
        status: "NOT_YET",
      },
    };
  },
  methods: {
    checkValue() {
      let err = true;
      let msg = "";
      !this.registForm.title && ((msg = "제목을 입력해주세요"), (err = false));
      err &&
        !this.registForm.content &&
        ((msg = "내용을 입력해주세요"), (err = false));
      if (!err) alert(msg);
      else this.registQuestion();
    },
    registQuestion() {
      let questionInfo = {
        title: this.registForm.title,
        content: this.registForm.content,
        author: this.registForm.author,
        status: this.registForm.status,
      };
      console.log(questionInfo);
      this.$axios
        .post("/qnas/question", questionInfo)
        .then(() => {
          alert("등록 성공");
          this.goList();
        })
        .catch(() => {
          alert("등록 실패");
        });
    },
    goList() {
      this.$router.push("/qna");
    },
  },
};
</script>

<style></style>
