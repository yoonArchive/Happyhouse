import jwt_decode from "jwt-decode";
import axios from "@/api/http";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    clickMyArea: false,
    clickUserModify: false,
    clickUserDelete: false,
  },
  getters: {},
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_CLICK_MY_AREA: (state) => {
      state.clickMyArea = true;
      state.clickUserModify = false;
      state.clickUserDelete = false;
    },
    SET_CLICK_USER_MODIFY: (state) => {
      state.clickUserModify = true;
      state.clickMyArea = false;
      state.clickUserDelete = false;
    },
    SET_CLICK_USER_DELETE: (state) => {
      state.clickUserDelete = true;
      state.clickMyArea = false;
      state.clickUserModify = false;
    },
    CLEAR_CLICK_MENU: (state) => {
      state.clickUserDelete = false;
      state.clickMyArea = false;
      state.clickUserModify = false;
    },
    CLEAR_USER_INFO: (state) => {
      state.isLogin = false;
      state.userInfo = null;
    },
  },
  actions: {
    async login({ commit }, loginInfo) {
      let { data } = await axios.post(`/user/login`, loginInfo);
      console.log(data);
      let token = data["userToken"];
      console.log(token);
      sessionStorage.setItem("access-token", token);
      let decodedToken = jwt_decode(token);
      console.log("토큰 정보 :", decodedToken);
      commit("SET_USER_INFO", {
        id: decodedToken.id,
        name: decodedToken.name,
      });
    },
    logout({ commit }) {
      sessionStorage.removeItem("access-token");
      commit("CLEAR_USER_INFO");
    },
    showUserArea({ commit }) {
      commit("SET_CLICK_MY_AREA");
    },
    showUserModify({ commit }) {
      commit("SET_CLICK_USER_MODIFY");
    },
    showUserDelete({ commit }) {
      commit("SET_CLICK_USER_DELETE");
    },
  },
};
export default userStore;
