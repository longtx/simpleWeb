import Vue from 'vue';
import Router from 'vue-router';
import Home from '@/views/home/home';
import Result from '@/views/result/result';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home,
    },
    {
      path: '/result',
      name: 'Result',
      component: Result,
    },
  ],
});
