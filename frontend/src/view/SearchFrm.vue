<template>
  <div class="back-img">
    <div class="searchFrm">
      <div class="srcinner">
        <!-- 브랜드 -->
        <select
          v-model="b_text"
          class="custom b"
          @change="getCar">
          <option
            class="b"
            disable
            value="">
            브랜드를 선택해주세요
          </option>
          <option
            v-for="brand in getData.brands"
            :key="brand"
            class="b"
            :value="brand.brand">
            {{ brand.brand }}
          </option>
        </select>

        <!-- 차급 -->
        <select
          v-model="s_text"
          class="custom s"
          @change="getCar">
          <option
            class="s"
            disable
            value="">
            차급을 선택해주세요
          </option>
          <option
            v-for="segment in getData.segment"
            :key="segment"
            class="s"
            :value="segment.segment"
            v-text="segment.segment"></option>
        </select>

        <!-- 차종 -->
        <select
          v-model="v_text"
          class="custom v"
          @change="getCid">
          <option
            class="v"
            disable
            value="">
            차종을 선택해주세요
          </option>
          <option
            v-for="car in getData.car"
            :key="car"
            class="v"
            :value="car.cid"
            v-text="car.name"></option>
        </select>

        <button
          class="addBtn"
          type="button"
          @click="handover">
          추가
          <font-awesome-icon :icon="['fas', 'plus']" />
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { URL } from '@/components/global'
import axios from 'axios'
import router from '@/router'
import { useCompStore } from '@/store/index'

// 검색 페이지 스크립
const b_text = ref('')
const s_text = ref('')
const v_text = ref('')

const store = useCompStore()

onMounted(() => {
  getBrand(), getSegment()
})

// Backend 연동
const getData = reactive({
  // brands에 넣으면 된다 (for문)
  brands: [],
  segment: [],
  car: [],
})

const getBrand = async () => {
  try {
    const res = await axios.get(URL + '/brand/all')
    getData.brands = res.data
    console.log('brand data : ', res.data)
  } catch (error) {
    console.log('브랜드 주세요', error)
  }
}

const getSegment = async () => {
  try {
    const res = await axios.get(URL + '/segment/all')
    getData.segment = res.data
    console.log('segment data : ', res.data)
  } catch (error) {
    console.log('차급 주세요', error)
  }
}

const getCar = async () => {
  let path = '/car/search?'
  if (b_text.value != '') {
    path = path + `brand=${b_text.value}&`
  }
  if (s_text.value != '') {
    path = path + `segment=${s_text.value}&`
  }
  console.log(b_text.value + '/' + s_text.value)
  console.log(path)
  try {
    const res = await axios.get(URL + path)
    getData.car = res.data
  } catch (error) {
    console.log('차급 주세요', error)
  }
}

const usecid = reactive({
  id: [],
})
function getCid(e) {
  const get = e.target.value
  usecid.id = get
  console.log('get >> ', get)
  console.log('id >> ', usecid.id)
}

const handover = () => {
  console.log('성공? >> ', usecid.id)
  store.getCarInfo(usecid.id)

  if (store.RIGHT_CAR != undefined) {
    console.log('CarInfomationL >> ', store.CarInfomation[1])
  }
  console.log('CarInfomationR >> ', store.CarInfomation[0])

  router.push({ path: '/comparison' })
}

</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap");

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Popins", sans-serif;
}

.back-img {
  background-image: url('@/components/img/car.jpg');
  min-height: 100vh;
  background-size: cover;
  background-position: center;
}

.searchFrm {
  width: 950px;
  height: 300px;
  transform: translate(40%);
  border: 1px solid white;
  position: absolute;
  top: 30%;
  background: transparent;
  backdrop-filter: blur(3px);
  box-shadow: -1px 0 10px rgba(0, 0, 0, 0.2);
  border-radius: 20px;
}

.searchFrm .srcinner {
  width: 700px;
  height: 70px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.custom {
  width: auto;
  height: 50px;
  margin-right: 20px;
  border-radius: 10px;
}

.custom:hover {
  border: 6px double greenyellow;
}

.b,
.s,
.v {
  text-align-last: center;
  text-align: center;
  font-size: 15px;
  font-weight: bold;
  background-color: black;
  color: white;
}

.addBtn {
  width: 100px;
  height: 50px;
  border-radius: 10px;
  background-color: transparent;
  color: white;
  font-size: 16px;
  margin-left: 20px;
  cursor: pointer;
}

.addBtn:hover {
  background-color: white;
  color: black;
}
</style>
