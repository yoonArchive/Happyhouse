<template>
  <article id="main">
    <header>
      <h2>Q&A</h2>
      <p>문의 조회</p>
    </header>
    <section class="wrapper style5">
      <div class="inner">
        <header>
          <h2>{{ questionForm.title }}</h2>
        </header>
        <p>{{ questionForm.author }} | {{ questionForm.createDate }}</p>
        <hr />
        <pre>{{ questionForm.content }}</pre>
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
        </div>
        <div>
          <textarea
            class="ta"
            name="comment"
            id="comment"
            placeholder="Enter comment"
            rows="3"
            required
            v-model="commentForm.content"
          ></textarea>
          <div style="float: right">
            <button type="button" class="button small" @click="addComment">
              등록
            </button>
          </div>
        </div>
        <br /><br />
        <h4>{{ lengthMsg }}</h4>
        <ul class="alt">
          <comment-list-item
            v-for="comment in comments"
            :key="comment.answerId"
            v-bind="comment"
          />
        </ul>
      </div>
    </section>
  </article>
</template>

<script>
import CommentListItem from "@/components/qna/item/CommentListItem.vue";
export default {
  components: {
    CommentListItem,
  },
  data() {
    return {
      questionForm: {
        questionId: "",
        title: "",
        content: "",
        author: "ssafy", // 나중에 수정
        createDate: "",
      },
      commentForm: {
        questionId: 0,
        author: "ssafy", // 나중에 수정
        content: "",
        createDate: "",
      },
      comments: {},
      lengthMsg: "총 0개의 댓글이 있습니다.",
    };
  },
  created() {
    this.$axios
      .get(`/qnas/${this.$route.params.questionId}`)
      .then(({ data }) => {
        console.log(data);
        this.questionForm.questionId = data.questionId;
        this.questionForm.title = data.title;
        this.questionForm.content = data.content;
        this.questionForm.author = data.author;
        this.questionForm.createDate = data.createDate;
        this.comments = data.answers;
        this.lengthMsg = `총 ${this.comments.length}개의 댓글이 있습니다.`;
      });
  },
  methods: {
    goModifyQuestion() {
      this.$router.push({
        name: "QuestionModify",
        params: this.questionForm.questionId,
      });
    },
    deleteQuestion() {
      if (confirm("정말 삭제하시겠습니까?")) {
        this.$axios
          .delete(`/qnas/question/${this.questionForm.questionId}`)
          .then(() => {
            alert("삭제되었습니다.");
            this.goList();
          });
      }
    },
    goList() {
      this.$router.push("/qna");
    },
    addComment() {
      let commentInfo = {
        questionId: this.questionForm.questionId,
        author: this.commentForm.author,
        content: this.commentForm.content,
      };
      this.$axios
        .post("/qnas/answer", commentInfo)
        .then(() => {
          alert("등록 성공");
          this.comments.push(commentInfo);
          location.reload();
        })
        .catch(() => {
          alert("등록 실패");
        });
    },
  },
};
</script>

<style></style>
