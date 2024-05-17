import { createRouter, createWebHistory } from 'vue-router'
import ShowSchedule from "@/components/ShowSchedule.vue";
import Login from "@/components/Login.vue";
import Register from "@/components/Register.vue";

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

export default router
