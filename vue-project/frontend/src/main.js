

import Vue from 'vue'
import Vuetify from 'vuetify'
import App from './App.vue'
import 'vuetify/dist/vuetify.min.css' // Ensure you are using css-loader
// import '@mdi/font/css/materialdesignicons.css'
import '@fortawesome/fontawesome-free/css/all.css' 

Vue.use(Vuetify);

new Vue({
  el: '#app',
  render: h => h(App),
  iconfont: 'fa'
})
