<template>
<div id="userInfoApp">
  <v-app id="inspire">
    <v-layout row>
      <v-flex xs48 sm48>
        <v-card>
            <v-card-title class="grey lighten-4 py-4 title">
            {{user}}님의 회원정보
            </v-card-title>
  
            <v-subheader>General</v-subheader>
            <v-form v-model="valid">
                <v-container>
                    <v-layout>
                        <v-flex xs12 md4>
                            <v-text-field v-model="firstname" :rules="nameRules" :counter="10" label="First name" required>
                            </v-text-field>
                        </v-flex>
                        <v-flex xs12 md4>
                            <v-text-field v-model="firstname" :rules="nameRules" :counter="10" label="First name" required>
                            </v-text-field>
                        </v-flex>
                        <v-flex xs12 md4>
                            <v-text-field v-model="email" :rules="emailRules" :counter="10" label="E-mail" required>
                            </v-text-field>
                        </v-flex>
                </v-layout>
             </v-container>
         </v-form>
  
          <v-divider></v-divider>
  
          <v-list
            subheader
            three-line
          >
            <v-subheader>General</v-subheader>
            <v-list-tile @click="">
              <v-list-tile-action>
                <v-checkbox
                  v-model="notifications"
                ></v-checkbox>
              </v-list-tile-action>
  
              <v-list-tile-content @click.prevent="notifications = !notifications">
                <v-list-tile-title>Notifications</v-list-tile-title>
                <v-list-tile-sub-title>Notify me about updates to apps or games that I downloaded</v-list-tile-sub-title>
              </v-list-tile-content>
            </v-list-tile>
  
            <v-list-tile @click="">
              <v-list-tile-action>
                <v-checkbox
                  v-model="sound"
                ></v-checkbox>
              </v-list-tile-action>
  
              <v-list-tile-content @click.prevent="sound = !sound">
                <v-list-tile-title>Sound</v-list-tile-title>
                <v-list-tile-sub-title>Auto-update apps at any time. Data charges may apply</v-list-tile-sub-title>
              </v-list-tile-content>
            </v-list-tile>
  
            <v-list-tile @click="">
              <v-list-tile-action>
                <v-checkbox
                  v-model="widgets"
                ></v-checkbox>
              </v-list-tile-action>
  
              <v-list-tile-content @click.prevent="widgets = !widgets">
                <v-list-tile-title>Auto-add widgets</v-list-tile-title>
                <v-list-tile-sub-title>Automatically add home screen widgets</v-list-tile-sub-title>
              </v-list-tile-content>
            </v-list-tile>
          </v-list>
        </v-card>
      </v-flex>
    </v-layout>
  </v-app>
</div>

</template>


<script>
export default {
  components:{
  },
  data () {
    return {
    user: '다솜',
    valid: false,
    firstname: '',
    lastname: '',
    nameRules: [
      v => !!v || 'Name is required',
      v => v.length <= 10 || 'Name must be less than 10 characters'
    ],
    email: '',
    emailRules: [
      v => !!v || 'E-mail is required',
      v => /.+@.+/.test(v) || 'E-mail must be valid'
    ]
    }
  }
};
</script>

<style>
#userInfoApp {
    position: relative;
    width: 100%;
}
</style>