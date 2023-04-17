<template>
  <div class="wrapper">
    <span
      class="icon-close"
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
    </div>
    <OAuth />
    <button @click="testAll">
      테스트 all
    </button>
    <button @click="testUser">
      테스트 user
    </button>
  </div>
</template>

<script setup>
import { reactive, defineEmits } from 'vue'
import OAuth from '@/components/OAuthComp.vue'
import cookies from 'vue-cookies'


const login_data = reactive({
  username: '',
  password: '',
})
const url = 'http://localhost:9000'
const emit = defineEmits(['closeLogin','openJoin'])
const iconClose = () => {
  emit('closeLogin')
}
const registerLink = () => {
    emit('openJoin')
    emit('closeLogin')
}

// login method
const tokenlogin = async () => {
  console.log(login_data)
  const requestOptions = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Access-Control-Request-Headers': 'Authorization',
    },
    body: JSON.stringify(login_data),
    credentials: 'include',
  }
  try {
    const res = await fetch(`${url}/login`, requestOptions)
    console.log(res)
  } catch (error) {
    console.log('니 실패함', error)
  }
}

const testAll = async () => {
  console.log('test : ',cookies.get('Authorization'))
  try{
    const res = await fetch(`${url}/all/hihi` ,{credentials: 'include' })
    console.log(res)
  } catch (err) {
    console.log(err)
  }
}
const testUser = async () => {
  console.log('test : ',cookies.get('Authorization'))
  try{
    const res = await fetch(`${url}/user/hihi` ,{credentials: 'include' })
    console.log(res)
  } catch (err) {
    console.log(err)
  }
}

</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap");

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
  position: sticky;
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

.input-box input:focus ~ label,
.input-box input:valid ~ label {
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
</style>
