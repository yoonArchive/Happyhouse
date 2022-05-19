<template>
  <li>
    <span>
      {{ author }} | {{ createDate }}
    </span>
    <span style="float: right">
      <img src="@/images/commentUpdate.png">
      <img src="@/images/commentDelete.png" @click="deleteAnswer">
    </span>
    <br /><br />

    {{ content }}
  </li>
</template>

<script>
import http from "@/api/http.js";

export default {
  name: "CommentListItem",
  props: {
    answerId: Number,
    author: String,
    content: String,
    createDate: String,
    questionId: Number,
  },
  methods: {
    deleteAnswer() {
      http.delete(`/qnas/answer/${this.answerId}`)
          .then(() => {
            alert("답변이 삭제되었습니다.");
            location.reload();
          }).catch((error) => {
        alert(error.message);
      });
    }
  }
};
</script>

<style>
img {
  height: 18px;
}
</style>
