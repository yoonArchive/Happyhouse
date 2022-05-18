<template>
  <tr>
    <td>{{ noticeId }}</td>
    <td></td>
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
    noticeId: Number,
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
