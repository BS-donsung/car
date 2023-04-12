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
        @click="btnPopup">
        로그인
      </button>
    </nav>
  </header>

  <!-- <Teleport to=".header">
    <AddEvent />
  </Teleport> -->
</template>

<script setup>
import { ref } from 'vue'
// import AddEvent from '@/components/ToLoginFrm.vue'

const isLoginEvent = ref(false)

const btnPopup = () => {
  isLoginEvent.value = true
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
  z-index: 99;
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
