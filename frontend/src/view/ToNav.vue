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
      <a href="#">문의하기</a>
      <button
        class="btnLogin-popup"
        @click="openLogin">
        로그인
      </button>
    </nav>
  </header>
</template>

<script setup>
import router from '@/router'

const openLogin = () => {
  router.push({ path: '/loginfrm' })
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
