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
        @click="btnPopup">
        로그인
      </button>
    </nav>
  </header>

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
          v-for="brand in GetData.brands"
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
          v-for="segment in GetData.segment"
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
          v-for="car in GetData.car"
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


  <!-- 로그인 페이지 구현 -->
  <section class="section">
    <!-- 검색창 구현 -->
    <div
      class="wrapper"
      :class="wrapper.classList">
      <span
        class="
              icon-close"
        @click="iconClose">
        <font-awesome-icon :icon="['fas', 'x']" />
      </span>
      <div class="logreg-box">
        <!-- login form -->
        <div class="form-box login">
          <div class="logreg-title">
            <h2>로그인</h2>
            <p>플랫폼을 이용하시려면 로그인 해주세요</p>
          </div>

          <form action="#">
            <div class="input-box">
              <span class="icon"><font-awesome-icon :icon="['fas', 'id-card']" /></span>
              <input
                v-model="login_data.username"
                type="id"
                required />
              <label>아이디</label>
            </div>

            <div class="input-box">
              <span class="icon"><font-awesome-icon :icon="['fas', 'lock']" /></span>
              <input
                v-model="login_data.password"
                type="password"
                required />
              <label>비밀번호</label>
            </div>

            <div class="remember-forgot">
              <label><input type="checkbox" />자동로그인</label>
              <a href="#">비밀번호를 잃어버리셨나요?</a>
            </div>

            <button
              type="submit"
              class="btn"
              @click.prevent="tokenlogin">
              로그인
            </button>

            <div class="logreg-link">
              <p>
                계정이 없습니까?
                <a
                  href="#"
                  class="register-link"
                  @click="registerLink">가입하기</a>
              </p>
            </div>
          </form>
        </div>

        <!-- register form -->
        <div class="form-box register">
          <div class="logreg-title">
            <h2>회원가입</h2>
            <p>귀하의 신원을 확인하기 위해 다음을 제공하십시오</p>
          </div>

          <form action="#">
            <div class="input-box">
              <span class="icon"><font-awesome-icon :icon="['fas', 'user']" /></span>
              <input
                type="text"
                required />
              <label>이름</label>
            </div>

            <div class="input-box">
              <span class="icon"><font-awesome-icon :icon="['fas', 'user']" /></span>
              <input
                v-model="register_data.nickname"
                type="text"
                required />
              <label>닉네임</label>
            </div>

            <div class="input-box">
              <span class="icon"><font-awesome-icon :icon="['fas', 'envelope']" /></span>
              <input
                v-model="register_data.email"
                type="email"
                required />
              <label>이메일</label>
            </div>

            <div class="input-box">
              <span class="icon"><font-awesome-icon :icon="['fas', 'id-card']" /></span>
              <input
                v-model="register_data.username"
                type="id"
                required />
              <label>아이디</label>
            </div>

            <div class="input-box">
              <span class="icon"><font-awesome-icon :icon="['fas', 'lock']" /></span>
              <input
                v-model="register_data.password"
                type="password"
                required />
              <label>비밀번호</label>
            </div>

            <div class="remember-forgot">
              <label><input type="checkbox" />이용약관에 동의합니다</label>
            </div>

            <button
              type="submit"
              class="btn"
              @click.prevent="registerUser">
              회원가입
            </button>

            <div class="logreg-link">
              <p>
                이미 회원이신가요?
                <a
                  href="#"
                  class="login-link"
                  @click="loginLink">돌아가기</a>
              </p>
            </div>
          </form>
        </div>
      </div>

      <div class="media-options">
        <a href="#">
          <font-awesome-icon
            :icon="['fab', 'google']"
            style="color: #006af5;"
            class="i" />
          <span>Login with Google</span>
        </a>

        <a href="#">
          <font-awesome-icon
            :icon="['fas', 'k']"
            style="color: #faf200;"
            class="i" />
          <span>Login with KaKao</span>
        </a>

        <a href="#">
          <font-awesome-icon
            :icon="['fab', 'neos']"
            style="color: #62f500;"
            class="i" />
          <span>Login with Naver</span>
        </a>
      </div>
    </div>
  </section>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { URL } from '@/components/global'
import axios from 'axios'
import router from '@/router'
import { useCompStore } from '@/store/index'


let wrapper = reactive({ classList: [] })
const btnPopup = () => {
  wrapper.classList.push('active-popup')
  alert('회원가입을 하시겠습니까?')
}
const registerLink = () => {
  wrapper.classList.push('active')
}
const loginLink = () => {
  wrapper.classList.pop('active')
}
const iconClose = () => {
  wrapper.classList.pop('active-popup')
  wrapper.classList.pop('active')
}
// login api
const login_data = reactive({
  username: '',
  password: '',
})

const tokenlogin = async () => {
  console.log(login_data)
  const requestOptions = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Access-Control-Request-Headers': 'Authorization'
    },
    body: JSON.stringify(login_data),
    credentials: 'include'
  }
  try {
    const res = await fetch(URL, requestOptions)
    console.log(res.headers)
    // console.log(res.headers['Authorization'])
  } catch (error) {
    console.log('니 실패함', error)
  }
}
// register api
const register_data = reactive({
  username: '',
  email: '',
  password: '',
  nickname: ''
})
const registerUser = async () => {
  console.log(register_data)
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(register_data)
  }
  try {
    const reg = await fetch(URL + '/user/join', requestOptions)
    console.log(reg.json())
  } catch (error) {
    console.log('니 실패함', error)
  }
}

// 검색 페이지 스크립
const b_text = ref('')
const s_text = ref('')
const v_text = ref('')

const store = useCompStore()

onMounted(() => {
  getBrand(), getSegment()
})



// Backend 연동
const GetData = reactive({ // brands에 넣으면 된다 (for문)
  brands: [],
  segment: [],
  car: []
})


const getBrand = async () => {

  try {
    const res = await axios.get(URL + '/brand/all')
    GetData.brands = res.data
    console.log('brand data : ', res.data)
  } catch (error) {
    console.log('브랜드 주세요', error)
  }

}

const getSegment = async () => {

  try {
    const res = await axios.get(URL + '/segment/all')
    GetData.segment = res.data
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
    GetData.car = res.data
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

</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Popins', sans-serif;
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

.section {
  background: url('@/components/img/car.jpg') no-repeat;
  min-height: 100vh;
  background-size: cover;
  background-position: center;
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
