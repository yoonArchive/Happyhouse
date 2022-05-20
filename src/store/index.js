import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

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
