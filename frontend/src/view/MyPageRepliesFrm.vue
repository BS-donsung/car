<template>
  <Nav />
  <PNav />
  <nav
    v-for="reply in replies"
    :key="reply">
    <router-link to="/">
      {{ reply.context }}
    </router-link>
  </nav>
</template>
  
  <script setup>
  import Nav from '@/view/ToNav.vue'
  import PNav from '@/components/PrivateNav.vue'
  import {reactive, onMounted} from 'vue'
  // 대충 board comp 가져다가 붙여버리기
  const replies = reactive([
      {
          context : '대충내용'
      }
  ])
  
  const getReplies = () =>{
      console.log('대충 fetch같은걸로 replies에 값 주입하기')
  }
  onMounted(()=>{
    getReplies()
  })
</script>

<style scoped></style>
