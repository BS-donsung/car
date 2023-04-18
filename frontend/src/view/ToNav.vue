<template>
  <header class="header">
    <router-link
      to="/"
      class="logo">
      LOGO
    </router-link>

    <nav class="navbar">
      <router-link to="/comparison">
        모델비교
      </router-link>
      <router-link to="/community">
        커뮤니티
      </router-link>
      <router-link to="/inquiry">
        문의하기
      </router-link>
      <span>{{ username }}</span>
      <button
        v-if="username != ''"
        class="btnLogin-popup"
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
// import { URL } from '@/components/global'

const username = ref('')
const token = Cookies.get('Authorization')

function gettoken() {
  console.log(token)
}

const openLogin = () => {
  router.push({ path: '/loginfrm' })
}

const getUsername = () => {
  // const requestOptions = {
  //   credentials: 'include'
  // }
  // fetch(URL + '/getuser', requestOptions)
  //   .then(res => res.text())
  //   .then(text => {
  //     username.value = text
  //     alert(`${username.value}님 환영합니다.`)
  //   })
  //   .catch(console.log('username을 받아오는데 실패하였습니다.'))
}

const logoutBtn = () =>{
  if (username.value!='') {
    username.value =''
  }
}



onMounted(() => {
  gettoken(), getUsername()

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
</style>
