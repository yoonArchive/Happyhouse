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
      <a href="#one" class="more scrolly">Learn More</a>
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
            Magna primis lobortis<br/>
            sed ullamcorper
          </h2>
          <p>
            Aliquam ut ex ut augue consectetur interdum. Donec hendrerit
            imperdiet. Mauris eleifend fringilla nullam aenean mi ligula.
          </p>
        </div>
      </section>
      <section class="spotlight">
        <div class="image"><img src="@/images/main3.png" style="height: 417.82px; width: 555.59px;" alt=""/></div>
        <div class="content">
          <h2>
            Tortor dolore feugiat<br/>
            elementum magna
          </h2>
          <p>
            Aliquam ut ex ut augue consectetur interdum. Donec hendrerit
            imperdiet. Mauris eleifend fringilla nullam aenean mi ligula.
          </p>
        </div>
      </section>
      <section class="spotlight">
        <div class="image"><img src="@/images/main2.png" style="height: 417.82px; width: 555.59px;" alt=""/></div>
        <div class="content">
          <h2>
            Augue eleifend aliquet<br/>
            sed condimentum
          </h2>
          <p>
            Aliquam ut ex ut augue consectetur interdum. Donec hendrerit
            imperdiet. Mauris eleifend fringilla nullam aenean mi ligula.
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
            Aliquam ut ex ut augue consectetur interdum. Donec amet imperdiet
            eleifend<br/>
            fringilla tincidunt. Nullam dui leo Aenean mi ligula, rhoncus
            ullamcorper.
          </p>
        </header>
        <ul class="features">
          <li class="icon fa-heart">
            <h3>Aenean Primis</h3>
            <p>
              Augue consectetur sed interdum imperdiet et ipsum. Mauris lorem
              tincidunt nullam amet leo Aenean ligula consequat consequat.
            </p>
          </li>
          <li class="icon fa-paper-plane">
            <h3>실거래가 조회</h3>
            <p>
              Augue consectetur sed interdum imperdiet et ipsum. Mauris lorem
              tincidunt nullam amet leo Aenean ligula consequat consequat.
            </p>
          </li>
          <li class="icon solid fa-laptop">
            <h3>Q&A</h3>
            <p>
              Augue consectetur sed interdum imperdiet et ipsum. Mauris lorem
              tincidunt nullam amet leo Aenean ligula consequat consequat.
            </p>
          </li>
          <li class="icon fa-flag">
            <h3>공지사항</h3>
            <p>
              Augue consectetur sed interdum imperdiet et ipsum. Mauris lorem
              tincidunt nullam amet leo Aenean ligula consequat consequat.
            </p>
          </li>
        </ul>
      </div>
    </section>

    <!-- CTA -->
    <section id="cta" class="wrapper style4">
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
