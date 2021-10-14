import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import ElementUI from '../components/ElementUI.vue'
import Home from '../components/Home.vue'
import User from '../components/user/user.vue'
import ItemCat from '../components/items/ItemCat.vue'
import Item from '../components/items/Item.vue'
import AddItem from '../components/items/addItem.vue'
//使用路由机制
Vue.use(VueRouter)
const routes = [
  {path: '/', redirect: '/login'},
  {path: '/login', component: Login},
  {path: '/elementUI', component: ElementUI},
  /*实现父子组件跳转,使用children关联 */
  {path: '/home', component: Home, children: [
    {path: '/user', component: User},
    {path: '/itemCat', component: ItemCat},
    {path: '/item', component: Item},
    {path: '/item/addItem', component: AddItem}
  ]}
]


//路由对象的定义
const router = new VueRouter({
  routes
})

/**
 * 路由导航守卫实现
 * 说明: beforeEach用户的每次请求在访问目标网址之前都会被拦截.
 * 参数:
 *     1.to    用户请求的网址对象
 *     2.from  用户请求的来源
 *     3.next  请求的放行
 *  需求:
 *     1.如果用户访问登录页面
 *     2.校验是否含有token数据
 *        有token  放行
 *        没有token  跳转到登录页面
 */
router.beforeEach((to,from,next) => {
    if(to.path === '/login'){
      //直接跳转并且结束代码
      return next()
    }
    //2.判断是否有token
    let token = window.sessionStorage.getItem("token")
    if(token !== null && token !==''){ //更加安全
      //有值,跳转指定页面
      return next()
    }
    //3.如果数据为空则跳转到登录页面
    next('/login')
})


//路由对象的使用
export default router
