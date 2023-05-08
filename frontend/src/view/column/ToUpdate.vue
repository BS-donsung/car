<template>
  <div class="contain-warp">
    <div class="info-warp">
      <hr class="hr_tag" />
      <div class="info-news">
        <img
          class="info-img"
          :src="`/img/info.png`"
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
        <div
          v-for="post in posts"
          :key="post"
          class="develog-box aos-init"
          data-aos="zoom-in-up"
          data-aos-duration="1500">
          <img
            class="develog_img"
            :src="`/img/column${post.thumbnail}`"
            alt="" />
          <div>
            <p class="dayfont">
              <span class="dayfont_span">
                {{ post.title }}
              </span>
            </p>
            <p class="month_font">
              {{ post.createdDate }}
            </p>
            <div
              class="box_font"
              v-html="post.content">
            </div>
          </div>
        </div> 
      </div>
    </div>
  </div>  
</template>

<script setup>
import { URL } from '@/components/global'
import AOS from 'aos'
import 'aos/dist/aos.css'
import axios from 'axios'
import { onMounted, ref } from 'vue'

const posts = ref([])

const getColums = async (params) => {
  const res = await axios.get(`${URL}/board/newest`, {params:{params}})
  posts.value = res.data
}

onMounted(() => {
  getColums(),
  AOS.init()
})
</script>

<style scoped>
@import '@/assets/info.css';

.contain-warp {
  min-height: 100vh;
  background: #F0F1F8;
  padding-top: 30px;
}
</style>
