<template>
  <Nav />
  <div class="brand-box">
    <div
      v-for="brand in data.brands"
      :key="brand"
      class="brand"
      @click="getCarList(brand.brand)">
      <img
        class="brand-img"
        :src="`/img/brand/${brand.brand}.jpg`" />
      {{ brand.brand }}
    </div>
    <div
      class="brand"
      @click="getCarList(undefined)">
      ALL
    </div>
  </div>
  <div class="cars-body">
    <div
      v-for="car in data.carList"
      :key="car"
      class="carbox">
      <router-link :to="'/car?cid='+car.cid">
        <img
          class="carimg"
          :src="'/img'+car.imgurl" />
        {{ car.name }}<br />
        <!-- car : {{ car }} -->
      </router-link>
    </div>
  </div>
</template>

<script setup>
import Nav from '@/view/ToNav.vue'
import { onMounted, reactive } from 'vue'
import { URL } from '@/components/global'
import axios from 'axios'


const data = reactive({
  carList : [],
  brands: [],
})


const getBrand = async () => {
  try {
    
    const res = await axios.get(`${URL}/brand/all?`)
    data.brands = res.data
    console.log('brand data : ', res.data)
  } catch (error) {
    console.log('브랜드 주세요', error)
  }
}

const getCarList = (brand) => {
  // const requestOptions = {
  //   credentials: 'include',
  // }
  let url = `${URL}/car/search`
  if(brand!==undefined){
    url += `?brand=${brand}`
  }
  axios.get(url)
  .then(res => {
    console.log(res.data)
    data.carList = res.data
  })
}

onMounted(() => {
  getCarList(), getBrand()
})
</script>

<style scoped>
.brand-box{
  width: 60vh;
  margin: 0 auto;
  margin-top: 88px;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr;
}
.brand{
  padding: 10px;
  text-align: center;
}
.brand a{
  text-decoration: none;
  color: black;
}
.brand-img{
  aspect-ratio: 4 / 3;
  width: 100%;
}
.cars-body {
  text-align: center;
  border: none;
  width: 70vw;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  gap: 20px;
}

.carbox{
  /* border: 1px solid black; */
}
.carbox a{
  text-decoration: none;
  color: black;

}
.carimg {
  aspect-ratio: 4 / 3;
  width: 100%;
}
</style>
