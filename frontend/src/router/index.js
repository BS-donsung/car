import {createRouter, createWebHistory} from 'vue-router/dist/vue-router'
import Home from '@/components/ToMainPage.vue'
import Comparison from '@/components/Comparison.vue'
import Login from '@/components/ToLoginFrm'
// import Register from '@/components/RegisterForm.vue'


const routes = [
  {path: '/', component: Home},
  {path: '/comparison', component: Comparison},
  {path: '/login', component: Login},
  // {path: '/register', component: Register},
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
