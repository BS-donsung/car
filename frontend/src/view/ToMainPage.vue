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
  <div id="body"></div>
  <Teleport to="#body">
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
const openLogin = () => { isLogin.value = true }
const openJoin = () => { isJoin.value = true }
const closeLogin = () => { isLogin.value = false }
const closeJoin = () => { isJoin.value = false }

</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap");
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Popins", sans-serif;
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
  z-index: 99;
}
.header * {
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
  content: "";
  position: absolute;
  width: 100%;
  height: 2px;
  background: #fff;
  bottom: -4px;
  left: 0;
  opacity: 0.85;
}

.section {
  background: url("./img/car.jpg") no-repeat;
  min-height: 100vh;
  background-size: cover;
  background-position: center;
}

#body {
  width: 100vw;
  height: 100vh;
}
</style>
