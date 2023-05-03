<template>
  <div class="cidcontain">
    <div class="image-box">
      <button
        v-if="!isimg"
        class="addcarBtn"
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
          {{
            isNaN(Number(InfoData.specifications.cost))
              ? ""
              : Number(InfoData.specifications.cost).toLocaleString()
          }}
        </p>
        <!-- 차종 -->
        <p>{{ InfoData.specifications.name }}</p>
        <!-- 출시일 -->
        <p>{{ "2023.05.03" }}</p>
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
        <p>{{ " 전장 / 전폭 " }}</p>
        <!-- 전고/축거 -->
        <p>{{ " 전고 / 축거 " }}</p>
        <!-- 윤거전/후 -->
        <p>{{ " 윤거전 / 윤거후 " }}</p>
        <!-- 배기량 -->
        <p>{{ InfoData.specifications.capacity }}</p>
        <!-- 구동박식 -->
        <p>{{ " 구동박식 " }}</p>
        <!-- 전륜서프펜션 -->
        <p>{{ " 전륜서스 " }}</p>
        <!-- 후륜서프펜션 -->
        <p>{{ " 후륜서스 " }}</p>
      </div>
    </div>

    <div class="opt">
      <hr />
      <div class="opttitle">
        옵션
      </div>
      <div
        v-for="caropt in InfoData.specifications.options"
        :key="caropt"
        class="optinner">
        <p>
          <label>
            <input
              v-model="caropt.userchk"
              :disabled="!caropt.chk"
              type="checkbox"
              @change="clickbox(caropt)" />
            {{ caropt.oname }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ isNaN(Number(caropt.cost)) ? '' :
              Number(caropt.cost).toLocaleString() }}
          </label>
        </p>
      </div>
    </div>
  </div>
  <div class="totalcost">
    <div class="totalbox">
      {{ isNaN(Number(total)) ? '' : Number(total).toLocaleString() }}
    </div>
  </div>
  <div class="postBtn">
    <button 
      @click="clickstore">
      저장
    </button>
  </div>
</template>

<script setup>
import axios from 'axios'
import router from '@/router'
import { URL, credentials } from '@/components/global'
import { onMounted, reactive, ref } from 'vue'
import { defineProps } from 'vue'
import { useCompStore } from '@/store/index'

const store = useCompStore()

const props = defineProps({
  cid: Number,
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

const InfoData = reactive({
  // brands에 넣으면 된다 (for문)
  specifications: {
    imgurl: '/imsi.jpg',
  },
})
const total = ref('') // 기본 차값 금액

const getInfo = async () => {
  if (props.cid == '' || props.cid == undefined) {
    return
  } else {
    isimg.value = true
  }
  try {
    const res = await axios.get(URL + `/car/search/car?cid=${props.cid}`)
    InfoData.specifications = res.data

    console.log('specifications data : ', res.data)
    InfoData.specifications.options.forEach(caropt => {
      caropt['userchk'] = false
    })
    total.value = InfoData.specifications.cost
  } catch (error) {
    console.log('제원 주세요', error)
  }
}

// 체크박스
const clickbox = (caropt) => {
  if (caropt.userchk) {
    total.value += caropt.cost
  } else {
    total.value -= caropt.cost
  }
}


const clickstore = () => {
  // console.log(InfoData.specifications.options)
  let data = {
    carDto: {
      cid: props.cid
    },
    options: []
  }
  for (const option of InfoData.specifications.options) {
    // console.log('>>',)
    let imsi_opt = {
      opk: option.opk,
      chk: option.userchk
    }
    data.options.push(imsi_opt)
  }
  console.log('데이터 post',data)

  axios.post(`${URL}/store/store`, data, credentials)
    .then((res) => {
      console.log(res.data)
    })
    .catch('데이터 저장 실패')
}

</script>

<style scoped>
.cidcontain {
  margin-top: 25%;
  background: #fff;
  border-radius: 6px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
  display: grid;
  /* grid-template-rows: auto; */
}

.spectitle,
.opttitle {
  width: 100%;
  text-align: left;
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 30px;
  margin-left: 10px;
  font-family: 'Noto Sans KR', sans-serif;
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

.totalbox {
  width: 100%;
  height: 60px;
  border: 1px solid black;
  border-radius: 6px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: blue;
  font-weight: 600;
  font-size: 22px;
  margin-top: 20px;
  background: white;
}

.addcarBtn {
  width: 200px;
  height: 30px;
  border: 1px solid black;
  box-shadow: 1px 3px 3px 1px rgb(0, 0, 0, 0.6);
  margin-top: 10px;
  border-radius: 6px;
  color: black;
}
.postBtn {
  display: flex;
  justify-content: center;
}
.postBtn button {
  width: 100%;
  height: 50px;
  color: black;
  margin-top: 10px;
  border: 1px solid black;
  border-radius: 6px;
  font-size: 22px;
  font-weight: 600;
  background: white;
}

button:hover {
  background: black;
  color: white;
}
</style>
