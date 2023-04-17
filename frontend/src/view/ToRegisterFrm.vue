<template>
  <body>
    <section class="container forms">
      <div class="form login">
        <div class="form-content">
          <header>회원가입</header>

          <form action="#">
            <div class="field input-field">
              <input
                type="text"
                class="input"
                placeholder="이름" />
            </div>

            <div class="field input-field">
              <input
                v-model="register_data.nickname"
                type="text"
                class="input"
                placeholder="닉네임" />
            </div>

            <div class="field input-field">
              <input
                v-model="register_data.username"
                type="id"
                class="input"
                placeholder="ID" />
            </div>

            <div class="field input-field">
              <input
                v-model="register_data.email"
                type="email"
                class="input"
                placeholder="E-mail" />
            </div>

            <div class="field input-field">
              <input
                v-model="register_data.password"
                type="password"
                class="password"
                placeholder="비밀번호" />
              <font-awesome-icon
                :icon="['fas', 'eye-slash']"
                class="eye-slash" />
            </div>

            <div class="field input-field">
              <input
                type="password"
                class="password"
                placeholder="비밀번호 확인" />
              <font-awesome-icon
                :icon="['fas', 'eye-slash']"
                class="eye-slash" />
            </div>

            <div class="field button-field">
              <button
                @click.prevent="registerBtn">
                회원가입
              </button>
            </div>

            <div class="form-link">
              <span>이미 회원인가요?</span> <a
                href="#"
                class="login-link"
                @click="loginfrm">로그인 하기</a>
            </div>
          </form>
        </div>

        <div class="line"></div>

        <!-- 로그인 a => button으로 교환 -->
        <div class="media-options">
          <a
            href="#"
            class="field facebook">
            <span class="facebook-icon"></span>
            <span>Facebook 로그인</span>
          </a>
        </div>

        <div class="media-options">
          <a
            href="#"
            class="field google">
            <span class="google-icon"></span>
            <span>Google 로그인</span>
          </a>
        </div>

        <div class="media-options">
          <a
            href="#"
            class="field naver">
            <span class="naver-icon"></span>
            <span>NAVER 로그인</span>
          </a>
        </div>
      </div>
    </section>
  </body>
</template>

<script setup>
import router from '@/router'
import { reactive } from 'vue'
import { URL } from '@/components/global'

const loginfrm = () => {
  router.push({ path: '/loginfrm' })
}

// register api
const register_data = reactive({
  username: '',
  nickname: '',
  email: '',
  password: '',
})

const registerBtn = async () => {
  alert('회원가입이 완료되었습니다.')
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(register_data)
  }
  try {
    const reg = await fetch(URL + '/user/join', requestOptions)
    console.log(reg.json())
  } catch (error) {
    console.log('회원가입에 실패했습니다.', error)
  }
}

if (register_data == '') {
  alert('경고')
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&family=Open+Sans:ital,wght@0,300;0,600;1,300;1,600&family=Poor+Story&family=Poppins:wght@300;400;500;600;700&display=swap');

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}

header {
  font-size: 28px;
  font-weight: 600;
  color: #232836;
  text-align: center;
}

form {
  margin-top: 30px;
}

.container {
  height: 100vh;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: transparent;
}

.form {
  max-width: 460px;
  width: 100%;
  padding: 30px;
  border-radius: 6px;
  background: #fff;
  border: 1px solid #cacaca;
}

/* 미디어 field 버튼 */
.form .field {
  position: relative;
  height: 50px;
  width: 400px;
  margin-top: 20px;
  border-radius: 6px;
}

.field input,
.field button {
  height: 50px;
  width: 400px;
  font-size: 16px;
  font-weight: 400;
  border: none;
  border-radius: 6px;
}

.field input {
  border: 1px solid #cacaca;
  padding: 0 15px;
  outline: none;
}

.field input:hover {
  border-bottom-width: 2px;
}

.eye-slash {
  position: absolute;
  top: 50%;
  right: 10px;
  transform: translateY(-50%);
  padding: 5px;
  font-size: 18px;
  color: #8b8b8b;
  cursor: pointer;
}

.field button {
  color: #fff;
  background-color: #0171d3;
  transition: all 0.3 ease;
  cursor: pointer;
}

.field button:hover {
  background-color: #016dcb;
}

.form-link {
  text-align: center;
  margin-top: 10px;
}

.form-link a {
  margin-right: 30px;
}

.form-link span,
.form-link a {
  font-size: 14px;
  font-weight: 400;
  color: #232836;
}

.form a {
  color: #0171d3;
  text-decoration: none;
}

form a:hover {
  text-decoration: underline;
}

.line {
  position: relative;
  height: 1px;
  width: 100%;
  margin: 36px 0;
  background-color: #d4d4d4;
}

.line::before {
  content: 'OR';
  position: absolute;
  top: -10px;
  left: 40%;
  background-color: #fff;
  color: #232836;
  padding: 0 15px;
  transform: translate(-60% -50%);
}

/* 미디어 로그인 */
.media-options a {
  display: flex;
  align-items: center;
  justify-content: center;
}

a.facebook,
a.google,
a.naver {
  color: #232836;
  border: 1px solid #cacaca;
}

.facebook-icon {
  width: 21px;
  height: 21px;
  position: absolute;
  top: 50%;
  left: 15px;
  transform: translateY(-50%);
  background: url(https://rs.nxfs.nexon.com/nxlogin/images/set_logo.svg) no-repeat 0 -100px;
}

.google-icon {
  width: 21px;
  height: 21px;
  position: absolute;
  top: 50%;
  left: 15px;
  transform: translateY(-50%);
  background: url(https://rs.nxfs.nexon.com/nxlogin/images/set_logo.svg) no-repeat 0 -150px;
}

.naver-icon {
  width: 21px;
  height: 21px;
  position: absolute;
  top: 50%;
  left: 15px;
  transform: translateY(-50%);
  background: url(https://rs.nxfs.nexon.com/nxlogin/images/set_logo.svg) no-repeat 0 -200px;
}
</style>
