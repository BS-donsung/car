<template>
  <div class="opt-container">
    <div class="comp-text">
      <p class="p-text">
        * <span class="s-text">제조사</span>를 선택하세요
      </p>
      <div class="domestic">
        전체
      </div>
      <div class="brand-text">
        <button
          v-for="brand in getData.brands"
          :key="brand"
          :v-model="c_text"
          :value="brand.brand"
          class="item"
          @click="changeText">
          {{ brand.brand }}
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, defineEmits } from 'vue'
import { URL } from '@/components/global'
import axios from 'axios'

const c_text = ref('')

const isRend = ref(false)
const getData = reactive({
  brands: [],
  segments: [],
  cars: [],
})

const getComp = async () => {
  try {
    const res = await axios.get(URL + '/brand/all')
    isRend.value = !isRend.value
    getData.brands = res.data
    console.log('brand data : ', res.data)
  } catch (error) {
    console.log('브랜드 주세요', error)
  }
}

// 클릭한 값으로 변경하기 위해 상위 컴포넌트와 연동
const emit = defineEmits('changeEvent_c')
const changeText = event => {
  console.log(event.target.value)
  if (event.target.classList.contains('item')) emit('changeEvent', event.target.value)
}

onMounted(() => {
  getComp()
})

</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&family=Open+Sans:ital,wght@0,300;0,600;1,300;1,600&family=Poor+Story&family=Poppins:wght@300;400;500;600;700&display=swap');
@import '@/assets/Opt.css';

.comp-text {
  width: 100%;
  margin: 10px;
}

.p-text {
  display: flex;
  font-size: 13px;
  font-family: 'Poppins', sans-serif;
}

.s-text {
  font-size: 13px;
  font-family: 'Poppins', sans-serif;
  color: red;
}

.domestic {
  margin-top: 15px;
  display: flex;
  font-weight: bold;
  margin-bottom: 15px;
}

.brand-text {
  display: flex;
  flex-wrap: wrap;
}

.item {
  width: 20%;
  margin-bottom: 10px;
  border: none;
  text-decoration: none;
  background: none;
  cursor: pointer;
}
</style>
