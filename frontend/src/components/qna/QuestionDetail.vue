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
          <div>
            <ul class="actions aln-center">
              <li>
                <button v-show="userInfo != null && (userInfo.id === questionForm.author || userInfo.authority === '관리자')"
                  type="button"
                  class="primary"
                  id="modifyBtn"
                  @click="goModifyQuestion"
                >
                  수정
                </button>
              </li>
              <li>
                <button v-show="userInfo != null && (userInfo.id === questionForm.author || userInfo.authority === '관리자')"
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
        <div v-show="userInfo">
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
            @refresh="initialize"
          />
        </ul>
      </div>
    </section>
  </article>
</template>

<script>
import CommentListItem from "@/components/qna/item/CommentListItem.vue";
import {mapState} from "vuex";

const userStore = "userStore";

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
        author: "",
        createDate: "",
      },
      commentForm: {
        questionId: 0,
        author: "",
        content: "",
        createDate: "",
      },
      comments: {},
    };
  },
  created() {
    this.initialize();
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    lengthMsg() {
      return `총 ${this.comments.length}개의 댓글이 있습니다.`;
    },
  },
  methods: {
    initialize() {
      this.$axios
        .get(`/qnas/${this.$route.params.questionId}`)
        .then(({ data }) => {
          this.questionForm.questionId = data.questionId;
          this.questionForm.title = data.title;
          this.questionForm.content = data.content;
          this.questionForm.author = data.author;
          this.questionForm.createDate = data.createDate;
          this.comments = data.answers;
        });
    },
    goModifyQuestion() {
      this.$router.push({
        name: "QuestionModify",
        params: this.questionForm.questionId,
      });
    },
    deleteQuestion() {
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
              .delete(`/qnas/question/${this.questionForm.questionId}`)
              .then(() => {
                this.sweetAlert("success", true);
                this.goList();
              })
              .catch(() => this.sweetAlert("fail", true));
          }
        });
    },
    goList() {
      this.$router.push("/qna");
    },
    addComment() {
      if (this.commentForm.content === "") {
        this.$swal("내용을 입력하세요.");
        return;
      }
      let commentInfo = {
        questionId: this.questionForm.questionId,
        author: this.userInfo.id,
        content: this.commentForm.content,
      };
      this.$axios
        .post("/qnas/answer", commentInfo)
        .then(() => {
          this.initialize();
          this.commentForm.content = "";
        })
        .catch(() => {
          this.sweetAlert("fail", false);
        });
    },
    sweetAlert(type, isQuestion) {
      if (isQuestion) {
        if (type === "success")
          this.$swal(type, "문의가 삭제되었습니다.", "success");
        else this.$swal(type, "삭제 중 문제가 발생하였습니다.", "error");
      } else {
        this.$swal(type, "댓글 등록 중 문제가 발생하였습니다.", "error");
      }
    },
  },
};
</script>

<style></style>
