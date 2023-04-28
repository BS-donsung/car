<template>
  <PNav />
  <div class="contain">
    <div class="myBody">
      <span>{{ user.username }}님 반갑습니다</span>
      <span>
        닉네임 : <input
          v-model="user.nickname"
          type="text" />
        <button
          class="changeNick"
          @click="changeNick">수정하기</button>
      </span>
      <span>
        이메일 : <input
          v-model="user.email"
          type="email"
          disabled />
      </span>
      <span>
        exp :
        <progress
          class="todo-progress"
          :value="user.exp"
          min="0"
          max="100"></progress>
        {{ user.level }}Lv
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
  </div>
</template>

<script setup>
import PNav from '@/components/PrivateNav.vue'
import { ref, onMounted } from 'vue'
import { URL, credentials } from '@/components/global'
import { useCompStore } from '@/store/index'
import axios from 'axios'
const store = useCompStore()

const user = ref('')
const isCloak = ref(['', '', ''])

const changeNick = () => {
  console.log('>>', user.value.nickname)
  let data = {
    nickname: user.value.nickname,
  }
  axios.post(`${URL}/user/nickname`, data, credentials)
}
const getUserInfo = () => {
  user.value = store.getUser()
  isCloak.value = [
    user.value.provider == 'google' ? '' : 'cloaking',
    user.value.provider == 'naver' ? '' : 'cloaking',
    user.value.provider == 'facebook' ? '' : 'cloaking',
  ]
}

onMounted(() => {
  getUserInfo()
})
</script>

<style scoped>
.contain {
  background: #f0f1f8;
  height: 100vh;
  padding-top: 50px;
}
.myBody {
  margin: 0 auto;
  display: grid;
  align-content: center;
  width: 70%;
  background: #fff;
  border-radius: 6px;
  padding: 20px 60px 40px 40px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
}

.myBody span input {
  border: none;
  background-color: #f0f1f8;
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
