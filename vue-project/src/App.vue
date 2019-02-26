<template>
  <div id="app">
    <mapApp></mapApp>
  
  </div>
</template>

<script>
import mapApp from './map/daum-map.vue'


export default {
  name: 'app',
  components:{
    mapApp : mapApp
    
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js A1p2p3'
    }
  }
}
</script>

<style>

</style>
