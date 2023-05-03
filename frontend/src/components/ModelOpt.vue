<template>
  <div class="opt-container">
    <div class="comp-text">
      <p class="p-text">
        * <span class="s-text">모델</span>를 선택하세요
      </p>
      <div class="domestic">
        전체
      </div>
      <div class="brand-text">
        <button
          v-for="car in getData.cars"
          :key="car"
          :v-model="m_text"
          :value="car.cid"
          class="item"
          @click="changeText(car)">
          {{ car.name }}
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, defineEmits, defineProps } from 'vue'
import { URL } from '@/components/global'
import axios from 'axios'

const props = defineProps({
  c_text: String,
  t_text: String,
})

const m_text = ref('')

const isRend = ref(false)
const getData = reactive({
  brands: [],
  segments: [],
  cars: [],
})

const getCar = async () => {
  console.log('>>', props)
  let path = '/car/search?'
  if (props.c_text != '제조사') {
    path = path + `brand=${props.c_text}&`
  }
  if (props.t_text != '차종') {
    path = path + `segment=${props.t_text}&`
  }
  console.log(props.c_text + '/' + props.t_text)
  console.log('path',path)
  try {
    const res = await axios.get(URL + path)
    isRend.value = !isRend.value
    getData.cars = res.data
  } catch (error) {
    console.log('차급 주세요', error)
  }
}

// 클릭한 값으로 변경하기 위해 상위 컴포넌트와 연동
const emit = defineEmits('changeEvent_m')
// const changeText = event => {
//   console.log('>>', event.target.dataset.name)
//   console.log('>>', event.target.dataset.cid)
//   if (event.target.classList.contains('item')) emit('changeEvent', event.dataset.name)
// }

const changeText = car => {
  console.log('name >>', car.name)
  console.log('cid >>', car.cid)
  emit('changeEvent', car.name, car.cid)
}


onMounted(() => {
  getCar()
})

// const usecid = reactive({
//   id: []
// })
// function getCid(car) {
//   const get = car.cid
//   usecid.id = get
//   console.log('get', get )
//   console.log('id', usecid.id)
// }
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&family=Open+Sans:ital,wght@0,300;0,600;1,300;1,600&family=Poor+Story&family=Poppins:wght@300;400;500;600;700&display=swap');
@import '@/assets/Opt.css';
</style>
