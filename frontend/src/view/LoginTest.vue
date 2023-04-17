<template>
  <div>
  </div>
  <button @click="getCookie">
    온세상이버튼이다
  </button>
  <button @click="getUsername">
    너의이름은
  </button>
  <div v-if="username!=''">
    {{ username }}
  </div>
  <div v-else>
    없따
  </div>
</template>

<script setup>
import Cookies from 'js-cookie'
import { URL } from '@/components/global'
import { onMounted, ref } from 'vue'

const token = Cookies.get('Authorization')
const username = ref('')

const getCookie = () =>{
    console.log(token)
}

const getUsername = () => {
  const requestOptions = {
    credentials: 'include'
  }
  fetch(`${URL}/getuser`, requestOptions)
  .then(res => res.text())
  .then(text => username.value = text)
  
}

onMounted(()=>{
  getUsername()
})
</script>

<style>

</style>
