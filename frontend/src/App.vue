<template>
  <body class="landing is-preload">
    <div id="page-wrapper">
      <div id="app">
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

html,
body,
#app {
  min-width: 100%;
  min-height: 100%;
  position: absolute;
}
</style>
