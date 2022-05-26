<template>
  <div>
    <div class="row">
      <i class="fa fa-search" style="color: black"></i>
      <h5>검색 방법을 선택하세요</h5>
    </div>
    <div class="row">
      <div class="form-check">
        <input
          value="dong"
          v-model="searchType"
          class="form-check-input"
          type="radio"
          id="searchByDong"
          name="demo-priority"
        />
        <label class="form-check-label" for="searchByDong">동 검색</label>
      </div>
      <div class="form-check">
        <input
          value="keyword"
          v-model="searchType"
          class="form-check-input"
          type="radio"
          id="searchByKeyword"
          name="demo-priority"
        />
        <label class="form-check-label" for="searchByKeyword"
          >키워드 검색</label
        >
      </div>
    </div>
    <div v-if="searchType == 'dong'" class="pb-2 d-flex justify-content-evenly">
      <div class="row">
        <select
          v-model="sidoName"
          @change="gugunList"
          class="array-select form-control form-select"
          style="margin-left: 25px; width: 30%"
        >
          <option
            v-for="(item, index) in sidos"
            :key="index"
            :value="item.value"
          >
            {{ item.text }}
          </option>
        </select>
        <select
          v-model="gugunName"
          @change="dongList"
          class="array-select form-control form-select"
        >
          <option
            v-for="(item, index) in guguns"
            :key="index"
            :value="item.value"
          >
            {{ item.text }}
          </option>
        </select>
        <select
          v-model="dongName"
          @change="searchHouseListByDong"
          class="array-select form-control form-select"
          aria-label="example"
        >
          <option
            v-for="(item, index) in dongs"
            :key="index"
            :value="item.value"
          >
            {{ item.text }}
          </option>
        </select>
      </div>
    </div>
    <!--@keyup.enter="search"-->
    <div v-if="searchType == 'keyword'" class="input-group row">
      <input
        type="text"
        v-model="inputKeyword"
        class="form-control d-inline-block"
        placeholder="아파트 또는 법정동 입력"
        style="color: gray; width: 70%; font-size: 0.8em; margin: 0 10px 0 20px"
        @keyup.enter="keywordSearch"
      />
      <button
        @click="keywordSearch"
        class="button"
        type="button"
        style="
          color: black;
          width: 70px;
          padding: 0 1em;
          margin: 0 0 0 0;
          font-size: 0.7em;
          height: 3.125em;
        "
      >
        검색
      </button>
      <br /><br />
      <div
        v-if="listVisible"
        style="height: 440px; overflow: auto; font-size: 12px; color: black"
      >
        <table class="keywordSearchTable">
          <thead>
            <th style="font-size: 13px; color: black; width: 55%">주소</th>
            <th style="font-size: 13px; color: black; width: 45%">아파트명</th>
          </thead>
          <tbody>
            <tr v-for="(element, index) in keywordList" :key="index">
              <td>
                {{ element.sidoName }} {{ element.gugunName }}
                {{ element.dongName }}
              </td>
              <td @click="selectApt(index)">
                <a>{{ element.apartmentName }}</a>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";
const userStore = "userStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      searchType: "dong",
      sidoName: null,
      gugunName: null,
      dongName: null,
      inputKeyword: null,
      listVisible: false,
    };
  },
  computed: {
    ...mapState(houseStore, [
      "sidos",
      "guguns",
      "dongs",
      "houses",
      "houseCnt",
      "keywordList",
    ]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseListByDong",
      "getListByKeyword",
      "detailHouse",
    ]),
    ...mapActions(userStore, ["getIsInWishList"]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_HOUSE_LIST",
      "CLEAR_KEYWORD_LIST",
      "CLEAR_MARKER_POSITIONS",
      "SET_MARKER_POSITIONS",
      "SET_HOUSE_LIST_BY_DONG",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.gugunName = null;
      this.dongName = null;
      if (this.sidoName) this.getGugun(this.sidoName);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongName = null;
      let sigu = {
        sido: this.sidoName,
        gugun: this.gugunName,
      };
      if (this.gugunName) this.getDong(sigu);
    },
    async searchHouseListByDong() {
      if (this.dongName) {
        this.CLEAR_MARKER_POSITIONS();
        this.CLEAR_HOUSE_LIST();
        const aptInfo = {
          si: this.sidoName,
          gu: this.gugunName,
          dong: this.dongName,
        };
        await this.getHouseListByDong(aptInfo);
        if (this.houseCnt === 0) {
          this.$swal("fail", "거래된 아파트가 없습니다.", "error");
          return;
        }
        this.$emit("setMarker");
      }
    },
    keywordSearch() {
      this.listVisible = true;
      this.getListByKeyword(this.inputKeyword);
    },
    selectApt(index) {
      this.CLEAR_MARKER_POSITIONS();
      this.CLEAR_HOUSE_LIST();
      this.detailHouse(this.keywordList[index].aptCode);
      this.getIsInWishList(this.keywordList[index].aptCode);
      console.log([this.keywordList[index]]);
      this.SET_MARKER_POSITIONS([this.keywordList[index]]);
      this.SET_HOUSE_LIST_BY_DONG([this.keywordList[index]]);
      this.$emit("setMarker");
    },
  },
  watch: {
    searchType: function (val) {
      this.listVisible = false;
      if (val === "dong") {
        this.listVisible = false;
        this.inputKeyword = "";
      } else if (val === "keyword") {
        this.CLEAR_KEYWORD_LIST();
        if (this.listVisible) this.listVisible = false;
      }
    },
  },
};
</script>
<style scoped>
#searchBox {
  position: absolute;
  top: 9%;
  left: 0.5%;
  width: 25%;
  padding: 15px;
  z-index: 100;
  background-color: rgba(241, 239, 239, 0.8);
  overflow-y: auto;
}

.form-check-label {
  color: black;
  font-size: 0.8em;
  font-weight: 600;
}
h5 {
  color: black;
}
select {
  height: 3.125em;
  line-height: 3.125em;
  width: 23%;
  color: black;
  font-family: "Open Sans", Helvetica, sans-serif;
  font-size: 0.8em;
  font-weight: 600;
  letter-spacing: 0.075em;
  margin-left: 10px;
  margin-right: 10px;
  text-align: center;
  padding: 0 0.5em;
}
.keywordSearchTable {
  margin-right: 75px;
  width: 100%;
}
</style>
