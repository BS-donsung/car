import {createRouter, createWebHistory} from 'vue-router/dist/vue-router'
import Home from '@/view/ReMainPage.vue'
import Comparison from '@/view/Comparison.vue'
import Login from '@/view/ToLoginFrm.vue'
import Register from '@/view/ToRegisterFrm.vue'
import Community from '@/view/CommunityFrm.vue'
import CreateNotice from '@/view/ToCreateNotice.vue'
import DetailNotice from '@/view/ToDetailNotice.vue'
import EditNotice from '@/view/ToEditNotice.vue'
import Inquiry from '@/view/InquiryFrm.vue'



const routes = [
  {path: '/', component: Home},
  {path: '/comparison', component: Comparison},
  {path: '/loginfrm', component: Login},
  {path: '/registerfrm', component: Register},
  {path: '/community', component: Community, name: 'community'},
  {path: '/cratenotice', component: CreateNotice},
  {path: '/community/:bno', component: DetailNotice, name: 'detailnotice' , props:true},
  {path: '/editnotice/:bno/Edit', component: EditNotice, name: 'editnotice'},
  {path: '/inquiry', component: Inquiry},
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
