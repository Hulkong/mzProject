import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import mainView from '@/components/view/mainView'
import mapView from '@/components/view/mapView'
import headerView from '@/components/header/header';
import noticeView from '@/components/notice/notice';

import settingView from '@/components/setting/setting';
import userInfoView from '@/components/setting/userInfo';

import LoginPage from '@/components/loginPage'
import MovieIndexPage from '@/components/movieIndexPage'
import MovieShowPage from '@/components/movieShowPage'


export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'main page',
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
    },
    {
      path : '/setting', 
      name : 'setting page',
      component : settingView,
      children : [
          {
              path: 'userInfo',
              component: userInfoView
          }
      ]
    },
    {
      path : '/view', 
      name : 'map page',
      components : {
        header : headerView,
        map : mapView
      }
    }
  ]
})