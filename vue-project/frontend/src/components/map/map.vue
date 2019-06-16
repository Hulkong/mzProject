// map

<template>
  <div class="mapApp">
    <div class="mapArea"  :class="mapHalf">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
 import $ from "jquery"
export default {
  components: {
  },
//   computed: {
//     ...mapGetters({
//        getLayerInfos: "getGisLayers",      // 레이어 정보 가져오기
//       getInitMapInfo: "getInitMapInfo"    // 맵 초기값 가져오기 ( center , zoomlevel )
//     })
//     //배열 또는 Object 이름을 변경하거나 할때엔 Object로 사용한다. //main.js의 getters 상속
//   },
  watch: {
    // layers : function(data){
    //     console.log(data);
    // }
    // center : function(e){
    //   this.setCenter(e);
    // }
  },
  props: ['defaultLayer'],
  created: function(){
    // 기본 지도 설정
    this.setInitInfo();
  },
  mounted: function() {
    this.createMap();
  },
  data: function() {
    return {
      zoomlevel : 7,
      center : [126.570667, 33.450701],
      map:null,
      layers:[],
      layerPopups:[],
      bounds:null,
      regionName : '',
      mapHalf : 'mapHeight100',
      searchResult : null,
      searchLayer : null,
      searchInfo : null
    };
  },
  methods: {
    // ...mapActions({ addLayer: "mapStore/addLayer",  }) ,//main.js의 action 상속
    getChild : function(name){    // 현재 소스 하위의 소스 찾아주기
      let vueObj = null;
      for(let child of this.$children){
        if(child.$options._componentTag == name) vueObj = child;
      }
      return vueObj;
    },
    setInitInfo: function(){      // 초기값 세팅
      var initInfo = this.getInitMapInfo;
      if(initInfo != null){
        if(initInfo.center != null) this.$data.center = initInfo.center;
        if(initInfo.zoomlevel != null) this.$data.zoomlevel = initInfo.zoomlevel;
        if(initInfo.regionName != null) this.$data.regionName = initInfo.regionName;
      }
    },
    createMap: function() {       // 지도 생성
      // var mapContainer = this.$el;
      var mapContainer = this.$el.lastChild;
      // console.log(this);
      var mapOption = {
          center: new daum.maps.LatLng(this.$data.center[1], this.$data.center[0]), // 지도의 중심좌표
          disableDoubleClick: true,
          disableDoubleClickZoom: true,
          tileAnimation: true,
          level: this.$data.zoomlevel // 지도의 확대 레벨
      };
      // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
      this.map = new daum.maps.Map(mapContainer, mapOption);
   //   this.showLayer(this.defaultLayer);
    }
  } //배열 또는 Object 이름을 변경하거나 할때엔 Object로 사용한다.
};
</script>

<style>
.mapApp{
  position: fixed;
  width: 100%;
  height: 100%;
  display: block;
  overflow: hidden;
}
.mapArea{
  width: 100%;
  height: 100%;
}
.mapHeight100{
  height: 100%;
}
.mapHeight41{
  height: 41%;
}
.labelRanking{
  top: -30px;
  left: -18px;
  width: 36px;
  height: 25px;
  background:url(/images/map/bg_label.png) no-repeat;
  color:#fff;
  text-align:center;
  font-weight: bold;
  z-index: 100;
  cursor:pointer;
}
</style>