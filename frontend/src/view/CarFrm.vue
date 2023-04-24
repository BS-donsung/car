<template>
  <Nav />
  <div class="car-body">
    <CompCID :cid="route.query.cid" />
    <div class="reviews-box">
      <div
        v-for="review in reviews"
        :key="review"
        class="review">
        {{ review }}
      </div>
    </div>
    <div class="reviews-write-box">
      <input />
      <button @click="postReviews">
        글쓰기
      </button>
    </div>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'
import Nav from '@/view/ToNav.vue'
import CompCID from '@/components/ComparCID.vue'
import { onMounted, ref } from 'vue'

const route = useRoute()

const reviews = ref([])

const getReviews = () =>{
  const requestOptions = {
    credentials: 'include'
  }
  fetch(`${URL}/getuser`, requestOptions)
  .then(res => res.text())
  .then( => )
}
const  postReviews = () =>{
  console.log('대충 fetch 같은걸로 리뷰 쓰기')
}
onMounted(() => {
  getReviews()
})
</script>

<style scoped>
.cidcontain {
  width: 70vh;
  margin: 0;
}
.car-body {
  text-align: center;
  border: none;
  width: 70vw;
  margin: 0 auto;
  margin-top: 88px;
  display: grid;
}
</style>
