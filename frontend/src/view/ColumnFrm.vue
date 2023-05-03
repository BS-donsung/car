<template>
  <Nav />
  <div class="column-warp">
    <div class="column-contain">
      <div class="column-box">
        <div class="column-img">
          <img
            src="../../public/img/brand/벤츠.jpg"
            alt="" />
          <div class="text-contain">
            <div class="text-top">
              이거 현대차 아니었어? 우리가 몰랐던 '배지 엔지니어링'의 세계 [주말의MG]
            </div>
            <div class="text-bottom">
              <div class="sub-text">
                해외에서 만난 국산차에 낯선 엠블럼이 달린 걸 보면 고개가 갸우뚱해진다. 디자인을 보면 현대차, 기아, 르노코리아 모델이 맞는데, 전혀 다른 엠블럼을 달고 있기 때문이다. 자동차 업계에서는
                이런 '라벨 갈이' 차들을 '배지 엔지니어링' 모델이라고 부른다.
                배지 엔지니어링은 대부분 같은 그룹에 속해있는 브랜드들 사이에 흔히 있는 일이다. 특정 시장에서 부족한 라인업을 보강하거나, 기술력 확보를 위한 이해관계로 탄생하기도 한다. 모터그래프가
                해외에서 전혀 다른 모델로 판매되고 있는 국산차를 정리해봤다.
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import Nav from '@/view/ToNav.vue'
</script>

<style scoped>
.column-warp {
  width: 100%;
  height: 100vh;
  margin: 0 auto;
  background: pink;
}

.column-contain {
  width: 1150px;
  height: 100%;
  margin: 0 auto;
  overflow: hidden;
  background: blue;
}

.column-box {
  width: 700px;
  height: 200px;
  margin: 0 auto;
  margin-top: 30px;
  background: red;
}

.column-img {
  display: flex;
  width: 100%;
  height: 100%;
}

.column-img img {
  width: 200px;
  height: 100%;
  /* background: black; */
}

.text-contain {
  width: 500px;
  height: 100%;
  flex-direction: column;
  display: flex;
  background: orange;
}

.text-top {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  font-weight: 700;
  background: lightblue;
  /* cursor: pointer; */
}

.text-bottom {
  flex: 1;
  background: lightcoral;
  display: flex;
  align-items: center;
}

.sub-text {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}</style>
