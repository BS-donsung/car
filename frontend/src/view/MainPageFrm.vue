<template>
  <div class="main-container">
    <!-- <User />
    <div class="logo">
      <router-link to="/">
        <img
          src="@/components/img/logo2.png"
          alt="" />
      </router-link>
    </div>
    <Nav /> -->
    <!-- 슬라이더 -->
    <Slider /> 
    <!-- 차량비교 -->
    <Seleted />
    <!-- 신차뉴스 / 리뷰 -->
    <News />
    <!-- 동영상 -->
    <Vedio />
    <!-- <RouterView /> -->
  </div>
</template>

<script setup>
// import User from '@/components/Userpage.vue'
// import Nav from '@/components/NewNav.vue'
import Slider from '@/components/ToSwiperslider.vue'
import Seleted from '@/components/ToSelect.vue'
import News from '@/components/ToNews.vue'
import Vedio from '@/components/ToVideo.vue'
</script>

<style scoped>
@import '@/assets/main.css';

.main-container {
  width: 100%;
  height: 250vh;
}
</style>
