<template>
    <div id="app">
        <headerApp></headerApp>
        <mapApp defaultLayer="regionArea"></mapApp>
    </div>
</template>

<script>
import mapApp from './body/body.vue'
import headerApp from './header/header.vue'

export default {
  name: 'app',
  components:{
    headerApp : headerApp,
    mapApp : mapApp
    
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
