<template>
  <router-view v-if="isRender" />
</template>

<script setup>
import axios from 'axios'
import { onMounted, ref } from 'vue'
import { useCompStore } from '@/store/index'
import { URL, credentials } from '@/components/global'

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
