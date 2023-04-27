<template>
  <router-view />
</template>

<script setup>
import axios from 'axios'
import { onMounted } from 'vue'
import { useCompStore } from '@/store/index'
import { URL } from '@/components/global'

const store = useCompStore()

const getUsername = () => {
  const credentials = {
    withCredentials: 'include',
  }
  axios.get(`${URL}/user/getuser`,credentials)
  .then(res => res.data)
  .then(body => {
    store.setUsername(body.username === undefined? '':body.username)
    })
  .catch(store.setUsername(''))

}

onMounted(()=>{
  getUsername()
})

</script>

<style>
body{
  background-color: #F0F1F8;
}
</style>
