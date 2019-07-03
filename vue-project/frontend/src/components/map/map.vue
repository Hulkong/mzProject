// map
<template>
  <div class="mapApp">
    <vue-daum-map

      :appKey="appKey"

      :center.sync="center"
      :level.sync="level"
      :mapTypeId="mapTypeId"
      :libraries="libraries"

      @load="onLoad"

      @zoom_start="onMapEvent('zoom_start', $event)"
      @zoom_changed="onMapEvent('zoom_changed', $event)"
      @click="onMapEvent('click', $event)"
   

      style="width:1500px;height:1400px;">
    </vue-daum-map>
  </div>
</template>


<script>
  import VueDaumMap from 'vue-daum-map';
  import config from '../../config';
  export default {
    name: 'App',
    components: {VueDaumMap},
    data: () => ({
      appKey: config.appKey,
      center: {lat:33.450701, lng:126.570667},
      level: 3,
      mapTypeId: VueDaumMap.MapTypeId.NORMAL,
      libraries: [],
      mapObject: null,
      psObject: null,
      searchStoreList: []
    
    }),
    methods: {
      onLoad (map) {
        // // 지도의 현재 영역을 얻어옵니다
        // var bounds = map.getBounds();
        // // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
        // var boundsStr = bounds.toString();
        // console.log('Daum Map Loaded', boundsStr);
        this.mapObject = map;
        this.psObject = new kakao.maps.services.Places(); 
        this.infowindowObject = new kakao.maps.InfoWindow({zIndex:1});
      },
      onMapEvent (event, params) {
        console.log(`Daum Map Event(${event})`, params);
        
          var map = this.mapObject;
          // 지도를 클릭한 위치에 표출할 마커입니다
          var marker = new kakao.maps.Marker({ 
              // 지도 중심좌표에 마커를 생성합니다 
              position: map.getCenter() 
          }); 
          // 지도에 마커를 표시합니다
          marker.setMap(map);
          
          this.searchPlace();

      },
      searchPlace() {
        var keyword = '이태원 맛집';
        
        this.psObject.keywordSearch(keyword, this.placesSearchCB); 
      },
      placesSearchCB(data, status, pagination){
        if (status === kakao.maps.services.Status.OK) {
          // 정상적으로 검색이 완료됐으면
          // 검색 목록과 마커를 표출합니다
          this.displayPlaces(data);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
            alert('검색 결과가 존재하지 않습니다.');
            return;
        } else if (status === kakao.maps.services.Status.ERROR) {
            alert('검색 결과 중 오류가 발생했습니다.');
            return;
        }

      },
      displayPlaces(places) {
        this.searchPlace = places;
        console.log(places);
      }
    }
  }
</script>
