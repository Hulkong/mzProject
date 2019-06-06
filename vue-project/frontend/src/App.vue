<template>
  <div>
    <router-view name="header"></router-view>
    <router-view name="map"></router-view>
  </div>
</template>

<script>

export default {    
  name: 'app',
  data () {
    return {
      msg: 'Welcome to Your Vue.js A1p2p3'
    }
  }
}
</script>

<style lang="scss">
</style>
