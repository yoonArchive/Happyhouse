<template>
  <div>
    <div v-if="house">
      <div id="detailBox" class="card">
        <div class="table-wrapper">
          <div class="row">
            <h4>{{ house.aptName }}</h4>
            <vue-clap-button
              class="icon"
              icon="love"
              :size="35"
              :initClicked="1"
            ></vue-clap-button>
          </div>
          <table>
            <tbody>
              <tr>
                <td>주소</td>
                <td>{{ house.dongName }}</td>
              </tr>
              <tr>
                <td>건축년도</td>
                <td>{{ house.buildYear }}</td>
              </tr>
              <tr>
                <td>면적</td>
                <td>{{ house.area }}</td>
              </tr>
              <tr>
                <td>거래금액</td>
                <td>
                  {{
                    (parseInt(house.dealAmount.replace(",", "")) * 10000)
                      | price
                  }}
                </td>
              </tr>
            </tbody>
          </table>

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
// import "bootstrap/dist/css/bootstrap.min.css";
// import "bootstrap";
import { mapState } from "vuex";

const houseStore = "houseStore";
export default {
  name: "HouseDetail",
  components: {},
  computed: {
    ...mapState(houseStore, ["house", "houses"]),
  },
  methods: {
    onBookmarkHouse() {},
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
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
  padding: 2px;
}
</style>
