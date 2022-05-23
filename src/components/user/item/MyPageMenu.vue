<template>
  <section>
    <div
      class="table-wrapper"
      style="width: 130%; margin-top: 70px; margin-left: -140px"
    >
      <table class="alt">
        <tbody>
          <tr>
            <td><a>나의 관심 지역</a></td>
          </tr>
          <tr>
            <td>
              <div @click="modify"><a>회원 정보 수정</a></div>
            </td>
          </tr>
          <tr>
            <td><a>회원 탈퇴</a></td>
          </tr>
        </tbody>
      </table>
    </div>
  </section>
</template>

<script>
export default {
  methods: {
    modify() {
      console.log("modify");
    },
  },
};
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css);
section {
  font-family: "Nanum Gothic", serif;
}
tr {
  background-color: white;
  font-weight: 600;
  font-size: 0.7em;
  text-align: center;
}
</style>
