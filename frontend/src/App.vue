<template>
  <router-view name="user" />
  <div
    v-if="route.path=='/'"
    class="logo">
    <router-link to="/">
      <img
        src="@/components/img/logo2.png"
        alt="" />
    </router-link>
  </div>
  <router-view name="header" />
  <router-view v-if="isRender" />
</template>

<script setup>
import axios from 'axios'
import { onMounted, ref } from 'vue'
import { useCompStore } from '@/store/index'

import { URL,credentials } from '@/components/global'
import { useRoute } from 'vue-router'


const route = useRoute()
const store = useCompStore()
const isRender = ref(false)

const getUsername = async () => {
  try {
    const data = await axios.get(`${URL}/user/getuser`, credentials)
    store.setUser(data.data)
  } catch (error) {
    console.log('App 오류', error)
  }
  isRender.value = true
}

onMounted(() => {
  getUsername()
})

</script>

<style>
body {
  background-color: #F0F1F8;
}
</style>
