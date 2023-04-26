<template>
  <Nav />
  <PNav />
  <div class="contain">
    <nav class="store-boxes">
      <div
        v-for="store in data.stores"
        :key="store"
        class="store-box">
        <router-link :to="'/mypage/store?cid='+store.carDto.cid">
          <button class="store-inner">
            {{ store.carDto.name }}
          </button>
        </router-link>
      </div>
    </nav>
  </div>
</template>

<script setup>
import Nav from '@/view/ToNav.vue'
import PNav from '@/components/PrivateNav.vue'
import { URL } from '@/components/global'
import { reactive, onMounted } from 'vue'

const data = reactive({
  stores: [],
})

const getStores = () => {
  const requestOptions = {
    credentials: 'include',
  }
  fetch(`${URL}/store/mystores`, requestOptions)
    .then((res) => res.json())
    .then((body) => (data.stores = body))
}

onMounted(() => {
  getStores()
})
</script>

<style scoped>
.contain{
  background: #F0F1F8;
  height: 100vh;
  padding-top: 50px;
}
.store-boxes {
  margin: 0 auto;
  /* background-color: aqua; */
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr 1fr;
  gap: 20px;
  padding: 20px;
  width: 70%;
  background: #fff;
  border-radius: 6px;
  /* padding: 20px 60px 40px 40px; */
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
}

.store-box {
  width: 10em;
  height: 10em;
  padding: 10px;
  /* border: 1px solid black; */
  margin: 0 auto;
}
.store-inner {
  width: 100%;
  height: 100%;
  border: none;
}
</style>
