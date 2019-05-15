<template>
  <div class="headerApp">
		<h1 class="logo">{{title}}</h1>
  </div>
</template>


<script>
export default {
  components: {},
  computed: {
    // ...mapGetters({
      // getInitInfo: "getInitMapInfo"
    // })
    //배열 또는 Object 이름을 변경하거나 할때엔 Object로 사용한다. //main.js의 getters 상속
  },
  watch: {
  },
  props: {},
  mounted: function() {
  },
  data: function() {
    return {
      title : "여성안심귀갓길",    // 타이틀
      search : '',                // 검색어
      sResultList : [],           // 검색 결과 리스트
      sResultPaging : null,       // 검색 결과 페이징
      sResult : {},               // 
      stopSearch : false,         // 검색 결과 선택시를 제외한 검색
      sRsultListOpen : false,     // 검색 결과 리스트 on/off 여부
      option : {
        style : {
          map :null,								        // marker가 올라갈 map
          position:null,							      // marker의 위치
          image: new daum.maps.MarkerImage(                          // marker의 이미지
            '/images/map/marker-icon.png',
            new daum.maps.Size(25,41)
          ),	
          title: '',								        // 툴팁의 text
          draggalbe:false,						      // 드래그 가능한 marker
          clickable:false,						      // 클릭 가능한 marker
          zIndex:2,								          // marker의 z-index 속성값
          visible: true,
          opacity:1								          // marker의 투명도
        },
        titleKey : 'place_name',
        InfowindowAble : true
      }
    };
  },
  methods: {
   
  } //배열 또는 Object 이름을 변경하거나 할때엔 Object로 사용한다.
};
</script>

<style>
/* .headerApp{
  width: 96%;
  height: 6%;
  min-height:20px;
  padding : 0% 2%;
  display: inline-block;
}
.headerApp h3 {margin:0;float: left; padding: 10px;}
.headerApp input {margin-left:10px; margin-top: 10px;} */

/* TOP */
.headerApp{
  width: 100%;
  height: 46px;
}
fieldset{ border:0 none; } /* 선(테두리)없음 */
.logo{ float:left; width:112px; padding-left:55px; padding-top:12px; padding-right:55px;}
.logo a{display:block;}


</style>