import {createRouter, createWebHistory} from 'vue-router/dist/vue-router'
import Home from '@/components/ToMainPage.vue'
import Comparison from '@/components/Comparison.vue'
import Login from '@/components/ToLoginFrm'
import TestLogin from '@/components/LoginComp.vue'
import TestJoin from '@/components/JoinComp.vue'


const routes = [
  {path: '/', component: Home},
  {path: '/comparison', component: Comparison},
  {path: '/login', component: Login},
  {path: '/test/login', component: TestLogin},
  {path: '/test/join', component: TestJoin},
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
