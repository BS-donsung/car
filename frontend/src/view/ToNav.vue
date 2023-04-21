<template>
  <header class="header">
    <router-link
      to="/"
      class="logo">
      LOGO
    </router-link>

    <nav class="navbar">
      <router-link to="/cars">
        차량목록
      </router-link>
      <router-link to="/comparison">
        모델비교
      </router-link>
      <router-link to="/community">
        커뮤니티
      </router-link>
      <router-link to="/inquiry">
        문의하기
      </router-link>
      <router-link
        v-if="username != ''"
        to="/mypage">
        내 정보
      </router-link>
      <button
        v-if="username != ''"
        class="btnLogin-popup logout"
        @click="logoutBtn">
        로그아웃
      </button>

      <button
        v-else
        class="btnLogin-popup"
        @click="openLogin">
        로그인
      </button>
    </nav>
  </header>
</template>

<script setup>
import router from '@/router'
import Cookies from 'js-cookie'
import { onMounted, ref } from 'vue'
import { URL } from '@/components/global'

const username = ref('')
// const token = Cookies.get('Authorization')


const openLogin = () => {
  router.push({ path: '/loginfrm' })
}

const getUsername = () => {
  const requestOptions = {
    credentials: 'include'
  }
  fetch(URL + '/user/getuser', requestOptions)
    .then(res => res.json())
    .then(body => {
      username.value = body.username===undefined?'':body.username
      console.log(`${username.value}님 환영합니다.`)
    })
    .catch(username.value='')
}

const logoutBtn = () =>{
  Cookies.remove('Authorization')
  Cookies.remove('JSESSIONID')
  if (username.value!='') {
    username.value =''
  }
}



onMounted(() => {
  getUsername()

})
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Popins', sans-serif;
}

span {
  color: white;
  margin-right: 30px;
}

.header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  padding: 20px 100px;
  background: transparent;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: black;
  z-index: 1;
}

.logo {
  font-size: 32px;
  color: #fff;
  text-decoration: none;
  font-weight: 700;
}

.navbar a {
  font-size: 18px;
  color: #fff;
  text-decoration: none;
  font-weight: 500;
  margin-right: 40px;
}

.navbar .btnLogin-popup {
  position: relative;
  background: transparent;
  border: none;
  outline: none;
  font-size: 18px;
  color: #fff;
  font-weight: 500;
  cursor: pointer;
}

.navbar .btnLogin-popup::before {
  content: '';
  position: absolute;
  width: 100%;
  height: 2px;
  background: #fff;
  bottom: -4px;
  left: 0;
  opacity: 0.85;
}

.logout {
  margin-left: 20px;
}
</style>
