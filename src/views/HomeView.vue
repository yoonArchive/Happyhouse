<template>
  <div>
    <!-- Banner -->
    <section id="banner">
      <div class="inner">
        <h2>Happy House</h2>
        <p>Welcome to the HappyHouse</p>
        <ul class="actions special" @click="limitAccess">
          <li>
            <router-link to="/trade" class="button primary"
            >실거래가 조회
            </router-link
            >
          </li>
        </ul>
      </div>
      <a href="#one" class="more scrolly">Show More</a>
    </section>

    <!-- One -->
<!--    <section id="one" class="wrapper style1 special">-->
<!--      <div class="inner">-->
<!--        <header class="major">-->
<!--          <h2>-->
<!--            Arcu aliquet vel lobortis ata nisl<br/>-->
<!--            eget augue amet aliquet nisl cep donec-->
<!--          </h2>-->
<!--          <p>-->
<!--            Aliquam ut ex ut augue consectetur interdum. Donec amet imperdiet-->
<!--            eleifend<br/>-->
<!--            fringilla tincidunt. Nullam dui leo Aenean mi ligula, rhoncus-->
<!--            ullamcorper.-->
<!--          </p>-->
<!--        </header>-->
<!--        <ul class="icons major">-->
<!--          <li>-->
<!--            <span class="icon fa-gem major style1"-->
<!--            ><span class="label">Lorem</span></span-->
<!--            >-->
<!--          </li>-->
<!--          <li>-->
<!--            <span class="icon fa-heart major style2"-->
<!--            ><span class="label">Ipsum</span></span-->
<!--            >-->
<!--          </li>-->
<!--          <li>-->
<!--            <span class="icon solid fa-code major style3"-->
<!--            ><span class="label">Dolor</span></span-->
<!--            >-->
<!--          </li>-->
<!--        </ul>-->
<!--      </div>-->
<!--    </section>-->

    <!-- Two -->
    <section id="two" class="wrapper alt style2">
      <section class="spotlight">
        <div class="image"><img src="@/images/main1.jpg" style="height: 417.82px; width: 555.59px;" alt=""/></div>
        <div class="content">
          <h2>
            Happy House는
          </h2>
          <p>
            현명한 주택 구입을 위한 거래내역 제공 사이트입니다.
          </p>
        </div>
      </section>
      <section class="spotlight">
        <div class="image"><img src="@/images/main2.png" style="height: 417.82px; width: 555.59px;" alt=""/></div>
        <div class="content">
          <h2>
            내 집 마련의 꿈, 거래내역 확인에서부터
          </h2>
          <p>
            2015년도부터 2021년도까지 거래내역을 기반으로<br>
            사용자가 최신 거래 정보를 비교할 수 있도록 하였습니다.<br>
            (거래내역은 공공데이터를 기반으로 매달 업데이트됩니다.)
          </p>
        </div>
      </section>
      <section class="spotlight">
        <div class="image"><img src="@/images/main3.png" style="height: 417.82px; width: 555.59px;" alt=""/></div>
        <div class="content">
          <h2>
            관심 매물 등록으로<br>
            내가 원하는 매물을 한 번에 관리
          </h2>
          <p>
            관심 매물 기능을 통해 원하는 정보만 마이페이지에서 빠르게 접근이 가능합니다.
          </p>
        </div>
      </section>
    </section>

    <!-- Three -->
    <section id="three" class="wrapper style3 special">
      <div class="inner">
        <header class="major">
          <h2>바로가기</h2>
          <p>
            Happy House의 기능을 소개합니다.
          </p>
        </header>
        <ul class="features">
          <li class="icon fa-paper-plane" @click="limitAccess">
            <router-link to="trade">
              <h3>실거래가 조회</h3>
              <p>
                법정동별 아파트의 실거래가를 확인하실 수 있습니다.
                또한 법정동명 또는 아파트명으로 검색해 내가 원하는 매물의 거래내역을 빠르게 확인하실 수 있습니다.
              </p>
            </router-link>
          </li>
          <li class="icon fa-heart" @click="limitAccess">
            <router-link to="myPage">
              <h3>관심 매물 관리</h3>
              <p>
                관심있는 매물은 '관심 매물'에 등록해 마이페이지에서 빠르게 접근하실 수 있습니다.<br>
              </p>
            </router-link>
          </li>
          <li class="icon solid fa-laptop">
            <router-link to="qna">
              <h3>Q&A</h3>
              <p>
                거래와 관련해 궁금한 점을 사이트 이용자들에게 물어보고, 다른 사용자들의 질문에 대해 답변하실 수 있습니다.
              </p>
            </router-link>
          </li>
          <li class="icon fa-flag">
            <router-link to="notice">
              <h3>공지사항</h3>
              <p>
                사이트 업데이트, 알림, 버그개선에 대한 공지사항을 확인하실 수 있습니다.
              </p>
            </router-link>
          </li>
        </ul>
      </div>
    </section>

    <!-- CTA -->
    <section id="cta" class="wrapper style4" v-if="!userInfo">
      <div class="inner">
        <header>
          <h2>Join us</h2>
          <p>
            To Find Your Happy House
          </p>
        </header>
        <ul class="actions stacked">
          <li><router-link to="/user/signup" class="button fit primary">SignUp</router-link></li>
          <li><router-link to="/user/login" class="button fit">Login</router-link></li>
        </ul>
      </div>
    </section>
  </div>
</template>

<script>
import {mapState} from "vuex";

const userStore = "userStore";

export default {
  name: "HomeView",
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    const msg = this.$route.params.msg;
    if (msg) this.$swal("success", msg, "success");
  },
  methods: {
    limitAccess() {
      if (!this.userInfo) {
        this.$swal(
            "접근 실패",
            "Happy House 회원만 접근할 수 있습니다.",
            "error"
        );
        this.$router.push("/user/login");
      }
    },
  },
};
</script>

<style scoped>
#banner {
  background-image: url("../assets/img/apt.jpg");
  background-size: cover;
}
</style>
