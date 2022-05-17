<template>
  <article id="main">
    <header>
      <h2>Notice</h2>
      <p>공지사항</p>
    </header>
    <section class="wrapper style5">
      <div class="inner">
        <h4>NOTICE</h4>
        <div class="table-wrapper">
          <table>
            <colgroup>
              <col style="width: 10%" />
              <col style="width: 45%" />
              <col style="width: 20%" />
              <col style="width: 25%" />
            </colgroup>
            <thead>
              <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(notice, index) in notices" :key="notice.noticeId">
                <td>{{ index + 1 }}</td>
                <td>
                  <router-link :to="'notice/' + notice.noticeId">{{
                    notice.title
                  }}</router-link>
                </td>
                <td>{{ notice.author }}</td>
                <td>{{ notice.createDate }}</td>
              </tr>
            </tbody>
          </table>
          <ul class="pagination">
            <li><span class="button disabled">Prev</span></li>
            <li><a href="#" class="page active">1</a></li>
            <li><a href="#" class="page">2</a></li>
            <li><a href="#" class="page">3</a></li>
            <li><span>&hellip;</span></li>
            <li><a href="#" class="page">8</a></li>
            <li><a href="#" class="page">9</a></li>
            <li><a href="#" class="page">10</a></li>
            <li><a href="#" class="button">Next</a></li>
          </ul>
        </div>
      </div>
    </section>
  </article>
</template>

<script>
export default {
  data() {
    return {
      notices: [],
    };
  },
  async created() {
    let { data } = await this.$axios.get("/notice/list/1");
    this.notices = data;
  },
};
</script>

<style></style>
