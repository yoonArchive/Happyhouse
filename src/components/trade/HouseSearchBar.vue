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
          v-model="sidoCode"
          @change="gugunList"
          class="array-select form-control form-select"
          style="margin-left: 25px"
        >
          <option value="default" selected>시 선택</option>
          <option
            v-for="(item, index) in sidos"
            :key="index"
            :value="item.sidoCode"
          >
            {{ item.sidoName }}
          </option>
        </select>
        <select
          v-model="gugunCode"
          @change="dongList"
          class="array-select form-control form-select"
        >
          <option value="default" selected>구 선택</option>
          <option
            v-for="(item, index) in guguns"
            :key="index"
            :value="item.guCode"
          >
            {{ item.guName }}
          </option>
        </select>
        <select
          v-model="dongCode"
          @change="searchApt"
          class="array-select form-control form-select"
          aria-label="example"
        >
          <option value="default" selected>동 선택</option>
          <option
            v-for="(item, index) in dongs"
            :key="index"
            :value="item.dongName"
          >
            {{ item.dongName }}
          </option>
        </select>
      </div>
    </div>
    <div v-if="searchType == 'keyword'" class="input-group row">
      <input
        @keyup.enter="keywordSearch"
        type="text"
        v-model="inputKeyword"
        class="form-control d-inline-block"
        placeholder="아파트명 / 주택명 / 법정동 입력"
        style="color: gray; width: 70%; font-size: 0.8em; margin: 0 10px 0 20px"
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
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      searchType: "dong",
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
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
      "getHouseList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    gugunList() {
      //console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      // console.log(this.gugunCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchApt() {
      if (this.dongCode) this.getHouseList(this.dongCode);
    },
  },
  watch: {
    searchType: function (val) {
      this.listVisible = false;
      if (val === "dong") {
        console.log("watch dong");
        this.initSearchByDongBox();
      } else if (val === "keyword") {
        if (this.listVisible) this.listVisible = false;
        console.log("watch keyword");
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
</style>
