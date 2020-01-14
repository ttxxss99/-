import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

import login from '@/views/Login'
import Home from '@/views/Home'

import article from '@/views/articleList'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'article',
      component: Home,
      redirect:'/article',
      children:[
          {
            path: '/article',
            name: 'article',
            component: article,
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
