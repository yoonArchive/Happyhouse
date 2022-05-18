<template>
  <li>
    {{ author }} | {{ createDate }}<br /><br />

    {{ content }}
  </li>
  <!--<hr />
    <div>
      <textarea
        class="ta"
        name="content"
        id="content"
        placeholder="Enter comment"
        rows="3"
        v-model="commentForm.content"
        required
      ></textarea>
      <div style="float: right">
        <button type="button" class="button small" @click="addComment">
          등록
        </button>
      </div>
      <br /><br />
      <section>
        <h4>comments</h4>
        <div class="row">
          <div class="col-6 col-12-medium">
            <h5>총 -개의 댓글이 있습니다.</h5>
            <ul class="alt">
              <li v-for="comment in comments" :key="comment.answerId">
                <hr />
                {{ comment.author }} | {{ comment.createDate }}
                {{ comment.content }}
                <hr />
              </li>
              <li>Dolor pulvinar etiam.</li>
              <li>Sagittis adipiscing.</li>
              <li>Felis enim feugiat.</li> 
            </ul>
          </div>
        </div>
      </section>
    </div>-->
</template>

<script>
export default {
  name: "CommentListItem",
  props: {
    answerId: Number,
    author: String,
    content: String,
    createDate: String,
    questionId: Number,
  },
  /*data() {
    return {
      comments: [],
      commentForm: {
        questionId: 0,
        author: "ssafy", // 나중에 수정
        content: "",
      },
    };
  },
  created() {
    this.$axios.get(`/qnas`).then(({ data }) => {
      this.questions = data;
    });
  },
  methods: {
    addComment() {
      let commentInfo = {
        questionId: this.$route.params.questionId,
        author: this.commentForm.author,
        content: this.commentForm.content,
      };
      console.log(commentInfo);
      this.$axios
        .post("/qnas/answer", commentInfo)
        .then(() => {
          alert("등록 성공");
          this.comments.push(commentInfo);
        })
        .catch(() => {
          alert("등록 실패");
        });
    },
  },*/
};
</script>

<style></style>
