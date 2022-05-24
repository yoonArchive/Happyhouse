<template>
  <div>
    <div id="wrapper">
      <div id="map"></div>
      <div id="searchBox" class="card">
        <div>
          <house-search-bar @setMarker="displayMarker"></house-search-bar>
        </div>
      </div>
      <div>
        <house-detail @setMarker="displayMarker"></house-detail>
      </div>
      <!-- <div class="button-group">
        <button @click="displayMarker(markerPositions)">marker set 2</button>
      </div> -->
    </div>
  </div>
</template>

<script>
import HouseSearchBar from "@/components/trade/HouseSearchBar.vue";
import HouseDetail from "@/components/trade/HouseDetail.vue";
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";
export default {
  name: "HouseSearch",
  components: {
    HouseSearchBar,
    HouseDetail,
  },
  data() {
    return {
      markers: [],
      listVisible: false,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses", "houseCnt", "markerPositions"]),
  },
  created() {
    this.CLEAR_DETAIL_HOUSE();
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
    ...mapActions(houseStore, ["getHouseListByDong", "detailHouse"]),
    ...mapMutations(houseStore, [
      "SET_HOUSE_COUNT",
      "SET_DETAIL_HOUSE",
      "CLEAR_DETAIL_HOUSE",
    ]),
    selectHouse(selectedAptCode) {
      this.detailHouse(selectedAptCode);
    },
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
    displayMarker() {
      // 1. 현재 표시되어 있는 marker들이 있다면 marker에 등록된 map을 없앤다.
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
      // 마커의 위치
      const positions = this.markerPositions.map(
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
              clickable: true,
            })
        );
        this.markers.forEach((marker, index) => {
          let item = this.houses[index];
          kakao.maps.event.addListener(marker, "click", () => {
            this.selectHouse(item.aptCode);
          });
        });

        // 지도 이동시켜주기
        // 배열.reduce((누적값, 현재값,인덱스,요소)=>{return 결과값}, 초기값)
        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );
        this.map.setBounds(bounds);
      }
    },
    showHouseDetail(item) {
      console.log(item);
    },
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
</style>
