import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import vueClapButton from "vue-clap-button";
Vue.use(Vuex);
Vue.use(vueClapButton);

import houseStore from "./modules/houseStore";

export default new Vuex.Store({
  modules: {
    houseStore,
  },

  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});
