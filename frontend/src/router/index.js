import { createRouter, createWebHistory } from 'vue-router/dist/vue-router'
import Home from '@/view/MainPageFrm.vue'
import Comparison from '@/view/Comparison.vue'
import Login from '@/view/ToLoginFrm.vue'
import Register from '@/view/ToRegisterFrm.vue'
import User from '@/components/Userpage.vue'
import Nav from '@/components/NewNav.vue'


import MyPage from '@/view/MyPageFrm.vue'
import MyPageBoards from '@/view/MyPageBoardsFrm.vue'
import MyPageReplies from '@/view/MyPageRepliesFrm.vue'
import MyPageAlerts from '@/view/MyPageAlertsFrm.vue'
import MyPageStores from '@/view/MyPageStoresFrm.vue'
import MyPageStore from '@/view/MyPageStoreFrm.vue'

import Cars from '@/view/CarListFrm.vue'
import Car from '@/view/CarFrm.vue'

import Community from '@/view/CommunityFrm.vue'
import CreateNotice from '@/view/ToCreateNotice.vue'
import DetailNotice from '@/view/ToDetailNotice.vue'
import EditNotice from '@/view/ToEditNotice.vue'
import Inquiry from '@/view/InquiryFrm.vue'

import Test from '@/view/TestFrm.vue'

import Column from '@/view/ColumnFrm.vue'
import Update from '@/view/ToUpdate.vue'


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

  { path: '/test', component: Test },

  {path: '/column', component: Column, name: 'column'},
  {path: '/update', component: Update, name: 'update'},
  
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
