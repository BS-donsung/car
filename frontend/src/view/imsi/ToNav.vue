<template>
  <div class="user-container">
    <ul class="user-contain">
      <li class="user-item">
        <router-link
          to="/"
          class="userfrm">
          HOME
        </router-link>
      </li>
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
  </div>

  <Nav />
</template>

<script setup>
import Nav from '@/components/NewNav.vue'
</script>

<style scoped>
@import '@/assets/nav.css';

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
  text-decoration: none;
  font-size: 14px;
  color: #aeaeae;
}
</style>
