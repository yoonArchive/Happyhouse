<template>
  <div>
    <div v-if="house">
      <div id="detailBox" class="card">
        <div class="table-wrapper">
          <img src="@/assets/img/apt.jpg" alt="My Image" height="180" />
          <div class="row">
            <h4>{{ houseInfo.apartmentName }}</h4>
            <vue-clap-button
              class="icon"
              icon="love"
              :size="20"
            ></vue-clap-button>
          </div>
          <table>
            <tbody id="aptDetail">
              <tr style="background-color: white">
                <td style="font-weight: 700; text-align: center">지번 주소</td>
                <td>{{ houseInfo.baseAddress }}</td>
              </tr>
              <tr style="background-color: white">
                <td style="font-weight: 700; text-align: center">
                  도로명 주소
                </td>
                <td>{{ houseInfo.roadBasedAddress }}</td>
              </tr>
              <tr style="background-color: white">
                <td style="font-weight: 700; text-align: center">건축년도</td>
                <td>{{ houseInfo.buildYear }}</td>
              </tr>
            </tbody>
          </table>

          <div style="height: 250px; overflow: auto">
            <table>
              <thead>
                <th>거래일자</th>
                <th>면적</th>
                <th>층수</th>
                <th>가격</th>
              </thead>
              <tbody>
                <tr v-for="(houseDeal, index) in houseDeals" :key="index">
                  <td>{{ houseDeal.dealDate | date }}</td>
                  <td>{{ houseDeal.area }}</td>
                  <td>{{ houseDeal.floor }}</td>
                  <td>{{ houseDeal.dealAmount }}</td>
                </tr>
              </tbody>
            </table>
          </div>
          <!--<HeartBtn
          v-if="isAuth && level == 2"
          class="px-1"
          @changeHeartBtn="onBookmarkHouse"
        />-->
        </div>
        <!-- contents -->
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";
export default {
  name: "HouseDetail",
  components: {},
  computed: {
    ...mapState(houseStore, ["house", "houseInfo", "houseDeals"]),
  },
  methods: {
    onBookmarkHouse() {},
  },
  filters: {
    date(value) {
      if (!value) return value;
      return value.substr(0, 10);
    },
  },
};
</script>
<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css);
#detailBox {
  position: absolute;
  color: black;
  top: 9%;
  right: 1%;
  width: 25%;
  height: 90%;
  padding: 15px;
  z-index: 100;
  background-color: rgba(241, 239, 239, 0.8);
  overflow-y: auto;
}

.icon {
  margin-top: 4px;
  margin-left: -10px;
}

h4 {
  color: black;
}
table {
  font-family: "Nanum Gothic", serif;
  font-size: 0.8em;
  font-weight: 400;
  background-color: white;
  opacity: 60%;
  color: black;
}
th,
td {
  color: black;
  padding: 2px;
}
</style>
