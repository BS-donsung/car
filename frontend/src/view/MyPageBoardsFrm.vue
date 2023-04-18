<template>
  <Nav />
  <PNav />
  <nav
    v-for="board in boards"
    :key="board">
    <router-link to="/">
      {{ board.title }}
    </router-link>
  </nav>
</template>

<script setup>
import Nav from '@/view/ToNav.vue'
import PNav from '@/components/PrivateNav.vue'
import {reactive, onMounted} from 'vue'
// 대충 board comp 가져다가 붙여버리기
const boards = reactive([
    {
        title : '대충제목'
    }
])

const getBoard = () =>{
    console.log('대충 fetch같은걸로 boards에 값 주입하기')
}
onMounted(()=>{
    getBoard()
})
</script>

<style></style>
