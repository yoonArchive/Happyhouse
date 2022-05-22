import { sidoList, gugunList, dongList, houseListByDong } from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시 선택" }],
    guguns: [{ value: null, text: "구 선택" }],
    dongs: [{ value: null, text: "동 선택" }],
    houses: [],
    house: null,
    houseCnt: 0,
    markerPositions: [],
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido, text: sido });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun, text: gugun });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong, text: dong });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "시 선택" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "구 선택" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "동 선택" }];
    },
    CLEAR_HOUSE_LIST: (state) => {
      state.houses = [];
    },
    CLEAR_DETAIL_HOUSE: (state) => {
      state.house = null;
    },
    CLEAR_MARKER_POSITIONS: (state) => {
      state.markerPositions = [];
    },
    SET_HOUSE_LIST_BY_DONG: (state, houses) => {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    SET_HOUSE_COUNT: (state, length) => {
      state.houseCnt = length;
    },
    SET_MARKER_POSITIONS: (state, aptsData) => {
      state.markerPositions = aptsData.map((aptData) => [
        aptData.lat,
        aptData.lng,
      ]);
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sido) => {
      const params = {
        si: sido,
      };
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, sigu) => {
      const params = {
        si: sigu.sido,
        gu: sigu.gugun,
      };
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseListByDong: async ({ commit }, aptInfo) => {
      const params = {
        si: aptInfo.si,
        gu: aptInfo.gu,
        dong: aptInfo.dong,
      };
      await houseListByDong(
        params,
        ({ data }) => {
          commit("SET_HOUSE_LIST_BY_DONG", data);
          commit("SET_HOUSE_COUNT", data.length);
          commit("SET_MARKER_POSITIONS", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
  },
};

export default houseStore;
