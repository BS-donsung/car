<template>
  <div class="contain-warp">
    <div class="info-warp">
      <hr class="hr_tag" />
      <div class="info-news">
        <img
          class="info-img"
          src="../../public/info.png"
          alt="" />
        <div>
          <p class="title_news">
            News page has been opend
          </p>
          <p class="sub_title">
            #Develog
          </p>
          <p class="content_p">
            news and information on the latest developments related to the website
          </p>
        </div>
      </div>

      <div class="develog-container">
        <!-- 세번째 업데이트 -->
        <div
          class="develog-box aos-init"
          data-aos="zoom-in-up"
          data-aos-duration="1500">
          <img
            class="develog_img"
            src="../../public/차량.png"
            alt="" />
          <div>
            <p class="dayfont">
              <span class="dayfont_span">Thursday 4</span>
            </p>
            <p class="month_font">
              #2 May, 2023 News
            </p>
            <p class="box_font">
              세번째 내용
            </p>
          </div>
        </div> 
        <!-- 두번째 업데이트 -->
        <div
          class="develog-box aos-init"
          data-aos="zoom-in-up"
          data-aos-duration="1500">
          <img
            class="develog_img"
            src="../../public/차량.png"
            alt="" />
          <div>
            <p class="dayfont">
              <span class="dayfont_span">Thursday 4</span>
            </p>
            <p class="month_font">
              #2 May, 2023 News
            </p>
            <p class="box_font">
              두번째 내용
            </p>
          </div>
        </div> 
        <!-- 첫번째 업데이트 -->
        <div
          class="develog-box aos-init"
          data-aos="zoom-in-up"
          data-aos-duration="1500">
          <img
            class="develog_img"
            src="../../public/차량.png"
            alt="" />
          <div>
            <p class="dayfont">
              <span class="dayfont_span">Thursday 4</span>
            </p>
            <p class="month_font">
              #1 May, 2023 News
            </p>
            <p class="box_font">
              첫번째 내용
            </p>
          </div>
        </div> 
      </div>
    </div>
  </div>  
</template>

<script setup>
import AOS from 'aos'
import 'aos/dist/aos.css'
import { onMounted } from 'vue'

onMounted(() => {
  AOS.init()
})
</script>

<style scoped>
@import '@/assets/info.css';

.contain-warp {
  display: flex;
  min-height: 100vh;
  flex-direction: column;
}
</style>
