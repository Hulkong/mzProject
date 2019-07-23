import Vue from 'vue';
import Vuex from 'vuex';


Vue.use(Vuex);

export default new Vuex.Store({
    strict: true,
    state: {
        mapData: 
            {
              mapObject: '',
              psObject: '',
              infowindowObject: ''
            }
    },
      getters: {
      },
      mutations: {
        addMapInfo(state, mapInfo) {
          state.mapData.mapObject = mapInfo;
        },
      },
      actions: {
      }
});