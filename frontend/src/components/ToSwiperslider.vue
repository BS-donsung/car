<template>
  <div class="container-md">
    <swiper
      :style="{
        '--swiper-navigation-color': '#fff',
        '--swiper-pagination-color': '#fff',
      }"
      :autoplay="{
        delay: 2000,
        disableOnInteraction: false,
      }"
      :modules="modules"
      effect="fade"
      :slides-per-view="1"
      :space-between="50"
      :speed="1200"
      navigation
      :parallax="true"
      :pagination="{ clickable: true }"
      :scrollbar="{ draggable: true }"
      :slide-change="onSlideChange"
      @swiper="onSwiper">
      <swiper-slide class="wrap-swiper">
        <a href="https://www.motoya.co.kr/news/articleView.html?idxno=37710">
          <div
            class="title"
            data-swiper-parallax="-200">
            기아, ‘The Kia EV9’ 사전 계약 개시
            <div class="subtitle">
              기아가 ‘The Kia EV9(더 기아 이 브이 나인, 이하 EV9)’의 사전 계약을 3일(수)부터 시작한다. EV9은 전기차 전용 플랫폼 E-GMP에 기반한 두 번째 모델로, 기아 라인업 상
              새로운 플래그십이자 전에 없던 새로운 영역을 개척해 나가는 가장 혁신적인 국내 최초 3열 대형 전동화 SUV다.
            </div>
          </div>

          <img
            src="../assets/swiper/EV9.png"
            alt=""
            class="swiper-img" />
        </a>
      </swiper-slide>

      <swiper-slide class="wrap-swiper">
        <a href="https://www.motorgraph.com/news/articleView.html?idxno=32060">
          <div
            class="title"
            data-swiper-parallax="-200">
            벤츠, 11세대 E클래스 공개…'75년 역사와 첨단의 조화'
            <div class="subtitle">
              이번에 공개된 11세대 E클래스(W214)는 7년만에 출시된 풀체인지 모델이다. 75년간의 헤리티지를 계승하는 한편, 디지털 기반의 첨단 편의사양을 대거 탑재했으며, 더욱 지능화된 개인화 기술도
              내장했다.
            </div>
          </div>
          <img
            src="../assets/swiper/벤츠e클.png"
            alt=""
            class="swiper-img" />
        </a>
      </swiper-slide>

      <swiper-slide class="wrap-swiper">
        <a href="https://www.motorgraph.com/news/articleView.html?idxno=32029">
          <div
            class="title"
            data-swiper-parallax="-200">
            현대차, 쏘나타 디 엣지 사전계약…가격은 2787만원부터
            <div class="subtitle">
              현대차가 쏘나타 디 엣지의 주요 사양과 가격을 공개하고 20일부터 사전계약을 시작한다. 이번에 나온 쏘나타 디 엣지는 다양한 사양을 기본으로 적용해 상품성을 높였다. 엔트리 트림인 프리미엄부터
              심리스 호라이즌 램프, 펜더 LED 방향지시등, 세이프티 파워 윈도우(전좌석), 운전석 전동시트(8way) 등을 기본 탑재했다.

            </div>
          </div>
          <img
            src="../assets/swiper/쏘나타.png"
            alt=""
            class="swiper-img" />
        </a>
      </swiper-slide>

      <swiper-slide class="wrap-swiper">
        <a href="https://www.motorgraph.com/news/articleView.html?idxno=32045">
          <div
            class="title"
            data-swiper-parallax="-200">
            폭스바겐코리아, 2023년형 ID.4 6월 출시…'주행거리 늘렸다'
            <div class="subtitle">
              폭스바겐코리아가 2023년형 ID.4를 출시한다. 주행거리는 더 늘리고, 상품성을 끌어올리는 한편 신규 트림 추가로 소비자들의 선택지도 넓힐 계획이다.
            </div>
          </div>
          <img
            src="../assets/swiper/폭스바겐id4.png"
            alt=""
            class="swiper-img" />
        </a>
      </swiper-slide>
    </swiper>
  </div>
</template>

<script setup>
import { Navigation, Pagination, Scrollbar, EffectFade, Autoplay, Parallax } from 'swiper'
import { Swiper, SwiperSlide } from 'swiper/vue'

import 'swiper/css'
import 'swiper/css/navigation'
import 'swiper/css/pagination'
import 'swiper/css/scrollbar'
import 'swiper/css/effect-fade'

const onSwiper = (swiper) => {
  console.log(swiper)
}

const onSlideChange = () => {
  console.log('slide change')
}

const modules = [Navigation, Pagination, Scrollbar, EffectFade, Autoplay, Parallax]
</script>

<style scoped>
.container-md {
  max-width: 1150px;
  margin: 0 auto;
  margin-top: 10px;
  padding: 0 15px;
  margin-bottom: 30px;
}

.wrap-swiper {
  position: relative;
  font-family: 'Roboto', 'Noto Sans KR', sans-serif;
}

.wrap-swiper img {
  filter: brightness(65%);
  z-index: 1;
  width: 100%;
  height: 500px;
}

.wrap-swiper .title {
  text-align: start;
  font-size: 30px;
  font-weight: 600;
  position: absolute;
  top: 80%;
  left: 5%;
  color: white;
  z-index: 1;
}

.wrap-swiper .title .subtitle {
  font-size: 12px;
  text-align: start;
  color: #cacaca;
}

/* @media (max-width: 1000px) {
  .wrap-swiper .title {
    font-size: 25px;
    position: absolute;
    z-index: 1;
    color: white;
    left: 15%;
  }

  .wrap-swiper .title .subtitle {
    font-size: 20px;
    position: absolute;
    color: white;
  }

  .wrap-swiper img {
    width: 800px;
  }
} */
</style>
