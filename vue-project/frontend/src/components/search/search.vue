<template>
  <searchStoreList :searchWord="searchWord" :showSearchStoreList="showSearchStoreList"></searchStoreList>
</template>


<script>
import searchStoreList from './searchStoreList'
export default {
  components: {
    searchStoreList
  },
  
  methods: {
   
  } //배열 또는 Object 이름을 변경하거나 할때엔 Object로 사용한다.
};
</script>