<template>
  <div class="wrapper">
    <span
      class="icon-close"
      @click="iconClose">
      <font-awesome-icon :icon="['fas', 'x']" />
    </span>

    <div class="logreg-box">
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
    <OAuth />
  </div>
</template>

<script setup>
import { reactive,defineEmits } from 'vue'
import OAuth from '@/components/OAuthComp.vue'
const register_data = reactive({
  username: '',
  email: '',
  password: '',
  nickname: '',
})
const url = 'http://localhost:9000'
const emit = defineEmits(['closeJoin','openLogin'])
const iconClose = () => {
  emit('closeJoin')
}
const loginLink = () => {
  emit('openLogin')
  emit('closeJoin')
}
const registerUser = async () => {
  console.log(register_data)
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(register_data),
  }
  try {
    const reg = await fetch(`${url}/user/join`, requestOptions)
    console.log(reg)
    loginLink()
  } catch (error) {
    console.log('니 실패함', error)
  }
}
</script>

<style scoped>
.wrapper {
  position: absolute;
  top: 0;
  right: 0;
  width: 450px;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.6);
  /* backdrop-filter: blur(20px); */
  box-shadow: -1px 0 10px rgba(0, 0, 0, 0.2);
  z-index: 100;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  padding: 0 40px;
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
.wrapper .form-box.register {
  display: none;
}

.wrapper.active .form-box.register {
  display: block;
}
</style>
