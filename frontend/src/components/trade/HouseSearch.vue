<template>
  <div>
    <div id="wrapper">
      <div id="map"></div>
      <div v-if="searchBarVisible">
        <div id="searchBox" class="card">
          <house-search-bar @setMarker="displayMarker"></house-search-bar>
        </div>
      </div>
      <div id="back" v-else>
        <img src="@/assets/img/back.png" width="50" height="50" />
      </div>
      <div>
        <house-detail @setMarker="displayMarker"></house-detail>
      </div>
    </div>
  </div>
</template>

<script>
import HouseSearchBar from "@/components/trade/HouseSearchBar.vue";
import HouseDetail from "@/components/trade/HouseDetail.vue";
//import BackIcon from "@/components/trade/BackIcon.vue";
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";
const userStore = "userStore";
export default {
  name: "HouseSearch",
  components: {
    HouseSearchBar,
    HouseDetail,
    // BackIcon,
  },
  data() {
    return {
      map: null,
      markers: [],
      searchBarVisible: true,
      longitude: 0,
      latitude: 0,
    };
  },
  computed: {
    ...mapState(houseStore, [
      "houses",
      "houseCnt",
      "houseInfo",
      "markerPositions",
    ]),
    ...mapState(userStore, ["houseWishListInfos"]),
  },
  created() {
    this.CLEAR_DETAIL_HOUSE();
    if (!("geolocation" in navigator)) {
      return;
    }
    const code = this.$route.params.code;
    const index = this.$route.params.index;
    if (code) {
      this.searchBarVisible = false;
      this.CLEAR_MARKER_POSITIONS();
      this.CLEAR_HOUSE_LIST();
      this.detailHouse(code);
      this.SET_IS_IN_WISH_LIST();
      console.log(this.houseWishListInfos[index]);
    }
    // get position
    navigator.geolocation.getCurrentPosition(
      (pos) => {
        if (code) {
          this.latitude = this.houseWishListInfos[index].lat;
          this.longitude = this.houseWishListInfos[index].lng;
        } else {
          this.latitude = pos.coords.latitude;
          this.longitude = pos.coords.longitude;
        }
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
    ...mapActions(houseStore, ["detailHouse"]),
    ...mapActions(userStore, ["getIsInWishList"]),
    ...mapMutations(houseStore, [
      "CLEAR_HOUSE_LIST",
      "CLEAR_DETAIL_HOUSE",
      "CLEAR_MARKER_POSITIONS",
      "SET_MARKER_POSITIONS",
    ]),
    ...mapMutations(userStore, ["SET_IS_IN_WISH_LIST"]),
    selectHouse(selectedAptCode) {
      this.detailHouse(selectedAptCode);
      this.getIsInWishList(selectedAptCode);
    },
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(this.latitude, this.longitude),
        level: 5,
      };
      //?????? ????????? ??????
      //?????? ????????? ????????? ?????? ????????? ???????????? initMap?????? ??????
      this.map = new kakao.maps.Map(container, options);
    },
    displayMarker() {
      // 1. ?????? ???????????? ?????? marker?????? ????????? marker??? ????????? map??? ?????????.
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
      // ????????? ??????
      const positions = this.markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );
      console.log(positions);
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
        // ?????? ??????????????????
        // ??????.reduce((?????????, ?????????,?????????,??????)=>{return ?????????}, ?????????)
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
#back {
  position: absolute;
  top: 9%;
  left: 0.5%;
  width: 25%;
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
