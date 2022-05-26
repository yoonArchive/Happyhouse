<template>
  <section>
    <h4>관심 지역 목록</h4>
    <br />
    <div class="table-wrapper" v-if="houseWishListInfos.length > 0">
      <table>
        <thead>
          <tr>
            <th>주소</th>
            <th>아파트명</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(houseInfo, index) in houseWishListInfos" :key="index">
            <td>
              {{ houseInfo.sidoName }} {{ houseInfo.gugunName }}
              {{ houseInfo.dongName }}
            </td>
            <td @click="goDetail(index)">
              <a>{{ houseInfo.apartmentName }}</a>
            </td>
            <td @click="deleteItem(index)">
              <img
                src="@/assets/img/Red-Trash-Transparent-Icon.png"
                width="18"
                height="18"
              />
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-else>
      <br />
      <h3>관심 매물을 등록해주세요.</h3>
    </div>
  </section>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const userStore = "userStore";
export default {
  async created() {
    await this.getWishList();
    if (this.houseWishListInfos.length == 0) {
      this.$swal({
        className: "swal",
        icon: "warning",
        text: "등록된 관심 매물이 없습니다.",
      });
    }
  },
  methods: {
    ...mapActions(userStore, ["getWishList", "deleteWishHouse"]),
    ...mapMutations(userStore, ["CLEAR_CLICK_MY_AREA"]),
    goDetail(index) {
      let aptCode = this.houseWishListInfos[index].aptCode;
      this.$router.push({
        name: "View",
        params: { code: `${aptCode}`, index: `${index}` },
      });
    },
    deleteItem(index) {
      let house = this.houseWishListInfos[index];
      this.$swal
        .fire({
          text: `${house.apartmentName}을 삭제하시겠습니까?`,
          icon: "warning",
          showCancelButton: true,
          confirmButtonText: "삭제",
          cancelButtonText: "취소",
        })
        .then(async (result) => {
          if (result.value) {
            await this.deleteWishHouse(house.likeId);
            this.getWishList();
          }
        });
    },
  },
  computed: {
    ...mapState(userStore, ["houseWishListInfos"]),
  },
  destroyed() {
    this.CLEAR_CLICK_MY_AREA();
  },
};
</script>

<style></style>
