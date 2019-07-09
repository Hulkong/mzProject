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
                    <v-list two-line v-if="false">
                        <template v-for="(place, index) in searchPlaces">
                        <v-list-tile :key="index" avatar ripple>
                            <v-list-tile-content>
                            <v-list-tile-title>{{ place.place_name }}</v-list-tile-title>
                            <v-list-tile-sub-title class="text--primary">{{ place.phone }}</v-list-tile-sub-title>
                            <v-list-tile-sub-title>{{ place.road_address_name }}</v-list-tile-sub-title>
                            </v-list-tile-content>
                            <v-list-tile-action>
                            <v-list-tile-action-text>{{ place.distance }}</v-list-tile-action-text>
                            <v-icon color="pink">fas fa-plus-circle</v-icon>
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
        this.searchPlaces = places;

        let bounds = new kakao.maps.LatLngBounds();
        let fragment = document.createDocumentFragment();

         for ( var i=0; i<places.length; i++ ) {
            
            // 마커를 생성하고 지도에 표시합니다
            let placePosition = new kakao.maps.LatLng(places[i].y, places[i].x);
            let marker = this.addMarker(placePosition, i);

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
            // LatLngBounds 객체에 좌표를 추가합니다
            bounds.extend(placePosition);
        //    this.prevealMarker(marker, places[i].place_name);


var iwContent = '<div style="padding:5px;">Hello World!</div>';

// 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
    content : iwContent
});

// 마커에 클릭이벤트를 등록합니다
kakao.maps.event.addListener(marker, 'click', function() {
      // 마커 위에 인포윈도우를 표시합니다
      infowindow.open(map, marker);  
});

        }
        this.map.setBounds(bounds); 
      },
    addMarker(position, idx, title) {
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
    displayInfowindow(marker, title) {
        var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

        this.infowindowObject.setContent(content);
        this.infowindowObject.open(map, marker);
}
  },
  data () {
    return {
        map: '',
        psObject: new kakao.maps.services.Places(),
        infowindowObject: new kakao.maps.InfoWindow({zIndex:1}),
        searchPlaces: [],
        markers: []
    }
  }
};

function  prevealMarker(marker, title) {
        kakao.maps.event.addListener(marker, 'mouseover', function() {
                    console.log("d");
                });

                // kakao.maps.event.addListener(marker, 'mouseout', function() {
                //     this.infowindowObject.close();
                // });

                // itemEl.onmouseover =  function () {
                //     this.displayInfowindow(marker, title);
                // };

                // itemEl.onmouseout =  function () {
                //     infowindow.close();
    };
</script>


<style>
.searchStoreListApp{
    position: fixed;
    z-index: 101;
    top: 60px;
    width: 1000px;
}
</style>