// 공지사항
<template>
<div id="noticeApp">
    <v-layout row>
      <v-flex xs24 sm12 offset-sm6>
        <v-card>
          <v-toolbar color="cyan" dark>
            <v-toolbar-title>{{user}}님의 새로운 소식</v-toolbar-title>
          </v-toolbar>
  
          <v-list two-line>
            <template v-for="(item, index) in items">
              <v-subheader
                v-if="item.header"
                :key="item.header"
              >
                {{ item.header }}
              </v-subheader>
  
              <v-divider
                v-else-if="item.divider"
                :key="index"
                :inset="item.inset"
              ></v-divider>
  
              <v-list-tile
                v-else
                :key="item.title"
                avatar
                @click=""
              >
                <v-list-tile-avatar>
                  <img :src="item.avatar">
                </v-list-tile-avatar>
  
                <v-list-tile-content>
                  <v-list-tile-title v-html="item.title"></v-list-tile-title>
                  <v-list-tile-sub-title v-html="item.subtitle"></v-list-tile-sub-title>
                </v-list-tile-content>
              </v-list-tile>
            </template>
          </v-list>
        </v-card>
      </v-flex>
    </v-layout>
</div>
</template>


<script>

export default {
  props: ['searchWord', 'showSearchStoreList'],
  components:{
  },
  data () {
    return {
      user : '셔누',
      items: [
        { header: 'Today' },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
          title: 'Brunch this weekend?',
          subtitle: "<span class='text--primary'>Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?"
        },
        { divider: true, inset: true },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
          title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
          subtitle: "<span class='text--primary'>to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend."
        },
        { divider: true, inset: true },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
          title: 'Oui oui',
          subtitle: "<span class='text--primary'>Sandra Adams</span> &mdash; Do you have Paris recommendations? Have you ever been?"
        }
      ]
    }
  }
};
</script>



<style>
#noticeApp{
    position: fixed;
    z-index: 101;
    top: 60px;
    left: 10px;
}


</style>
