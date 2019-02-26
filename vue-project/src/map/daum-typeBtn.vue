<template>
  <div>
      <div class="baseChange">
				<a href="#none" @click="changeMapType($event,'ROADMAP')" class="on"><span>일반</span></a>
				<a href="#none" @click="changeMapType($event,'SKYVIEW')"><span>위성</span></a>
				<a href="#none" @click="changeMapType($event,'HYBRID')"><span>겹쳐보기</span></a>
			</div>
  </div>
</template>

<script>
import $ from '../assets/js/lib/jquery/jquery-1.11.2.min.js'
import { mapGetters, mapActions } from "vuex";
export default {
  components: {},
  computed: {
    // ...mapGetters({
    //   getLayers: "getLayers",
    //   getMapType : "getMapType"
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
      mapType: 'ROADMAP'
      
    };
  },
  methods: {
      // ...mapActions({ setMapType: "mapStore/setMapType" }) ,//main.js의 action 상속
      changeMapType: function(e,mType) {
        let obj = $(e.target).parent();
        let parentEl = $(e.target).parent().parent();
        parentEl.find("a").each(function(){
            $(this).removeClass("on")
        });
        $(obj).addClass("on");
      this.$parent.$data.map.setMapTypeId(daum.maps.MapTypeId[mType]);
    }
  } //배열 또는 Object 이름을 변경하거나 할때엔 Object로 사용한다.
};
</script>

<style>
.baseChange {position:absolute;top:5px;right:10px;height:24px;z-index: 2;}
.baseChange a span {padding:2px 10px;height:24px;font-size: 12px;display: inline-block;line-height: 2;font-weight: bold;background: #f7f7f7;border-radius: 4px;border: 1px solid #c8c8c8;box-shadow: 0px 1px #888;cursor: pointer;text-align:center;}
.baseChange a.on span {background: #4C4E57;color:#fff;}
</style>
