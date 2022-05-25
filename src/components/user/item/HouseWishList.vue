<template>
  <section>
    <h4>관심 지역 목록</h4>
    <div class="table-wrapper">
      <table>
        <thead>
          <tr>
            <th>주소</th>
            <th>아파트명</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(houseInfo, index) in houseWishListInfos" :key="index">
            <td>
              {{ houseInfo.sidoName }} {{ houseInfo.gugunName }}
              {{ houseInfo.dongName }}
            </td>
            <td @click="goDetail">
              <a>{{ houseInfo.apartmentName }}</a>
            </td>
            <td @click="deleteItem(index)">삭제</td>
          </tr>
        </tbody>
      </table>
    </div>
  </section>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";
const houseStore = "houseStore";
export default {
  created() {
    this.getWishList();
  },
  methods: {
    ...mapActions(userStore, ["getWishList", "deleteWishHouse"]),
    goDetail() {},
    deleteItem(index) {
      let house = this.houseWishListInfos[index];
      this.deleteWishHouse(house.likeId);
    },
  },
  computed: {
    ...mapState(houseStore, ["houseWishListInfos"]),
  },
};
</script>

<style></style>
