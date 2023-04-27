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
            to="/"
            class="navfrm">
            비교하기
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
            to="/"
            class="navfrm">
            커뮤니티
          </router-link>
        </li>
        <li class="nav-item">
          <router-link
            to="/"
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
/* @import '@/assets/nav.css'; */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.navbar-container {
  width: 100%;
  height: 50px;
  display: flex;
  justify-content: center;
  background: #8EA7E9;
}

.navbar-nav {
  width: 700px;
  height: 50px;
  text-align: center;
}

.nav-contain {
  width: 100%;
  height: 50px;
  display: flex;
  list-style: none;
  align-items: center;
  justify-content: space-between;
}

.nav-item {
  width: 100px;
  height: 50px;
  font-size: 20px;
  font-weight: 700;
  align-items: center;
}

.navfrm {
  text-decoration: none;
  color: white;
  cursor: pointer;
}

.navfrm:hover {
  color: #111;
}
</style>
