<template>
  <User />
  <div class="logo">
    <router-link to="/">
      <img
        src="@/components/img/logo2.png"
        alt="" />
    </router-link>
  </div>
  <Nav2 />
  <RouterView />
</template>

<script setup>
import User from '@/components/Userpage.vue'
import Nav2 from '@/components/NewNav.vue'
</script>

<style scoped>
@import '@/assets/main.css';

</style>
