<template>
  <div class="comp-contain">
    <div class="text-comp">
      <p class="p-text">
        비교하기
      </p>
      <div class="select-model">
        <button
          class="seletedBtn1"
          @click="clickBtn(1)">
          {{ c_text }}
        </button>
        <button
          class="seletedBtn2"
          @click="clickBtn(2)">
          {{ t_text }}
        </button>
        <button
          class="seletedBtn3"
          @click="clickBtn(3)">
          {{ m_text }}
        </button>
        <button
          class="addBtn"
          @click="postBtn">
          추가하기
        </button>
        <div class="opt-cont">
          <optC
            v-if="isOpt == 1"
            @change-event="changeEvent_c" />
          <optT
            v-if="isOpt == 2"
            @change-event="changeEvent_t" />
          <optM
            v-if="isOpt == 3"
            :c_text="c_text"
            :t_text="t_text"
            @change-event="changeEvent_m" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useCompStore } from '@/store/index'
import router from '@/router'
import optC from '@/components/CompanyOpt.vue'
import optT from '@/components/TypeOpt.vue'
import optM from '@/components/ModelOpt.vue'


const isOpt = ref(0)

const clickBtn = (num) => {
  if (isOpt.value != num) {
    isOpt.value = num
  } else {
    isOpt.value = 0
  }
}

const c_text = ref('제조사')
const t_text = ref('차종')
const m_text = ref('모델')

const changeEvent_c = (changeText) => {
  c_text.value = changeText
  isOpt.value = 0
}

const changeEvent_t = (changeText) => {
  t_text.value = changeText
  isOpt.value = 0
}

const changeEvent_m = (changeText) => {
  m_text.value = changeText
  isOpt.value = 0
}

const usecid = reactive({
  id: []
})
const store = useCompStore()

// function getCid(e) {
//   const get = e.target.value
//   usecid.id = get
//   console.log('cid', get)
// }

const postBtn = () => {
  console.log('cid >> ', usecid)
  store.getCarInfo(usecid.id)

  if (store.RIGHT_CAR != undefined) {
    console.log('CarInfomationL >> ', store.CarInfomation[1])
  }
  console.log('CarInfomationR >> ', store.CarInfomation[0])

  router.push({ path: '/comparison' })
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&family=Open+Sans:ital,wght@0,300;0,600;1,300;1,600&family=Poor+Story&family=Poppins:wght@300;400;500;600;700&display=swap');

.comp-contain {
  width: 100%;
  height: 300px;
}
.text-comp {
  width: 1000px;
  height: 300px;
  text-align: center;
  border: 2px solid black;
  margin: 0 auto;
}

.text-comp .p-text {
  display: flex;
  margin-left: 30px;
  font-size: 22px;
  font-weight: bold;
  padding: 10px;
}

.seletedBtn1,
.seletedBtn2,
.seletedBtn3,
.addBtn {
  width: 180px;
  height: 40px;
  border: none;
  border-right: 1px solid red;
  background: #fff;
  cursor: pointer;
  font-size: 16px;
  font-weight: 600;
  margin-bottom: 5px;
  color: #CACACA;
  text-decoration: none;
}

.seletedBtn1:hover,
.seletedBtn2:hover,
.seletedBtn3:hover {
  color: black;
}

.addBtn {
  background: #FF441C;
  color: white;
}
</style>
