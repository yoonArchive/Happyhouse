<template>
  <div>
    <div id="map"></div>
    <div class="button-group">
      <button @click="displayMarker(markerPositions2)">marker set 2</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "KakaoMap",
  data() {
    return {
      markerPositions2: [
        [37.499590490909185, 127.0263723554437],
        [37.499427948430814, 127.02794423197847],
        [37.498553760499505, 127.02882598822454],
        [37.497625593121384, 127.02935713582038],
        [37.49629291770947, 127.02587362608637],
        [37.49754540521486, 127.02546694890695],
        [37.49646391248451, 127.02675574250912],
      ],
      markers: [],
      infowindow: null,
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
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 720px;
}
</style>
