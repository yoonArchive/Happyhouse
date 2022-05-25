import jwt_decode from "jwt-decode";
import axios from "@/api/http";
import router from "@/router";

const userStore = {
  namespaced: true,
  state: {
    user: null,
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    clickMyArea: false,
    clickUserModify: false,
    clickUserDelete: false,
    houseWishList: [],
    houseWishListInfos: [],
    isInWishList: false,
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
    SET_USER_DETAIL: (state, user) => {
      state.user = user;
    },
    ADD_HOUSE_WISH_LIST: (state, data) => {
      state.houseWishList.push(data);
    },
    SET_HOUSE_WISH_LIST_INFOS: (state, houseWishListInfos) => {
      state.houseWishListInfos = houseWishListInfos;
    },
    SET_IS_IN_WISH_LIST: (state) => {
      state.isInWishList = true;
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
    CLEAR_CLICK_MY_AREA: (state) => {
      state.clickMyArea = false;
    },
    CLEAR_USER_INFO: (state) => {
      state.isLogin = false;
      state.userInfo = null;
      state.user = null;
      state.clickUserDelete = false;
    },
    CLEAR_HOUSE_WISH_LIST: (state) => {
      state.houseWishList = null;
    },
    CLEAR_IS_IN_WISH_LIST: (state) => {
      state.isInWishList = false;
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
        });
    },
    async updateUserAuthority(data) {
      console.log(data);
      let updateInfo = {
        authority: data,
      };
      console.log(updateInfo);
      await axios
        .put(`user/authority`, updateInfo)
        .then(() => {
          alert("회원정보가 수정되었습니다.");
        })
        .catch(() => {
          alert("회원정보 수정 실패");
        });
    },
    async deleteUser({ commit }) {
      await axios
        .delete(`user`)
        .then(() => {
          sessionStorage.removeItem("access-token");
          commit("CLEAR_USER_INFO");
          router.push({
            name: "home",
            params: { msg: "탈퇴가 완료되었습니다." },
          });
        })
        .catch(() => {
          alert("회원 탈퇴 중 문제가 발생했습니다.");
        });
    },
    async addWishList({ commit }, aptCode) {
      console.log(aptCode + "추가");
      await axios.post(`user/like/${aptCode}`).then(({ data }) => {
        commit("ADD_HOUSE_WISH_LIST", [data.likeId, aptCode]);
        commit("SET_IS_IN_WISH_LIST");
      });
    },
    async getWishList({ commit }) {
      await axios
        .get(`user/like`)
        .then(({ data }) => {
          commit("SET_HOUSE_WISH_LIST_INFOS", data);
        })
        .catch((error) => {
          alert("목록을 불러오는 중 문제가 발생하였습니다.");
          console.log(error);
        });
    },
    async deleteWishHouse({ state, commit }, likeId) {
      await axios
        .delete(`user/like/${likeId}`)
        .then(() => {
          commit("CLEAR_IS_IN_WISH_LIST");
          let index = -1;
          for (let i = 0; i < state.houseWishList.length; i++) {
            if (state.houseWishList[i][0] == likeId) {
              index = i;
              break;
            }
          }
          state.houseWishList.splice(index, 1);
        })
        .catch(() => {
          alert("관심 매물 삭제에 실패하였습니다.");
        });
    },
    getIsInWishList({ state, commit }, aptCode) {
      let isIn = false;
      for (let i = 0; i < state.houseWishList.length; i++) {
        if (state.houseWishList[i][1] == aptCode) {
          isIn = true;
          break;
        }
      }
      if (isIn) commit("SET_IS_IN_WISH_LIST");
      else commit("CLEAR_IS_IN_WISH_LIST");
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
