// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css' // Ensure you are using css-loader
// import '@fortawesome/fontawesome-free/css/all.css' 
// import 'material-design-icons-iconfont/dist/material-design-icons.css' 

Vue.prototype.$http = axios

Vue.use(Vuetify);
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
 // components: { App },  
})
