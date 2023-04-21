import {createRouter, createWebHistory} from 'vue-router/dist/vue-router'
import Home from '@/view/ReMainPage.vue'
import Comparison from '@/view/Comparison.vue'
import Login from '@/view/ToLoginFrm.vue'
import Register from '@/view/ToRegisterFrm.vue'

import MyPage from '@/view/MyPageFrm.vue'
import MyPageBoards from '@/view/MyPageBoardsFrm.vue'
import MyPageReplies from '@/view/MyPageRepliesFrm.vue'
import MyPageAlerts from '@/view/MyPageAlertsFrm.vue'
import MyPageStores from '@/view/MyPageStoresFrm.vue'
import MyPageStore from '@/view/MyPageStoreFrm.vue'

import Community from '@/view/CommunityFrm.vue'
import CreateNotice from '@/view/ToCreateNotice.vue'
import Inquiry from '@/view/InquiryFrm.vue'




const routes = [
  {path: '/', component: Home},
  {path: '/comparison', component: Comparison},
  {path: '/loginfrm', component: Login},
  {path: '/registerfrm', component: Register},

  {path: '/mypage', component: MyPage},
  {path: '/mypage/boards', component: MyPageBoards},
  {path: '/mypage/replies', component: MyPageReplies},
  {path: '/mypage/alerts', component: MyPageAlerts},
  {path: '/mypage/stores', component: MyPageStores},
  {path: '/mypage/store', component: MyPageStore},

  {path: '/community', component: Community},
  {path: '/cratenotice', component: CreateNotice},
  {path: '/inquiry', component: Inquiry},

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
