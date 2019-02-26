<template>
  <div>
    <div class="custom_zoomcontrol radius_border 4menu">
      <!-- <span @click="ckRoadView()">
        <template v-if="roadViewAction">
          <img src="../../images/map/ico_roadview_off.png" id="roadBtn" alt="로드뷰">
        </template>
        <template v-else>
          <img src="../../images/map/ico_roadview.png" id="roadBtn" alt="로드뷰">
        </template>
      </span> -->
      <span @click="setZoomLevel(-1)">
        <img
          src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png"
          class="zoom"
          alt="확대"
        >
      </span>
      <span @click="setZoomLevel(1)">
        <img
          src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_minus.png"
          class="zoom"
          alt="축소"
        >
      </span>
      <span @click="reload()">
        <img src="../assets/images/map_util_refresh.png" class="zoom" alt="지도 초기화">
      </span>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  components: {},
  computed: {
    // ...mapGetters({
    //   getLayers: "getLayers"
    // })
    //배열 또는 Object 이름을 변경하거나 할때엔 Object로 사용한다. //main.js의 getters 상속
  },
  watch: {
    // getLsDsInfo(newLst, oldLst) {
    //   console.log(newLst);
    //   console.log(oldLst);
    // }
  },
  props: {},
  mounted: function() {
    
  },
  data: function() {
    return {
      msg: "짇를 시작합니다.",
      map:{
            zoomlevel : 12,
            center : [33.450701, 126.570667]
      },
      roadViewAction : false,
      selectRoadView : null,
      showDongdong : false,
      
    };
  },
  methods: {
    ...mapActions({ addLayer : "addLayer",}) ,//main.js의 action 상속
    setZoomLevel : function(zoomLevel, center) {
        // 중심좌표 설정
        // if(center) this.$parent.$data.map.setCenter(center);
        // zoomLevel 변경
        var nowLevel = this.getZoomlevel(this.$parent.$data.map);
        this.$parent.$data.map.setLevel((nowLevel + zoomLevel));
    },
    getZoomlevel : function() {
        return this.$parent.$data.map.getLevel();
    },
    reload : function() {
        var moveLatLon = new daum.maps.LatLng(this.map.center[0],this.map.center[1]);
        this.$parent.$data.map.panTo(moveLatLon); 
        this.$parent.$data.map.setLevel(this.map.zoomlevel);
    }
  } //배열 또는 Object 이름을 변경하거나 할때엔 Object로 사용한다.
};
</script>

<style>
.radius_border{border:1px solid #919191;border-radius:5px;}     
.custom_zoomcontrol {position:absolute;top:10%;right:10px;width:36px;overflow:hidden;z-index:2;background-color:#f5f5f5;} 
.custom_zoomcontrol.3menu {height:120px;}
.custom_zoomcontrol.4menu {height:160px;}
.custom_zoomcontrol span {display:block;width:36px;height:41px;text-align:center;cursor:pointer;}     
.custom_zoomcontrol span img {border:none;}            
.custom_zoomcontrol span img.zoom {width:15px;height:15px;padding:12px 0;border:none;}             
.custom_zoomcontrol span {border-bottom:1px solid #bfbfbf;} 
.custom_zoomcontrol span:last-child{border-bottom:0px solid #bfbfbf;} 
.custom_zoomcontrol span:last-child img {padding:9px 0;border:none;} 

</style>
