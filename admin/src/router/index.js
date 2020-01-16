import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

import login from '@/views/Login'
import Home from '@/views/Home'

import article from '@/views/articleList'
import post from '@/views/post'
import salaryDetail from '@/views/salaryDetail'
import payStructure from '@/views/payStructure'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home,
      redirect: '/article',
      children: [
        {
          path: '/article',
          name: 'article',
          component: article,
        },
        {
          path: '/post',
          name: 'post',
          component: post,
        },
        {
          path: '/salaryDetail',
          name: 'salaryDetail',
          component: salaryDetail,
        },
        {
          path: '/payStructure',
          name: 'payStructure',
          component: payStructure,
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: login
    }
  ]
})
