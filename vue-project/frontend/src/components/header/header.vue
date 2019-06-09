<template>
  <div class="headerApp">
    <search :searchWord="searchWord" :showSearchStoreList="showSearchStoreList"></search>
    <v-app>
    <v-toolbar app>
      <v-toolbar-title class="headline text-uppercase">
        <!-- <span>Vuetify test : </span> -->
        <span>{{user.name}}</span>
        <span class="font-weight-light">{{title}}</span>
      </v-toolbar-title>
      <v-form>
        <v-container>
          <v-layout>
          <v-flex xs60 md20>
            <v-text-field v-model="searchWord" label="검색어를 입력해주세요." required>
            </v-text-field>
          </v-flex>
          </v-layout>
        </v-container>
      </v-form>
      <v-btn  fab dark small color="primary" @click="goStoreSearch">
        <v-icon dark>fas fa-search</v-icon>
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn fab dark small color="teal" @click="goNotice">
        <v-icon dark>fas fa-bell</v-icon>
      </v-btn>
      <v-btn fab dark small color="teal">
        <v-icon dark>fas fa-user</v-icon>
      </v-btn>
      <v-btn fab dark small color="teal">
        <v-icon dark>fas fa-bars</v-icon>
      </v-btn>
      <v-btn fab dark small color="teal">
        <v-icon dark>fas fa-cog</v-icon>
      </v-btn>
    </v-toolbar>
  </v-app>

  </div>
</template>


<script>
import search from '../search/search'

export default {
  props: {},
  components:{
    search
  },
  computed: {
    user() {
      return this.$store.state.user
    }
  },
  methods: {
    goStoreSearch() {
      this.showSearchStoreList = true;
    },
    goNotice() {
      console.log('axiosTest');
      axios.get('/view/notice')
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      })
    }
  },
  data: function() {
    return {
      title : "Mukza_1001",    
      searchWord : '',
      showSearchStoreList : false
    }
  }
};
</script>


<style>
/* .headerApp{
  width: 96%;
  height: 6%;
  min-height:20px;
  padding : 0% 2%;
  display: inline-block;
}
.headerApp h3 {margin:0;float: left; padding: 10px;}
.headerApp input {margin-left:10px; margin-top: 10px;} */

/* TOP */
.headerApp{
  width: 100%;
  height: 60px;
}
fieldset{ border:0 none; } /* 선(테두리)없음 */
.logo{ float:left; width:112px; padding-left:55px; padding-top:12px; padding-right:55px;}
.logo a{display:block;}


</style>
