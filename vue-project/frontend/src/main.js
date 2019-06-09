import Vue from 'vue'
import Vuetify from 'vuetify'
import Vuex from 'vuex'
import router from './components/routes/routes'
import App from './App.vue'
import axios from 'axios'
import store from './store'
import 'vuetify/dist/vuetify.min.css' // Ensure you are using css-loader
import '@fortawesome/fontawesome-free/css/all.css' 
import 'material-design-icons-iconfont/dist/material-design-icons.css' 

window.axios = require('axios');
Vue.use(Vuetify);
Vue.prototype.$axios = axios

new Vue({
  el: '#app',
  strict: true,
  render: h => h(App),
  router,
  store
})
