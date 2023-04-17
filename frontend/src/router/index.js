import {createRouter, createWebHistory} from 'vue-router/dist/vue-router'
import Home from '@/view/ReMainPage.vue'
import Comparison from '@/view/Comparison.vue'
import Login from '@/view/ToLoginFrm.vue'
import Register from '@/view/ToRegisterFrm.vue'



const routes = [
  {path: '/', component: Home},
  {path: '/comparison', component: Comparison},
  {path: '/loginfrm', component: Login},
  {path: '/registerfrm', component: Register},

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
