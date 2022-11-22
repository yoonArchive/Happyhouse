<template>
  <article id="main">
    <header>
      <h2>Q&A</h2>
      <p>Happy House와 관련하여 궁금하신 점을 남겨주세요.</p>
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
              <col style="width: 10%" />
              <col style="width: 15%" />
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
<!--            <li><span class="button small disabled">Prev</span></li>-->
<!--            <li><a href="#" class="page active">1</a></li>-->
<!--            <li><a href="#" class="page">2</a></li>-->
<!--            <li><a href="#" class="page">3</a></li>-->
<!--            <li><span>&hellip;</span></li>-->
<!--            <li><a href="#" class="page">8</a></li>-->
<!--            <li><a href="#" class="page">9</a></li>-->
<!--            <li><a href="#" class="page">10</a></li>-->
<!--            <li><a href="#" class="button small">Next</a></li>-->

            <button
              type="button"
              class="button small"
              id="registQuestionBtn"
              @click="moveWrite"
              style="float: right"
              v-show="userInfo"
            >
              등록
            </button>
          </ul>
          <br><br>
          <div id="searchBar" class="row aln-center">
            <div class="col-2 col-12-xsmall">
              <select v-model="searchCategory">
                <option value="">전체</option>
                <option value="title">제목</option>
                <option value="content">내용</option>
                <option value="author">작성자</option>
              </select>
            </div>
            <div class="col-6 col-12-xsmall">
              <input
                type="text"
                name="search"
                id="search"
                v-model="searchKeyword"
                placeholder="작성자, 제목, 내용 검색"
                style="float: right width: 60%  ;"
              />
            </div>
            <div id="searchBtnFrom" class="col-2 col-12-xsmall">
              <button
                type="button"
                class="button small"
                id="searchBtn"
                @click="search"
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
import {mapState} from "vuex";

const userStore = "userStore";

export default {
  name: "QuestionList",
  components: {
    QuestionListItem,
  },
  data() {
    return {
      searchCategory: "",
      searchKeyword: "",
      questions: [],
      //  searchedQuestions: [],
    };
  },
  created() {
    this.$axios.get(`/qnas`).then(({ data }) => {
      this.questions = data;
    });
  },
  computed: {
    ...mapState(userStore, ["userInfo"])
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "QuestionCreate" });
    },
    search() {
      console.log(this.searchCategory);
      let url = "/qnas/search";
      if (this.searchCategory !== "") {
        url += "/" + this.searchCategory;
      }
      this.$axios
        .get(url + "?keyword=" + this.searchKeyword)
        .then(({ data }) => {
          this.questions = data;
        });
    },
  },
};
</script>

<style>
#searchBar {
  height: 65px;
}

select {
  width: 150px;
  float: right;
  margin-right: -20px;
}

#searchBtnFrom {
  margin-left: -20px;
  margin-top: 2px;
}
</style>
