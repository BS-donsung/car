<template>
  <div class="navbar-container">
    <div class="navbar-nav">
      <ul class="nav-contain">
        <li class="nav-item">
          <router-link
            to="/"
            class="navfrm">
            차량정보
          </router-link>
        </li>
        <li class="nav-item">
          <router-link
            to="/comparison"
            class="navfrm">
            비교하기
          </router-link>
        </li>
        <li class="nav-item">
          <router-link
            to="/cars"
            class="navfrm">
            차량목록  
          </router-link>
        </li>
        <li class="nav-item">
          <router-link
            to="/"
            class="navfrm">
            칼럼
          </router-link>
        </li>
        <li class="nav-item">
          <router-link
            to="/community"
            class="navfrm">
            커뮤니티
          </router-link>
        </li>
        <li class="nav-item">
          <router-link
            to="/inquiry"
            class="navfrm">
            문의하기
          </router-link>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
@import '@/assets/nav.css';
</style>
