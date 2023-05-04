import { createRouter, createWebHistory } from 'vue-router/dist/vue-router'
import Home from '@/view/MainPageFrm.vue'
import Comparison from '@/view/Comparison.vue'
import Login from '@/view/ToLoginFrm.vue'
import Register from '@/view/ToRegisterFrm.vue'
import User from '@/components/Userpage.vue'
import Nav from '@/components/NewNav.vue'


import MyPage from '@/view/mypage/MyPageFrm.vue'
import MyPageBoards from '@/view/mypage/MyPageBoardsFrm.vue'
import MyPageReplies from '@/view/mypage/MyPageRepliesFrm.vue'
import MyPageAlerts from '@/view/mypage/MyPageAlertsFrm.vue'
import MyPageStores from '@/view/mypage/MyPageStoresFrm.vue'
import MyPageStore from '@/view/mypage/MyPageStoreFrm.vue'

import Cars from '@/view/CarListFrm.vue'
import Car from '@/view/CarFrm.vue'

import Community from '@/view/community/CommunityFrm.vue'
import CreateNotice from '@/view/community/ToCreateNotice.vue'
import DetailNotice from '@/view/community/ToDetailNotice.vue'
import EditNotice from '@/view/community/ToEditNotice.vue'
import StoreCommunity from '@/view/community/StoreCommunity.vue'
import DetailStore from '@/view/community/StoreDetail.vue'

import Inquiry from '@/view/InquiryFrm.vue'

import Test from '@/view/TestFrm.vue'

const AppLayout = (component) => {
  let components = {
    default: component,
    user: User,
    header: Nav,
  }
  return components
}

const routes = [
  {
    path: '/',
    components : AppLayout(Home)
  },
  {
    path: '/comparison',
    components: AppLayout(Comparison)
  },
  {
    path: '/loginfrm',
    components: {
      default: Login,
    },
  },
  {
    path: '/registerfrm',
    components: {
      default: Register,
    },
  },
  { path: '/inquiry', components: AppLayout(Inquiry) },
  
  { path: '/mypage', components: AppLayout(MyPage) },
  { path: '/mypage/boards', components: AppLayout(MyPageBoards) },
  { path: '/mypage/replies', components: AppLayout(MyPageReplies) },
  { path: '/mypage/alerts', components: AppLayout(MyPageAlerts) },
  { path: '/mypage/stores', components: AppLayout(MyPageStores) },
  { path: '/mypage/store', components: AppLayout(MyPageStore) },
  { path: '/cars', components: AppLayout(Cars) },
  { path: '/car', components: AppLayout(Car) },
  
  { path: '/community', components: AppLayout(Community) },
  { path: '/community/create', components: AppLayout(CreateNotice) },
  { path: '/community/detail', components: AppLayout(DetailNotice), props: true },
  { path: '/community/edit', components: AppLayout(EditNotice), },
  { path: '/community/store', components: AppLayout(StoreCommunity)},
  { path: '/community/store/detail', components: AppLayout(DetailStore), },

  { path: '/test', component: Test },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
