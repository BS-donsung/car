<template>
  <Nav />
  <PNav />
  <nav id="store-boxes">
    <div
      v-for="store in data.stores"
      :key="store"
      class="store-box">
      <router-link :to="'/mypage/store?cid='+store.carDTO.cid">
        <button class="store-inner">
          {{ store.carDTO.name }}
        </button>
      </router-link>
    </div>
  </nav>
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
#store-boxes {
  /* background-color: aqua; */
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr 1fr;
  grid-column-gap: 20px;
  grid-row-gap: 20px;
  padding: 20px;
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
