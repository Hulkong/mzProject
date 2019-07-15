// 검색리스트
<template>
<div class="searchStoreListApp">
    <v-container>
        <v-layout row>
            <v-flex xs12 sm6 offset-sm3>
                <v-card>
                    <v-toolbar color="pink" dark>
                        <v-toolbar-title>{{searchWord}} 검색결과</v-toolbar-title>
                    </v-toolbar>
                    <v-list two-line>
                        <template v-for="(place, index) in searchPlaces">
                        <v-list-tile :key="index" avatar ripple> 
                            <v-list-tile-content  @mouseover="showListMarkerTooltip(place)" @mouseout="closeListMarkerTooltip" >
                            <v-list-tile-title  v-html="place.place_name"></v-list-tile-title>
                            <v-list-tile-sub-title v-html="place.phone" class="text--primary"></v-list-tile-sub-title>
                            <v-list-tile-sub-title v-html="place.road_address_name"></v-list-tile-sub-title>
                            </v-list-tile-content>
                            <v-list-tile-action>
                            <v-list-tile-action-text>{{ place.distance }}</v-list-tile-action-text>
                            <v-icon color="pink" @click="">fas fa-plus-circle</v-icon>
                            </v-list-tile-action>
                        </v-list-tile>
                        <v-divider v-if="index + 1 < searchPlaces.length" :key="`divider-${index}`"></v-divider>
                        </template>
                    </v-list>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>
</div>
</template>


<script>
import EventBus from '@/js/eventBus'

export default {
  props: ['searchWord', 'showSearchStoreList'],
  components:{
  },
  mounted() {
    EventBus.$on('triggerMapData', value =>{
      this.map = value;
    });
  },
  methods: {
    searchPlace() {
        var keyword = this.searchWord;
        this.psObject.keywordSearch(keyword, this.placesSearchCB); 
      },
    placesSearchCB(data, status, pagination){
        if (status === kakao.maps.services.Status.OK) {
          // 정상적으로 검색이 완료됐으면
          // 검색 목록과 마커를 표출합니다
          this.searchPlaces = data;
          this.displayPlaces(data);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
            alert('검색 결과가 존재하지 않습니다.');
            return;
        } else if (status === kakao.maps.services.Status.ERROR) {
            alert('검색 결과 중 오류가 발생했습니다.');
            return;
        }

    },
    showListMarkerTooltip(place){
        let placePosition = new kakao.maps.LatLng(place.y, place.x);
        let marker = new kakao.maps.Marker({
            position: placePosition
        });
       this.mInfoWindo = new kakao.maps.InfoWindow({
            content: place.place_name
        });
        let outer = makeOverListener(this.map, marker, this.mInfoWindo);
        outer();
    },
    closeListMarkerTooltip(){
    //    let infowindow = new kakao.maps.InfoWindow();
        let outer = makeOutListener(this.mInfoWindo);
        outer();
    },
    displayPlaces(places) {

        let bounds = new kakao.maps.LatLngBounds();
        let fragment = document.createDocumentFragment();

        this.removeMarker();
         for ( var i=0; i<places.length; i++ ) {
            
            // 마커를 생성하고 지도에 표시합니다
            let placePosition = new kakao.maps.LatLng(places[i].y, places[i].x);
            let marker = this.addMarker(placePosition, i);

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
            // LatLngBounds 객체에 좌표를 추가합니다
            bounds.extend(placePosition);
            
            let infowindow = new kakao.maps.InfoWindow({
                content: places[i].place_name
            });

            kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(this.map, marker, infowindow));
            kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));

        }
        this.map.setBounds(bounds); 
    },
    showStoreInfo(place){
        let placePosition = new kakao.maps.LatLng(place.y, place.x);
        let marker = new kakao.maps.Marker({
            position: placePosition
        });
        let infowindow = new kakao.maps.InfoWindow({
            content: place.place_name
        });
        makeOverListener(this.map, marker, infowindow);
        makeOutListener(infowindow);
    },
    addMarker(position, idx) {
        var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
            imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
            imgOptions =  {
                spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
                spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
            },
            markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
                marker = new kakao.maps.Marker({
                position: position, // 마커의 위치
                image: markerImage 
            });

        marker.setMap(this.map); // 지도 위에 마커를 표출합니다
        this.markers.push(marker);  // 배열에 생성된 마커를 추가합니다

        return marker;
    },
    removeMarker() {
        for ( var i = 0; i < this.markers.length; i++ ) {
            this.markers[i].setMap(null);
        }   
        this.markers = [];
    }
  },
  data () {
    return {
        map: '',
        psObject: new kakao.maps.services.Places(),
        searchPlaces: [],
        markers: [],
        mInfoWindow: null
    }
  }
};

function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}

function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}

</script>


<style>
.searchStoreListApp{
    /* position: fixed;
    z-index: 101;
    top: 60px;
    width: 1000px; */    
    position: relative;
    z-index: 101;
    width: 800px;
    top: 100px;
    right: 200px;
}
</style>