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
  import config from '@/js/config';
  import EventBus from '@/js/eventBus'

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

        this.mapData();
     //   this.$store.commit('addMapInfo', map);
      },
      mapData() {
        EventBus.$emit('triggerMapData', this.mapObject)
      },
      onMapEvent (event, params) {
        
        //   var map = this.mapObject;
        //   // 지도를 클릭한 위치에 표출할 마커입니다
        //   var marker = new kakao.maps.Marker({ 
        //       // 지도 중심좌표에 마커를 생성합니다 
        //       position: map.getCenter() 
        //   }); 
        //   // 지도에 마커를 표시합니다
        //   marker.setMap(map);
        // console.log(`Daum Map Event(${event})`, map);
          
     //     this.searchPlace();

      }
    }
  }
</script>
