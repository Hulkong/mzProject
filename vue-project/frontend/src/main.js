import Vue from 'vue'
import Vuetify from 'vuetify'
import router from './components/routes/routes'
import App from './App.vue'
import axios from 'axios'
import 'vuetify/dist/vuetify.min.css' // Ensure you are using css-loader
// import '@mdi/font/css/materialdesignicons.css'
import '@fortawesome/fontawesome-free/css/all.css' 


Vue.use(Vuetify);
Vue.prototype.$http = axios

new Vue({
  el: '#app',
  iconfont: 'fa',
  render: h => h(App),
  router
})
