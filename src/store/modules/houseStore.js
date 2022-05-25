import {
  sidoList,
  gugunList,
  dongList,
  showDetail,
  houseListByDong,
  ListByKeyword,
} from "@/api/house.js";

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
    houseInfo: null,
    houseDeals: [],
    keywordList: [],
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
    SET_HOUSE: (state, house) => {
      state.house = house;
    },
    SET_HOUSE_INFO: (state, houseInfo) => {
      state.houseInfo = houseInfo;
    },
    SET_HOUSE_DEALS: (state, houseDeals) => {
      state.houseDeals = houseDeals;
    },
    SET_KEYWORD_LIST: (state, keywordResults) => {
      state.keywordList = keywordResults;
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
    CLEAR_KEYWORD_LIST: (state) => {
      state.keywordList = null;
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
      console.log(aptsData);
      state.markerPositions = aptsData.map((aptData) => [
        aptData.lat,
        aptData.lng,
      ]);
    },
    SET_MARKER_POSITIONS_OF_MY_AREA: (state, aptsData) => {
      console.log(aptsData);
      state.markerPositions = [aptsData.lat, aptsData.lng];
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
      console.log(params);
      await houseListByDong(
        params,
        ({ data }) => {
          console.log(data);
          commit("SET_HOUSE_LIST_BY_DONG", data);
          commit("SET_HOUSE_COUNT", data.length);
          commit("SET_MARKER_POSITIONS", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getListByKeyword: async ({ commit }, keyword) => {
      const params = {
        keyword: keyword,
      };
      await ListByKeyword(
        params,
        ({ data }) => {
          commit("SET_KEYWORD_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailHouse: ({ commit }, aptCode) => {
      console.log("detail");
      showDetail(
        aptCode,
        ({ data }) => {
          // 해당 아파트에 대한 모든 실거래가 정보
          let houseInfoData = {
            apartmentName: data.apartmentName,
            aptCode: data.aptCode,
            baseAddress: data.baseAddress,
            roadBasedAddress: data.roadBasedAddress,
            dongCode: data.dongCode,
            buildYear: data.buildYear,
          };
          console.log(houseInfoData);
          commit("SET_HOUSE", houseInfoData.aptCode);
          commit("SET_HOUSE_INFO", houseInfoData);
          commit("SET_HOUSE_DEALS", data.houseDeals);
        },
        (error) => {
          console.log(error);
        }
      );
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", aptCode);
    },
  },
};

export default houseStore;
