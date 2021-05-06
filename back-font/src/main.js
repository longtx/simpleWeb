import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
Vue.config.productionTip = false;
Vue.use(ElementUI);


// step1：引入 axios
import Axios from 'axios'

Vue.config.productionTip = false

// step2：把axios挂载到vue的原型中，在vue中每个组件都可以使用axios发送请求,
// 不需要每次都 import一下 axios了，直接使用 $axios 即可
Vue.prototype.$axios = Axios

// step3：使每次请求都会带一个 /api 前缀 
Axios.defaults.baseURL = '/api'
    // API
import api from './config/fetch.js'
// A绑定全局
Vue.prototype.$api = api

new Vue({
    el: '#app',
    router,
    store,
    template: '<App/>',
    components: { App }
})