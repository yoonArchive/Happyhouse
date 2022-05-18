<template>
  <article id="main">
    <header>
      <h2>Q&A</h2>
      <p>1:1 문의</p>
    </header>
    <section class="wrapper style5">
      <div class="inner">
        <h4>Q&A</h4>
        <div class="table-wrapper">
          <table>
            <colgroup>
              <col style="width: 10%" />
              <col style="width: 45%" />
              <col style="width: 20%" />
              <col style="width: 15%" />
              <col style="width: 10%" />
            </colgroup>
            <thead>
              <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>상태</th>
              </tr>
            </thead>
            <tbody>
              <question-list-item
                v-for="(question, index) in questions"
                :key="question.questionId"
                v-bind="question"
                :index="questions.length - index"
              />
            </tbody>
          </table>
          <ul class="pagination">
            <li><span class="button small disabled">Prev</span></li>
            <li><a href="#" class="page active">1</a></li>
            <li><a href="#" class="page">2</a></li>
            <li><a href="#" class="page">3</a></li>
            <li><span>&hellip;</span></li>
            <li><a href="#" class="page">8</a></li>
            <li><a href="#" class="page">9</a></li>
            <li><a href="#" class="page">10</a></li>
            <li><a href="#" class="button small">Next</a></li>

            <button
              type="button"
              class="button small"
              id="registQuestionBtn"
              @click="moveWrite"
              style="float: right"
            >
              등록
            </button>
          </ul>

          <div class="row aln-center">
            <div class="col-7 col-12-xsmall">
              <input
                type="text"
                name="search"
                id="search"
                v-model="searchKeyWord"
                placeholder="작성자, 제목, 내용 검색"
                style="float: right width: 60%  ;"
              />
            </div>
            <div>
              <button
                type="button"
                class="button small"
                id="searchBtn"
                @click="search"
                style="float: right"
              >
                검색
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
  </article>
</template>

<script>
import QuestionListItem from "@/components/qna/item/QuestionListItem.vue";
export default {
  name: "QuestionList",
  components: {
    QuestionListItem,
  },
  data() {
    return {
      searchKeyWord: "",
      questions: [],
      //  searchedQuestions: [],
    };
  },
  created() {
    this.$axios.get(`/qnas`).then(({ data }) => {
      this.questions = data;
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "QuestionCreate" });
    },
    search() {
      this.$axios
        .get(`/qnas/search?keyword=${this.searchKeyWord}`)
        .then(({ data }) => {
          this.questions = data;
        });
    },
  },
};
</script>

<style></style>
