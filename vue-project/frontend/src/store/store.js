import Vue from 'vue';
import Vuex from 'vuex';


Vue.use(Vuex);

export default new Vuex.Store({
    strict: true,
    state: {
        user: 
            {
              name: 'dasom',
              title: 'BBB',
              completed: true
            }
    },
      getters: {
      },
      mutations: {
      },
      actions: {
      }
});