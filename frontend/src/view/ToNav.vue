<template>
  <header class="header">
    <router-link
      to="/"
      class="logo">
      LOGO
    </router-link>

    <nav class="navbar">
      <router-link to="/comparison">
        비교
      </router-link>
      <a href="#">Service</a>
      <a href="#">Content</a>
      <button
        class="btnLogin-popup"
        @click="openLogin">
        로그인
      </button>
    </nav>
  </header>
  <div id="user"></div>
  <Teleport to="#user">
    <login-comp
      v-if="isLogin"
      @close-login="closeLogin"
      @open-join="openJoin" />
    <join-comp
      v-if="isJoin"
      @close-join="closeJoin"
      @open-login="openLogin" />
  </Teleport>
</template>

<script setup>
import { ref } from 'vue'
import LoginComp from '@/components/LoginComp.vue'
import JoinComp from '@/components/JoinComp.vue'

const isLogin = ref(false)
const isJoin = ref(false)
const openLogin = () => {
  console.log('open!!')
  isLogin.value = true
}
const openJoin = () => {
  isJoin.value = true
}
const closeLogin = () => {
  isLogin.value = false
}
const closeJoin = () => {
  isJoin.value = false
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Popins', sans-serif;
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
</style>
