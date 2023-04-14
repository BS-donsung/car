import {createRouter, createWebHistory} from 'vue-router/dist/vue-router'
import Home from '@/view/ToMainPage.vue'
import Comparison from '@/view/Comparison.vue'
import Login from '@/view/ToLoginFrm.vue'


const routes = [
  {path: '/', component: Home},
  {path: '/comparison', component: Comparison},
  {path: '/login', component: Login},
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
