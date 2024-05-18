import { createRouter, createWebHistory } from 'vue-router'
import ShowSchedule from "@/components/ShowSchedule.vue";
import Login from "@/components/Login.vue";
import Register from "@/components/Register.vue";
import {useUserStore} from "@/stores/user";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      name: 'index',
      path: '/showSchedule',
      component: ShowSchedule
    },
    {
      name: 'login',
      path: '/login',
      component: Login
    },
    {
      name: 'register',
      path: '/register',
      component: Register
    },
    {
      path: '/',
      redirect: '/showSchedule'
    }
  ]
})

// 全局前置守卫
router.beforeEach((to, from, next) => {
  const userStore = useUserStore()
  // 检查用户是否已登录
  const isLoggedIn = userStore.sysUser.username !== '';

  // 如果用户已登录，允许导航到目标路由
  if (isLoggedIn) {
    next();
  } else if (to.path === '/register') {
    next();
  } else {
    // 如果用户未登录，则重定向到登录页面
    if (to.path !== '/login') {
      console.log(11111)
      next('/login');
    } else {
      // 如果用户已经在登录页面，则继续导航
      next();
    }
  }
});

export default router
