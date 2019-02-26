import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import Vuex from 'vuex'
import VueRouter from 'vue-router'
import $ from './assets/js/lib/jquery/jquery-1.11.2.min.js'

Vue.use(Vuex)
Vue.use(VueRouter)
Vue.prototype.$http = axios


const store = new Vuex.Store({
  modules: {
  },
  getters: {
    getGisLayers: function (state) {
      return state.gis.layers;
    },
    getInitMapInfo: function (state) {
      return state.gis.mapInfo;
    }
  },
  state: {
    gis: {
      mapInfo : {
        zoomlevel : 7,
        center : [37.516627931025965, 126.91608734987726],
        regionName : '영등포구'
      },
      layers: {
      }
    }
  }
})

new Vue({
  el: '#app',
  store,
  render: h => h(App)
})
