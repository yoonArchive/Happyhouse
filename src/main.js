import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import http from "@/api/http";
// import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
//import "bootstrap/dist/css/bootstrap.css";
//import "bootstrap-vue/dist/bootstrap-vue.css";

Vue.prototype.$axios = http;
//Vue.use(BootstrapVue);
//Vue.use(IconsPlugin);
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
