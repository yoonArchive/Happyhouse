<template>
  <li>
    <span>
      {{ author }} | {{ createDate }}
    </span>
    <span style="float: right">
      <img src="@/images/commentUpdate.png" @click="modify">
      <img src="@/images/commentDelete.png" @click="deleteAnswer">
    </span>
    <br/><br/>
    <div v-show="isNotModify" id="comment">
      {{ content }}
    </div>
    <div v-show="isModify" id="modifyForm">
        <textarea
            name="modifiedComment"
            id="modifiedComment"
            placeholder="Enter comment"
            rows="3"
            required
            v-model="modifiedContent"
        ></textarea>
      <div style="float: right">
        <button type="button" class="button small" @click="modifyAnswer">
          수정
        </button>
        <button type="button" class="button small" @click="cancel">
          취소
        </button>
      </div>
    </div>
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
  data() {
    return {
      isModify: false,
      isNotModify: true,
      modifiedContent: this.content
    }
  },
  methods: {
    modify() {
      this.isModify = true;
      this.isNotModify = false;
    },
    modifyAnswer() {
      let data = {
        answerId: this.answerId,
        content: this.modifiedContent
      }

      http.put(`/qnas/answer/${this.answerId}`, data)
          .then(({result}) => {
            this.$props.content = result;
            this.isModify = false;
            this.isNotModify = true;
            this.$swal("success", "답변이 수정되었습니다.", "success")
                .then(() => {
                  this.$emit("refresh")
                });
          })
          .catch(() => {
            this.$swal("fail", "수정 중 문제가 발생하였습니다.", "fail");
          });
    },
    cancel() {
      this.isModify = false;
      this.isNotModify = true;
    },
    deleteAnswer() {
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
            cancelButtonText: "취소"
          })
          .then((result) => {
            if (result.isConfirmed) {
              this.$axios
                  .delete(`/qnas/answer/${this.answerId}`)
                  .then(() => {
                    this.$emit("refresh");
                    this.sweetAlert("success");
                  })
                  .catch(() => this.sweetAlert("fail"));
            }
          });
    },
    sweetAlert(type) {
      if (type === "success") {
        this.$swal(type, "답변이 삭제되었습니다.", "success")
      } else {
        this.$swal(type, "삭제 중 문제가 발생하였습니다.", "error");
      }
    }
  }
};
</script>

<style>
img {
  height: 18px;
}

textarea {
  margin-bottom: 15px;
}

#modifyForm {
  margin-bottom: 65px;
}
</style>
