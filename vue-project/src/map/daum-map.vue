<template>
  <div class="mapApp">
    <div class="mapArea"  :class="mapHalf">
      <mapTypeApp></mapTypeApp>
      <zoomApp></zoomApp>
    </div>
    
  </div>
</template>

<script>
import $ from '../assets/js/lib/jquery/jquery-1.11.2.min.js'
import { mapGetters, mapActions, mapState } from "vuex";
import zoomApp from './daum-zoombar.vue'
import mapTypeApp from './daum-typeBtn.vue'

export default {
  components: {
    zoomApp,
    mapTypeApp
  },
  computed: {
    ...mapGetters({
       getLayerInfos: "getGisLayers",      // 레이어 정보 가져오기
      getInitMapInfo: "getInitMapInfo"    // 맵 초기값 가져오기 ( center , zoomlevel )
    })
    //배열 또는 Object 이름을 변경하거나 할때엔 Object로 사용한다. //main.js의 getters 상속
  },
  watch: {
    // layers : function(data){
    //     console.log(data);
    // }
    mapHalf : function(){
      this.mapReload();
    }
    
  },
  props: ['defaultLayer'],
  mounted: function() {
    this.setInitInfo();
    this.createMap();
  },
  data: function() {
    return {
      zoomlevel : 7,
      center : [33.450701, 126.570667],
      map:null,
      layers:[],
      bounds:null,
      regionName : '',
      mapHalf : 'mapHeight100',
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
      console.log(this);
      var mapOption = {
          center: new daum.maps.LatLng(this.$data.center[0], this.$data.center[1]), // 지도의 중심좌표
          disableDoubleClick: true,
          disableDoubleClickZoom: true,
          tileAnimation: true,
          level: this.$data.zoomlevel // 지도의 확대 레벨
      };

      // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
      this.map = new daum.maps.Map(mapContainer, mapOption);

      // console.log(this);
      // console.log(this.getLayerInfos);
      // console.log(this.getInitMapInfo);
      this.showLayer(this.defaultLayer);
    },
    mapReload : function(){
      this.$data.map.relayout();
    },
    hideLayer : function(dataId){   // 레이어 숨기기
      var targetLayers = null;
      targetLayers = this.getLayer(dataId);
      // targetLayers에 도형정보가 있으면
      if(targetLayers._features != null && targetLayers._features.length > 0){
        if(targetLayers._type =='wms'){
          // wms일 경우
          this.$data.map.removeOverlayMapTypeId(targetLayers._mapTypeId);
          // data에서 해당 레이어 삭제
          this.deleteLayer(dataId);
        }else if(targetLayers._type =='wfs'){
          // wfs일 경우
          $(targetLayers._features).each(function(index, tLayer){
            if(tLayer !== undefined && tLayer != null) tLayer.setMap(null);
          });
        }
      }

      this.$emit("map-hide-legend", dataId);
    },
    showLayer : function(dataId){     // 레이어 보여주기
      var targetLayers = null;
      targetLayers = this.getLayer(dataId);

      // targetLayers에 도형정보가 있으면
      if(targetLayers == null || targetLayers._features.length == 0){
        this.getData(dataId);
      }else{
          for(var tl in targetLayers._features){
            var tLayer = targetLayers._features[tl];
            if(tLayer.featureType !== undefined && tLayer.featureType == 'wms'){
              this.getData(tLayer.param.layerId)
            }else{
              tLayer.setMap(this.$data.map);
            }
          }
      }

      this.$emit("map-show-legend", this.getLayerInfos[dataId]);
    },
    deleteLayer : function(layerId){    // 레이어 삭제
      var tLayers = $(this.$data.layers).map(function(item){
          if(item._fid != layerId){
            return item
          }
      },[]);
      this.$data.layers = tLayers;
    },
    getLayer : function(layerId){     // 레이어 찾기
      var tLayers = null;
      $(this.$data.layers).each(function(index, item){
          if(item._fid == layerId){
            tLayers = item;
          }
      });
      return tLayers;
    },
    getData: function(dataId){      // 데이터 가져오기
      var dataInfo = this.getLayerInfos[dataId];
      if(dataInfo.type == 'wfs'){
        this.getWFSDate(dataId);
      } else if(dataInfo.type == 'wms') {
        this.getWMSDate(dataId);
      }
    },
    setOverlayMapLayer : function(layerId, overlayId){    // 다음에서 제공해주는 레이어
      var daumLayer = {};
      var feature = {overlayId : overlayId,  featureType : 'overlay'};
      daumLayer._features=[];
      daumLayer._features.push(feature);
      daumLayer._fid = layerId;
      daumLayer._type = 'wms';
      daumLayer._mapTypeId = overlayId;

      // 레이어 등록
      this.$data.layers.push(daumLayer);
      this.$data.map.addOverlayMapTypeId(overlayId);

    },
    getWMSDate : function(dataId){    // wms 정보가져오기 & 레이어 생성 (다음에서 제공하지않는 레이어)
      // map에 추가할 layer담기
      var daumLayer = {};
      var dataInfo = this.getLayerInfos[dataId];
      var feature = {url : dataInfo.serverUrl, param : dataInfo, featureType : 'wms'}
      daumLayer._features=[];
      daumLayer._features.push(feature);
      daumLayer._fid = dataId;
      daumLayer._type = dataInfo.type;
      
      // wms tileset 등록
      this.tileSet(dataInfo.serverUrl, dataInfo);
      daumLayer._mapTypeId = daum.maps.MapTypeId[dataId];

      // 레이어 등록
      this.$data.layers.push(daumLayer);
      this.$data.map.addOverlayMapTypeId(daum.maps.MapTypeId[dataId]);
    },
    getWFSDate : function(dataId){    // wfs 데이터(geoJson) 가져오기
      var dataInfo = this.getLayerInfos[dataId];
     // 영역 경계 데이터 가져오기
      this.$http({
          method : 'GET',
          url : dataInfo.url,
          type : 'JSON'
      })
      .then((result) => {
          var data = JSON.parse(result.data.substr(result.data.indexOf("{")));
          // daum 좌표계로 변환
          // addlayer
          this.addDaumLayer(data, dataId);
      });
    },
    addDaumLayer : function (json, layerId){  // 레이어 생성
        var dataInfo = this.getLayerInfos[layerId];
        var daumLayer = {};
            daumLayer._fid = layerId;
            daumLayer._type = dataInfo.type;
        var featureArr = [];

        for(var fi = 0; fi < json.features.length; fi++){
            var geometry = json.features[fi].geometry;
            var properties = json.features[fi].properties;
            var daumFeature = null;
            // feature의 type으로 모형만들기
            if(geometry.type == 'Polygon'){
              // 색지정
              if(dataInfo.option.colorKey !== undefined){
                dataInfo.option.style.strokeColor = dataInfo.option.colorMap[properties[dataInfo.option.colorKey]];
                dataInfo.option.style.fillColor = dataInfo.option.colorMap[properties[dataInfo.option.colorKey]];
              }
              daumFeature = this.mkPolygon(geometry, dataInfo.option.style); 
            }else if(geometry.type == 'LineString'){
                // 색지정
                if(dataInfo.option.colorKey !== undefined){
                  dataInfo.option.style.strokeColor = dataInfo.option.colorMap[properties[dataInfo.option.colorKey]];
                }
                daumFeature = this.mkPolyline(geometry, dataInfo.option.style); 
            // }else if(geometry.type == 'MultiPolygon'){
                // daumFeature.feature = this.mkMultiPolygon(geometry, properties, option.style(item));
            }else if(geometry.type == 'Point'){
                // if(option.pointToLayer){
                //     daumFeature.feature = option.pointToLayer(geometry, properties);
                // }else{				
                daumFeature = this.mkPoint(geometry, dataInfo.option, properties);
                // }
            }else if(geometry.type == 'DivPoint'){
              daumFeature = this.mkDivPoint(dataInfo.option);
            }
            
            featureArr.push(daumFeature);
        }
        daumLayer._features = featureArr;
        this.$data.layers.push(daumLayer);
    },
    mkPolygon : function(feature, style){   // polygon feature 만들기
        var featureType = feature.type;
        // polygon 좌표 코딩
        var polygonPaths = this.mkPolygonCoord(feature.coordinates);
        
        // polygon 생성 및 지도에 띄우기
        style.path = polygonPaths;
        style.map = this.$data.map;
        
        return new daum.maps.Polygon(style);
    },
    mkPolygonCoord : function(coordinates){     // 폴리곤의 좌표를 daum좌표 객체로 
        var polygonPath = [];
        $.each(coordinates[0], function(i, coord){
            polygonPath.push(new daum.maps.LatLng(coord[1],coord[0]));
        });
        return polygonPath;
    },
    mkPolyline : function(feature, style){      // polyline feature 만들기
        var featureType = feature.type;
        // polygon 좌표 코딩
        var polylinePaths = this.mkPolylineCoord(feature.coordinates);
        
        // polygon 생성 및 지도에 띄우기
        style.path = polylinePaths;
        style.map = this.$data.map;
      
        return new daum.maps.Polyline(style);
    },
    mkPolylineCoord : function(coordinates){     // 폴리곤의 좌표를 daum좌표 객체로 
        var polylinePath = [];
        $.each(coordinates, function(i, coord){
            polylinePath.push(new daum.maps.LatLng(coord[1],coord[0]));
        });
        return polylinePath;
    },
    mkPoint : function(feature, option, properties){    // point feature 만들기
        var style = option.style;
        var featureType = feature.type;
        // point 좌표 코딩
        var position = this.mkLatlng(feature.coordinates);
        
        // polygon 생성 및 지도에 띄우기
        if(option.titleKey !== undefined && option.titleKey != '') style.title = properties[option.titleKey];
        style.position = position;
        style.map = this.$data.map;
        var marker = new daum.maps.Marker(style);
        if(option.mouseHandler && option.mouseHandler.length > 0){
          for(var i = 0 ; i < option.mouseHandler.length ; i++){
            daum.maps.event.addListener(marker, option.mouseHandler[i].name, option.mouseHandler[i].fnc);
          }
        }
        marker.properties = properties;
        return marker;
    },
    mkDivPoint : function(option){
      // text 필드 기본 스타일
      var customOverlayOption = {
//		  clickable : true,		// true 로 설정하면 컨텐츠 영역을 클릭했을 경우 지도 이벤트를 막아준다.
//		  zIndex:3,				// z-index
        xAnchor : 0.5,			// 컨텐츠의 x축 위치. 0_1 사이의 값을 가진다. 기본값은 0.5
        yAnchor : 0.5			// 컨텐츠의 y축 위치. 0_1 사이의 값을 가진다. 기본값은 0.5
//	    position : position,
//	    content : content   
      };
      
      if(option)$.extend(customOverlayOption,option);
      customOverlayOption.map = this.$data.map;
      return new daum.maps.CustomOverlay(customOverlayOption);
    },
    mkLatlng : function(coordinates) {    // 폴리곤의 좌표를 daum좌표 객체로 
	    return new daum.maps.LatLng(coordinates[1],coordinates[0])
    },
    tileSet : function(url, wmsOption){   // wms 레이어 타일 만들기
      url += "&"+$.param(wmsOption);
      var tileSize = wmsOption.tileSize;
      var tileset = new daum.maps.Tileset(tileSize, tileSize, function(x, y, z) {
        /**   x: column,  y: row,  z: level   **/
        //		var bounds = [[-30000, -60000], [494288, 988576]]
        var resolutions = [0.125,  0.25, 0.5, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048],
            origin = [-30000, -60000];

        var minX =  Math.abs((x)   * (resolutions[z] * tileSize )+ origin[0]);
        var maxX =  Math.abs((x+1) * (resolutions[z] * tileSize )+ origin[0]);
        var minY =  Math.abs((y)   * (resolutions[z] * tileSize )+ origin[1]);
        var maxY =  Math.abs((y+1) * (resolutions[z] * tileSize )+ origin[1]);
        
        // 세 parameter를 조합하여 타일 주소를 조합/반환한다.
        var full_url = url; 
        full_url += "&WIDTH="+tileSize+"&HEIGHT="+tileSize;
        full_url += "&BBOX="+minX+"%2C"+minY+"%2C"+maxX+"%2C"+maxY;
        
        return full_url;
              
      }, [], false, 1, 10);
      daum.maps.Tileset.add( wmsOption.layerId, tileset );
    },
    setCursor : function(cType){   // 마우스 커서 변경
      this.$data.map.setCursor(cType);
    },
    toggleRoadview : function(position) {
      // 로드뷰 켜기
      // this.$parent.$emit("map-show-roadview",position);
      var vueObj = this.getChild("resultApp");
      vueObj.openRoadViewArea(position);

      // 로드뷰 끄기
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
</style>
