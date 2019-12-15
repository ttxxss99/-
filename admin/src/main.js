import Vue from 'vue'
import App from './App'
import less from 'less'
import axios from 'axios'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '../static/css/base.css'
import '../static/fonts/iconfont.css'

import animate from 'animate.css'
Vue.prototype.$axios = axios;

Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.use(less)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
