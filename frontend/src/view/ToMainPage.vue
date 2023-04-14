<template>
  <header class="header">
    <router-link
      to="/"
      class="logo">
      LOGO
    </router-link>

    <nav class="navbar">
      <router-link to="/comparison">
        비교
      </router-link>
      <a href="#">Service</a>
      <a href="#">Content</a>
      <button
        class="btnLogin-popup"
        @click="openLogin">
        로그인
      </button>
    </nav>
  </header>
  <div id="body"></div>
  <Teleport to="#body">
    <login-comp
      v-if="isLogin"
      @close-login="closeLogin"
      @open-join="openJoin" />
    <join-comp
      v-if="isJoin"
      @close-join="closeJoin"
      @open-login="openLogin" />
  </Teleport>
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
          <!-- v-text="brand.brand"> -->
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
          v-text="segment.segment">
        </option>
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
          v-text="car.name">
        </option>
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
</template>

<script setup>
import LoginComp from '@/components/LoginComp.vue'
import JoinComp from '@/components/JoinComp.vue'
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
const getData = reactive({ // brands에 넣으면 된다 (for문)
  brands: [],
  segment: [],
  car: []
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
  id: []
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

const isLogin = ref(false)
const isJoin = ref(false)
const openLogin = () => { isLogin.value = true }
const openJoin = () => { isJoin.value = true }
const closeLogin = () => { isLogin.value = false }
const closeJoin = () => { isJoin.value = false }

</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap");
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Popins', sans-serif;
}
#body{
  background: url('@/components/img/car.jpg') no-repeat;
}
.header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  padding: 20px 100px;
  background: transparent;
  display: flex;
  justify-content: space-between;
  align-items: center;
  z-index: 99;
}

.logo {
  font-size: 32px;
  color: #fff;
  text-decoration: none;
  font-weight: 700;
}

.navbar a {
  font-size: 18px;
  color: #fff;
  text-decoration: none;
  font-weight: 500;
  margin-right: 40px;
}

.navbar .btnLogin-popup {
  position: relative;
  background: transparent;
  border: none;
  outline: none;
  font-size: 18px;
  color: #fff;
  font-weight: 500;
  cursor: pointer;
}

.navbar .btnLogin-popup::before {
  content: '';
  position: absolute;
  width: 100%;
  height: 2px;
  background: #fff;
  bottom: -4px;
  left: 0;
  opacity: 0.85;
}

.wrapper {
  position: absolute;
  top: 0;
  right: 0;
  width: 450px;
  height: 100%;
  background: transparent;
  backdrop-filter: blur(20px);
  box-shadow: -1px 0 10px rgba(0, 0, 0, 0.2);
  z-index: 100;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  padding: 0 40px;
  opacity: 0;
  pointer-events: none;
}

.wrapper.active-popup {
  opacity: 1;
  pointer-events: auto;
}

.icon-close {
  top: 0;
  right: 0;
  position: absolute;
  width: 45px;
  height: 45px;
  background: #fff;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
  border-bottom-left-radius: 10px;
}

.icon-close .i {
  font-size: 32px;
  color: #333;
}

.wrapper .logreg-box {
  width: 100%;
}

.wrapper .form-box.login {
  display: block;
}

.wrapper.active .form-box.login {
  display: none;
}

.wrapper .form-box.register {
  display: none;
}

.wrapper.active .form-box.register {
  display: block;
}

.logreg-box .logreg-title {
  text-align: center;
  margin-bottom: 40px;
  color: #fff;
}

.logreg-title h2 {
  font-size: 32px;
}

.logreg-title p {
  font-size: 14px;
  font-weight: 500;
}

.logreg-box .input-box {
  position: relative;
  width: 100%;
  height: 50px;
  margin: 30px 0;
  border-bottom: 2px solid #fff;
}

.input-box input {
  width: 100%;
  height: 100%;
  background: transparent;
  border: none;
  outline: none;
  font-size: 16px;
  color: #fff;
  font-weight: 500;
  padding-right: 25px;
}

.input-box label {
  position: absolute;
  top: 50%;
  left: 0;
  transform: translateY(-50%);
  font-size: 16px;
  color: #fff;
  font-weight: 500;
  transition: 0.5s ease;
}

.input-box .icon {
  position: absolute;
  top: 50%;
  right: 0;
  font-size: 19px;
  color: #fff;
  transform: translateY(-50%);
}

.input-box input:focus~label,
.input-box input:valid~label {
  top: -5px;
}

.logreg-box .remember-forgot {
  font-size: 14.5px;
  color: #fff;
  font-weight: 500;
  margin: -15px 0 15px;
  display: flex;
  justify-content: space-between;
}

.remember-forgot label input {
  accent-color: #fff;
  margin-right: 3px;
}

.remember-forgot a {
  color: #fff;
  text-decoration: none;
}

.remember-forgot a:hover {
  text-decoration: underline;
}

.logreg-box .btn {
  width: 100%;
  height: 45px;
  background: #fff;
  border: none;
  outline: none;
  border-radius: 40px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  cursor: pointer;
  font-size: 16px;
  color: #222;
  font-weight: 600;
}

.logreg-box .logreg-link {
  font-size: 14.5px;
  color: #fff;
  text-align: center;
  font-weight: 500;
  margin-right: 25px;
  margin: 25px 0 15px;
}

.logreg-link p a {
  color: #fff;
  text-decoration: none;
  font-weight: 600;
}

.logreg-link p a:hover {
  text-decoration: underline;
}

.media-options {
  display: flex;
  align-items: center;
  flex-direction: column;
}

.media-options a {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  width: 200%;
  height: 50px;
  border: 2px solid #fff;
  margin: 10px 0;
  background: transparent;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  border-radius: 40px;
  color: #fff;
  text-decoration: none;
}

.media-options a:hover {
  background: rgba(255, 255, 255, 0.1);
}

.media-options a .i {
  font-size: 16px;
  margin: 0 8px 1.5px 0;
}

.media-options a span {
  font-size: 16px;
  font-weight: 500;
}

/* select searchFrm css */
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
