import jwt_decode from "jwt-decode";
import axios from "@/api/http";

const userStore = {
  namespaced: true,
  state: {
    user: null,
    isLogin: false,
    isLoginError: false,
    //isUpdated: false,
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
    // SET_IS_UPDATED: (state) => {
    //   state.isUpdated = true;
    // },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_USER_DETAIL: (state, user) => {
      state.user = user;
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
      state.user = null;
    },
    // CLEAR_IS_UPDATED: (state) => {
    //   state.isUpdated = false;
    // },
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
        authority: decodedToken.authority,
      });
    },
    logout({ commit }) {
      sessionStorage.removeItem("access-token");
      commit("CLEAR_USER_INFO");
    },
    async getDetail({ commit }) {
      let token = {
        userToken: sessionStorage.getItem("access-token"),
      };
      let { data } = await axios.post(`user/detail`, token);
      commit("SET_USER_DETAIL", data);
    },
    async updateUser({ commit }, changeData) {
      let updateInfo = {
        userToken: sessionStorage.getItem("access-token"),
        userPwd: changeData.userPwd,
        name: changeData.name,
        email: changeData.email,
        phone: changeData.phone,
      };
      await axios
        .put(`user`, updateInfo)
        .then(({ data }) => {
          alert("회원정보가 수정되었습니다.");
          //commit("SET_IS_UPDATED");
          let token = data["userToken"];
          sessionStorage.setItem("access-token", token);
          let decodedToken = jwt_decode(token);
          commit("SET_USER_DETAIL", updateInfo);
          commit("SET_USER_INFO", {
            id: decodedToken.id,
            name: decodedToken.name,
            authority: decodedToken.authority,
          });
        })
        .catch(() => {
          alert("회원정보 수정 실패");
          // commit("CLEAR_IS_UPDATED");
        });
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
