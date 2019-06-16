import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import mainView from '@/components/view/mainView'
import mapView from '@/components/view/mapView'

import LoginPage from '@/components/loginPage'

import MovieIndexPage from '@/components/movieIndexPage'
import MovieShowPage from '@/components/movieShowPage'


export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'mainView',
      component: mainView
    },
    {
      path: '/loginPage',
      name: 'LoginPage',
      component: LoginPage
    },
    {
      path: '/movie',
      name: 'MovieIndexPage',
      component: MovieIndexPage
    },
    {
      path: '/movie/:id',
      name: 'MovieShowPage',
      component: MovieShowPage
    }
  ]
})