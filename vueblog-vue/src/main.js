import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import axios from 'axios'

import mavonEditor from 'mavon-editor'

import "element-ui/lib/theme-chalk/index.css"
import 'mavon-editor/dist/css/index.css'

import "./axios"
import "./permission"

Vue.use(Element)
Vue.use(mavonEditor)

// 定义全局的时间过滤器，格式化时间
Vue.filter('dateFormat', function (dateStr) {
  var dt = new Date(dateStr);
  var y = dt.getFullYear();
  var m = (dt.getMonth() + 1).toString().padStart(2, '0');
  var d = (dt.getDate()).toString().padStart(2, '0');
  var hh = dt.getHours().toString().padStart(2, '0');
  var mm = dt.getMinutes().toString().padStart(2, '0');
  var ss = dt.getSeconds().toString().padStart(2, '0');
  return `${y}-${m}-${d} ${hh}:${mm}:${ss}`;
})

Vue.config.productionTip = false
Vue.prototype.$axios = axios

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
