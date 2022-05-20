<template>
  <div>
    <div id="wrapper">
      <div id="map"></div>
      <div id="searchBox" class="card">
        <div>
          <house-search-bar></house-search-bar>
        </div>
      </div>
      <!--<div v-if="listVisible" id="showList" class="card p-0 bg-secondary">
        <div
          @click="moveOngoingList"
          v-if="ongoingCount == 0"
          class="text-center text-white py-2"
          style="background-color: #ccc"
        >
          <h6>매물 보기 (0개)</h6>
        </div>
        <div
          @click="moveOngoingList"
          v-else
          class="text-center text-primary py-2 bg-warning"
          style="cursor: pointer"
        >
          <h6>매물 보기 ({{ ongoingCount }}개)</h6>
        </div>
         아파트 정보 요약 
        <div class="bg-white mb-2">
          <div
            class="p-3 border-bottom d-flex justify-content-between align-items-center"
          >
            <h4 class="m-0">{{ houseList[curIndex].aptName }}</h4>
            <HeartBtn
              class="px-1"
              :enabled="houseList[curIndex].bookmark"
              @changeHeartBtn="onBookmarkHouse"
            />
          </div>
           contents
          <div class="px-3">
            <div class="border-bottom d-flex py-2">
              <div class="text-secondary w-25">주소</div>
              <div>
                {{ houseList[curIndex].dongName }}
                {{ houseList[curIndex].jiBun }}
              </div>
            </div>
            <div class="d-flex py-2">
              <div class="text-secondary w-25">건축년도</div>
              <div>{{ houseList[curIndex].buildYear }}</div>
            </div>
          </div>
        </div>
         실거래가
        <div class="bg-white mb-2">
          <div class="border-bottom"><h5 class="p-3 m-0">실거래가</h5></div>
          <div>
            <table class="w-100">
              <thead class="bg-secondary text-white">
                <tr>
                  <td class="ps-3 py-1">거래일</td>
                  <td>거래가격</td>
                  <td>면적</td>
                  <td>층수</td>
                </tr>
              </thead>
              <tbody class="px-2">
                <tr
                  v-for="(item, index) in dealInfo"
                  :key="index"
                  class="border-bottom"
                >
                  <td class="ps-3 py-2">{{ item.dealYear }}</td>
                  <td>{{ item.dealAmount }}</td>
                  <td>{{ item.area }}</td>
                  <td>{{ item.floor }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>-->
      <div class="button-group">
        <button @click="displayMarker(markerPositions2)">marker set 2</button>
      </div>
    </div>
  </div>
</template>

<script>
import HouseSearchBar from "@/components/trade/HouseSearchBar.vue";
import { mapState, mapActions, mapMutations } from "vuex";
const storeName = "houseStore";
export default {
  name: "HouseSearch",
  components: {
    HouseSearchBar,
  },
  data() {
    return {
      markers: [],
      markerPositions2: [
        [37.499590490909185, 127.0263723554437],
        [37.499427948430814, 127.02794423197847],
        [37.498553760499505, 127.02882598822454],
        [37.497625593121384, 127.02935713582038],
        [37.49629291770947, 127.02587362608637],
        [37.49754540521486, 127.02546694890695],
        [37.49646391248451, 127.02675574250912],
      ],
      infowindow: null,
      listVisible: false,
    };
  },
  created() {
    if (!("geolocation" in navigator)) {
      return;
    }
    // get position
    navigator.geolocation.getCurrentPosition(
      (pos) => {
        this.latitude = pos.coords.latitude;
        this.longitude = pos.coords.longitude;

        if (window.kakao && window.kakao.maps) {
          this.initMap();
        } else {
          const script = document.createElement("script");
          /* global kakao */
          script.onload = () => kakao.maps.load(this.initMap);
          script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}`;
          document.head.appendChild(script);
        }
      },
      (err) => {
        alert(err.message);
      }
    );
  },
  methods: {
    ...mapActions(storeName, [
      "getGu",
      "getDong",
      "getHouseListByDong",
      "getHouseListByKeyword",
    ]),
    ...mapMutations(storeName, ["CLEAR_KEYWORD"]),
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(this.latitude, this.longitude),
        level: 5,
      };
      //지도 객체를 등록
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언
      this.map = new kakao.maps.Map(container, options);
    },
    displayMarker(markerPositions) {
      // 1. 현재 표시되어 있는 marker들이 있다면 marker에 등록된 map을 없앤다.
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
      // 마커의 위치
      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );
      const imgSrc = require("@/assets/map/house_icon5.png");
      const imgSize = new kakao.maps.Size(50, 50);
      const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);
      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
              image: markerImage,
            })
        );
        // 지도 이동시켜주기
        // 배열.reduce((누적값, 현재값,인덱스,요소)=>{return 결과값}, 초기값)
        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );
        this.map.setBounds(bounds);
      }
    },
  },
  computed: {
    ...mapState(storeName, ["gu", "dong", "houseList", "fromMainKeyword"]),
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  min-height: 100%;
  position: absolute;
}
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
#showList {
  position: absolute;
  top: 20px;
  bottom: 20px;
  right: 20px;
  width: 400px;
  padding: 10px;
  z-index: 100;
  overflow-y: auto;
}
.bi-plus-circle {
  font-size: 1.5rem;
}
.bi-plus-circle:hover {
  color: dodgerblue;
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
  margin-right: 10px;
  text-align: center;
  padding: 0 0.5em;
}
</style>
