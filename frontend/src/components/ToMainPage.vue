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

  <section class="section">
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

            <div class="input-box">
              <span class="icon"><font-awesome-icon :icon="['fas', 'lock']" /></span>
              <input
                v-model="register_data.nickname"
                type="text"
                required />
              <label>닉네임</label>
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
import { reactive } from 'vue'
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
const url = 'http://localhost:9000/login'
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
    const res = await fetch(url, requestOptions)
    console.log(res.headers)
    console.log(res.headers['Authorization'])
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
  const url = 'http://localhost:9000/user/join'
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(register_data)
  }
  try {
    const reg = await fetch(url, requestOptions)
    console.log(reg.json())
  } catch (error) {
    console.log('니 실패함', error)
  }
}
</script>

<style scoped>
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
  background: url('./img/car.jpg') no-repeat;
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
</style>
