import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import vueClapButton from "vue-clap-button";
Vue.use(Vuex);
Vue.use(vueClapButton);

import houseStore from "./modules/houseStore";
import userStore from "./modules/userStore";

export default new Vuex.Store({
  modules: {
    houseStore,
    userStore,
  },

  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});
