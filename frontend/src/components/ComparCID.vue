<template>
  <Nav />
  <div class="cidcontain">
    <div class="image-box">
      <button
        v-if="!isimg"
        @click="goHome">
        추가하기
      </button>
      <img
        v-if="isimg"
        class="imginner"
        :src="'/img' + InfoData.specifications.imgurl" />
    </div>
    <div class="spec">
      <hr />
      <div class="spectitle">
        요약
      </div>
      <div class="specinner">
        <!-- 가격 -->
        <p class="costcolor">
          {{ isNaN(Number(InfoData.specifications.cost)) ? '' : Number(InfoData.specifications.cost).toLocaleString() }}
        </p>
        <!-- 차종 -->
        <p>{{ InfoData.specifications.name }}</p>
        <!-- 출시일 -->
        <p>{{ '2023.04.14' }}</p>
        <!-- 연료 -->
        <p>{{ InfoData.specifications.engine }}</p>
        <!-- 연비 -->
        <p>{{ InfoData.specifications.fuel_efficiency }}</p>
      </div>
    </div>
    <div class="spec">
      <hr />
      <div class="spectitle">
        제원
      </div>
      <div class="specinner">
        <!-- 전장/전폭 -->
        <p>{{ ' 전장 / 전폭 ' }}</p>
        <!-- 전고/축거 -->
        <p>{{ ' 전고 / 축거 ' }}</p>
        <!-- 윤거전/후 -->
        <p>{{ ' 윤거전 / 윤거후 ' }}</p>
        <!-- 배기량 -->
        <p>{{ InfoData.specifications.capacity }}</p>
        <!-- 구동박식 -->
        <p>{{ ' 구동박식 ' }}</p>
        <!-- 전륜서프펜션 -->
        <p>{{ ' 전륜서스 ' }}</p>
        <!-- 후륜서프펜션 -->
        <p>{{ ' 후륜서스 ' }}</p>
      </div>
    </div>
    <div class="opt">
      <hr />
      <div class="opttitle">
        옵션
      </div>
      <div class="optinner">
        옵션 넣는곳이요
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios'
import router from '@/router'
import { URL } from '@/components/global'
import { onMounted, reactive, ref } from 'vue'
import { defineProps } from 'vue'
import { useCompStore } from '@/store/index'
import Nav from '@/view/ToNav.vue'

const store = useCompStore()

const props = defineProps({
  cid: Number
})

// true ==> 이미지가 보이게 하고, false ==> button이 보이게한다.
const isimg = ref(false)


onMounted(() => {
  getInfo(), getcid()
})

function getcid() {
  return props.cid
}

const goHome = () => {
  if (store.CarInfomation[0] == '') {
    // 홈 화면에서 시작하는 것이 아니라 비교화면에서 먼저 시작할때 왼쪽부터 채워넣기
    store.CarInfomation[0] = InfoData.specifications
  }
  router.push({ path: '/' })
}

const InfoData = reactive({ // brands에 넣으면 된다 (for문)
  specifications: []
})

const getInfo = async () => {
  console.log('id >>', props.cid)
  if (props.cid == '' || props.cid == undefined) {
    return
  } else {
    isimg.value = true
  }
  try {
    const res = await axios.get(URL + `/car/search/car?cid=${props.cid}`)
    InfoData.specifications = res.data
    console.log('specifications data : ', res.data)
  } catch (error) {
    console.log('제원 주세요', error)
  }

}
</script>

<style scoped>
.cidcontain {
  margin-top: 50%;
  height: 100vh;
  border: 4px double black;
  display: grid;
  grid-template-rows: auto;
}

.spectitle,
.opttitle {
  width: 100%;
  text-align: left;
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 30px;
  margin-left: 10px;
}

.image-box {
  text-align: center;
  margin: auto;
}

.imginner {
  min-width: 200px;
  min-height: 200px;
}

hr {
  border: 2px solid black;
}

p {
  min-width: 100px;
  min-height: 30px;
  text-align: center;
  margin-top: 10px;
}

.costcolor {
  color: blue;
  font-weight: bold;
}
</style>
