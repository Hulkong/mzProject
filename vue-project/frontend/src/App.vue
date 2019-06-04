<template>
    <div id="app">
        <headerApp></headerApp>
        <storeListApp></storeListApp>
        <mapApp defaultLayer="regionArea"></mapApp>
    </div>


</template>

<script>
import mapApp from './components/map/map.vue'
import headerApp from './components/header/header.vue'
import storeListApp from './components/store/searchStoreList.vue'

export default {    
  name: 'app',
  components:{
    headerApp : headerApp,
    mapApp : mapApp,
    storeListApp: storeListApp
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js A1p2p3'
    }
  }
}
</script>

<style lang="scss">
</style>
