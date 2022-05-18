<template>
  <tr>
    <td>{{ index }}</td>
    <td>{{ category }}</td>
    <td>
      <router-link
        :to="{ name: 'NoticeDetail', params: { noticeId: noticeId } }"
        >{{ title }}</router-link
      >
    </td>
    <td>{{ author }}</td>
    <td>{{ createDate | dateFormat }}</td>
  </tr>
</template>

<script>
import moment from "moment";

export default {
  name: "NoticeListItem",
  props: {
    index: Number,
    noticeId: Number,
    category: String,
    author: String,
    title: String,
    createDate: String,
  },
  filters: {
    dateFormat(createDate) {
      return moment(new Date(createDate)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
