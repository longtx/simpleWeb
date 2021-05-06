import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const login = r => require.ensure([], () => r(require('@/page/login')), 'login');
const manage = r => require.ensure([], () => r(require('@/page/manage')), 'manage');
const home = r => require.ensure([], () => r(require('@/page/home')), 'home');
const foodList = r => require.ensure([], () => r(require('@/page/foodList')), 'foodList');

const routes = [{
    path: '/',
    component: login,
    },
    {
        path: '/manage',
        component: manage,
        name: '',
        children: [{
            path: '',
            component: home,
            meta: [],
        }, {
            path: '/foodList',
            component: foodList,
            meta: ['参与活动', '报名人员列表'],
        }]
    }
]

const router = new Router({
        mode: 'hash',
        routes: routes
    })
    /**
     * 当一个导航触发时，全局的 before 钩子按照创建顺序调用。钩子是异步解析执行，此时导航在所有钩子 resolve 完之前一直处于 等待中。
     *  to: Route: 即将要进入的目标 路由对象
     *  from: Route: 当前导航正要离开的路由
     *  next: Function: 一定要调用该方法来 resolve 这个钩子。
     */
router.beforeEach((to, from, next) => {
    next();
})

router.afterEach(route => {})


export default router;