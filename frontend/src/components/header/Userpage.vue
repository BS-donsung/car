<template>
  <div class="user-container">
    <ul
      v-if="store.user===''"
      class="user-contain">
      <li class="user-item">
        <router-link
          to="/loginfrm"
          class="userfrm">
          로그인
        </router-link>
      </li>
      <li class="user-item">
        <router-link
          to="/registerfrm"
          class="userfrm">
          회원가입
        </router-link>
      </li>
    </ul>
    <ul
      v-if="store.user!==''"
      class="user-contain">
      <li class="user-item">
        <router-link
          to="/mypage"
          class="userfrm">
          내정보
        </router-link>
      </li>
      <li class="user-item">
        <a
          class="userfrm"
          @click="logout">
          로그아웃
        </a>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { useCompStore } from '@/store/index'
import Cookies from 'js-cookie'
const store = useCompStore()
  
const logout = () =>{
  Cookies.remove('Authorization')
  Cookies.remove('JSESSIONID')
  if (store.user.username!='') {
    store.user=''
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.user-container {
  width: 100%;
  height: 30px;
  display: flex;
}

.user-contain {
  width: 100%;
  list-style: none;
  display: flex;
  justify-content: end;
  margin-right: 30px;
}

.user-item {
  width: 70px;
  height: 30px;
  font-size: 20px;
  font-weight: 700;
}

.userfrm {
  font-size: 12px;
  color: #aeaeae;
}
</style>
