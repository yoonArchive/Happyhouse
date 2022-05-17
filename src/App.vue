<template>
  <body class="landing is-preload">
    <div id="page-wrapper">
      <div id="app">
        <!-- <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </nav> -->
        <Nav />
        <router-view />
        <Footer />
      </div>
    </div>
  </body>
</template>
<script>
import Nav from "@/components/common/Nav.vue";
import Footer from "@/components/common/Footer.vue";

export default {
  components: {
    Nav,
    Footer,
  },
};
</script>

<style>
@import "@/assets/css/main.css";
@import "@/assets/css/noscript.css";
</style>
