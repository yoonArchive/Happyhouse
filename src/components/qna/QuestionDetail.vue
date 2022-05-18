<template>
  <article id="main">
    <header>
      <h2>Q&A</h2>
      <p>문의 조회</p>
    </header>
    <section class="wrapper style5">
      <div class="inner">
        <header>
          <h2>{{ question.title }}</h2>
        </header>
        <p>{{ question.author }} | {{ question.createDate }}</p>
        <hr />
        <pre>{{ question.content }}</pre>
        <div class="row gtr-uniform aln-center">
          <div></div>
          <div class="col-6">
            <ul class="actions">
              <li>
                <button
                  type="button"
                  class="primary"
                  id="modifyBtn"
                  @click="goModifyQuestion"
                >
                  수정
                </button>
              </li>
              <li>
                <button
                  type="button"
                  class="button"
                  id="deleteQuestionBtn"
                  @click="deleteQuestion"
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
          <div></div>
          <Comment />
        </div>
      </div>
    </section>
  </article>
</template>

<script>
import Comment from "@/components/qna/Comment.vue";
export default {
  components: {
    Comment,
  },
  data() {
    return {
      question: {},
    };
  },
  created() {
    this.$axios
      .get(`/qnas/${this.$route.params.questionId}`)
      .then(({ data }) => {
        this.question = data;
      });
  },
  methods: {
    goModifyQuestion() {
      this.$router.push({
        name: "QuestionModify",
        params: this.$route.params.questionId,
      });
    },
    deleteQuestion() {
      if (confirm("정말 삭제하시겠습니까?")) {
        this.$axios
          .delete(`/qnas/${this.$route.params.questionId}`)
          .then(() => {
            alert("삭제되었습니다.");
            this.goList();
          });
      }
    },
    goList() {
      this.$router.push("/qna");
    },
  },
};
</script>

<style></style>
