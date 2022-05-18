<template>
  <tr>
    <td>{{ questionId }}</td>
    <td>
      <router-link
        :to="{ name: 'QuestionDetail', params: { questionId: questionId } }"
        >{{ title }}</router-link
      >
    </td>
    <td>{{ author }}</td>
    <td>{{ createDate | dateFormat }}</td>
    <td>{{ status }}</td>
  </tr>
</template>

<script>
import moment from "moment";

export default {
  name: "QuestionListItem",
  props: {
    questionId: Number,
    author: String,
    title: String,
    createDate: String,
    status: String,
  },
  filters: {
    dateFormat(createDate) {
      return moment(new Date(createDate)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
