<template>
  <article id="main">
    <header>
      <h2>Notice</h2>
      <p>최신 Happy House의 소식을 확인하실 수 있습니다.</p>
    </header>
    <section class="wrapper style5">
      <div class="inner">
        <h4>NOTICE</h4>
        <div class="table-wrapper">
          <table>
            <colgroup>
              <col style="width: 10%" />
              <col style="width: 10%" />
              <col style="width: 45%" />
              <col style="width: 20%" />
              <col style="width: 15%" />
            </colgroup>
            <thead>
              <tr>
                <th>번호</th>
                <th>분류</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
              </tr>
            </thead>
            <tbody>
              <notice-list-item
                v-for="(notice, index) in notices"
                :key="notice.noticeId"
                v-bind="notice"
                :index="notices.length - index"
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
              v-show="userInfo != null && userInfo.authority === '관리자'"
              type="button"
              class="button small"
              id="registNoticeBtn"
              @click="moveWrite"
              style="float: right"
            >
              등록
            </button>
          </ul>
        </div>
      </div>
    </section>
  </article>
</template>

<script>
import { mapState } from "vuex";
import NoticeListItem from "@/components/notice/item/NoticeListItem.vue";

const userStore = "userStore";

export default {
  name: "NoticeList",
  components: {
    NoticeListItem,
  },
  data() {
    return {
      notices: [],
    };
  },
  created() {
    this.$axios.get(`/notice/list/1`).then(({ data }) => {
      this.notices = data;
    });
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "NoticeCreate" });
    },
  },
};
</script>

<style></style>
