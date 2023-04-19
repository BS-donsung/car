<template>
  <Nav />
  <PNav />
  <div class="myBody">
    <span>{{ username }}님 반갑습니다</span>
    <span>
      닉네임 : <input
        v-model="nickname"
        type="text" />
      <button
        class="changeNick"
        @click="changeNick">수정하기</button>
    </span>
    <span> 이메일 : <input
      v-model="email"
      type="email"
      disabled /> </span>
    <span>
      exp :
      <progress
        class="todo-progress"
        :value="exp"
        min="0"
        max="100"></progress>
      {{ level }}Lv
    </span>
    <span>
      <i
        class="google-icon icon"
        :class="isCloak[0]"></i>
      <i
        class="naver-icon icon"
        :class="isCloak[1]"></i>
      <i
        class="facebook-icon icon"
        :class="isCloak[2]"></i>
    </span>
  </div>
</template>

<script setup>
import Nav from '@/view/ToNav.vue'
import PNav from '@/components/PrivateNav.vue'
import { ref, onMounted } from 'vue'
import { URL } from '@/components/global'

const username = ref('')
const nickname = ref('별명')
const email = ref('이메일')
const exp = ref('56')
const level = ref('6')
const isCloak = ref(['cloaking', 'cloaking', ''])

const changeNick = () => {
  console.log('대충 닉네임 바꾸는 로직')
}
const getUserInfo = () => {
  console.log('대충 username, nickname, email, exp 가져오는 로직')
  console.log('대충 exp가지고 lv이랑 percentage 가공하는 로직')
  console.log('대충 provider가지고 isCloak 조정해서 oapacity 설정하는 로직')

  const requestOptions = {
    credentials: 'include'
  }
  fetch(`${URL}/getuser`, requestOptions)
  .then(res => res.text())
  .then(text => username.value = text)
}
onMounted(()=>{
  getUserInfo()
})
</script>

<style scoped>
.myBody {
  display: grid;
  align-content: center;
}

.myBody span {
  display: inline-block;
  margin: 20px auto;
  height: 20px;
  width: 30em;
}

.icon {
  height: 30px;
  width: 30px;
  top: 15px;
  margin: 0 10px;
}

.changeNick {
  margin-left: 10px;
}

.cloaking {
  opacity: 0.1;
}

progress {
  margin: 0.3em 0;
  height: 0.4em;
  width: 50%;
  appearance: none;
}
progress::-webkit-progress-bar {
  border-radius: 4px;
  box-shadow: inset 3px 3px 4px #ebebeb;
}
progress::-webkit-progress-value {
  border-radius: 10px;
  background: #93f9b9;
  background: -webkit-linear-gradient(to rigth, #93f9b9, #41b98f);
  background: linear-gradient(to right, #93f9b9, #41b98f);
}

.facebook-icon {
  display: inline-block;
  background: url(https://www.mhafbfun.com/wp-content/uploads/2017/12/Facebook-Logo-Square-768x768-1.png);
  background-size: contain;
}

.google-icon {
  display: inline-block;
  background: url(https://upload.wikimedia.org/wikipedia/commons/5/53/Google_%22G%22_Logo.svg);
  background-size: contain;
}
.naver-icon {
  display: inline-block;
  background: url(https://image.rocketpunch.com/company/5466/naver_logo.png?s=400x400&t=inside);
  background-size: contain;
}
</style>
