<template>
  <article id="main">
    <header>
      <h2>Q&A</h2>
      <p>문의 등록</p>
    </header>
    <section class="wrapper style5">
      <div class="inner">
        <h4>Form</h4>
        <form @submit.prevent="registQuestion" @reset="reset">
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
    registQuestion() {
      let questionInfo = {
        title: this.registForm.title,
        content: this.registForm.content,
        author: this.registForm.author,
        status: this.registForm.status,
      };
      this.$axios
        .post("/qnas/question", questionInfo)
        .then(() => {
          this.sweetAlert("success");
          this.goList();
        })
        .catch(() => {
          this.sweetAlert("fail");
        });
    },
    goList() {
      this.$router.push("/qna");
    },
    reset() {
      this.registForm.title = "";
      this.registForm.content = "";
    },
    sweetAlert(type) {
      if (type === "success")
        this.$swal(type, "문의가 등록되었습니다.", "success");
      else this.$swal(type, "등록 중 문제가 발생하였습니다.", "error");
    },
  },
};
</script>

<style></style>
