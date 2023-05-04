<template>
  <div class="cidcontain">
    <div class="image-box">
      <img
        class="imginner"
        :src="'/img' + InfoData.specifications.imgurl" />
    </div>

    <div class="spec">
      <hr />
      <div class="spectitle">
        요약
      </div>
      <div class="specinner">
        <span>가격</span>
        <span class="costcolor">
          {{
            isNaN(Number(InfoData.specifications.cost))
              ? ""
              : Number(InfoData.specifications.cost).toLocaleString()
          }}
        </span>
        <span>차종</span>
        <span>{{ InfoData.specifications.name }}</span>
        <span>출시일</span>
        <span>{{ InfoData.specifications.date }}</span>
        <span>연료</span>
        <span>{{ InfoData.specifications.engine }}</span>
        <span>연비</span>
        <span>{{ InfoData.specifications.fuel_efficiency }}</span>
      </div>
    </div>

    <div class="spec">
      <hr />
      <div class="spectitle">
        제원
      </div>
      <div class="specinner">
        <span>전장 / 전폭</span>
        <span>{{ InfoData.specifications.length }} /
          {{ InfoData.specifications.width }}</span>
        <span>전고 / 축거</span>
        <span>{{ InfoData.specifications.height }} /
          {{ InfoData.specifications.wheelBase }}</span>
        <span>윤거전 / 윤거후</span>
        <span>{{ InfoData.specifications.frontThread }} /
          {{ InfoData.specifications.rearThread }}</span>
        <span>배기량</span>
        <span>{{ InfoData.specifications.capacity }}</span>
        <span>전륜 서스펜스</span>
        <span>{{ InfoData.specifications.frontSuspension }}</span>
        <span>후륜 서스펜스</span>
        <span>{{ InfoData.specifications.rearSuspension }}</span>
      </div>
    </div>

    <div class="opt">
      <hr />
      <div class="opttitle">
        옵션
      </div>
      <div class="optinner">
        <div
          v-for="caropt in InfoData.storeoptions"
          :key="caropt">
          <p>
            <label :class="'opt-' + caropt.chk">
              {{ caropt.oname }}
            </label>
          </p>
        </div>
      </div>
    </div>
  </div>
  <div class="totalcost">
    <div class="totalbox">
      {{
        isNaN(Number(InfoData.total))
          ? ""
          : Number(InfoData.total).toLocaleString()
      }}
    </div>
  </div>
</template>

<script setup>
import axios from 'axios'
// import router from '@/router'
import { URL, } from '@/components/global'
import { onMounted, reactive } from 'vue'
import { defineProps } from 'vue'


const props = defineProps({
  spk: Number,
})
const InfoData = reactive({
  valeu: 0,
  storeoptions: {},
  specifications: {},
})

const getBySpk = async () => {
  try {
    const res = await axios.get(`${URL}/store/mystore?spk=${props.spk}`)
    console.log(res.data)
    InfoData.specifications = res.data.carDto
    InfoData.storeoptions = res.data.options
    InfoData.total = InfoData.specifications.cost
    for (let i = 0; i < InfoData.storeoptions.length; i++) {
      if (InfoData.storeoptions[i].chk) {
        InfoData.total += InfoData.specifications.options[i].cost
      }
    }
  } catch (error) {
    console.log('제원 주세요', error)
  }
}



onMounted(() => {
  getBySpk()
})
</script>

<style scoped>
.cidcontain {
  background: #fff;
  border-radius: 6px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
  display: grid;
}
.spectitle {
  font-weight: bold;
}
.specinner {
  grid-template-columns: 0.4fr 0.6fr;
  display: grid;
  margin: 0 5px;
}
.opttitle {
  width: 100%;
  text-align: left;
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 30px;
  margin-left: 10px;
  font-family: "Noto Sans KR", sans-serif;
}
.image-box {
  text-align: center;
  margin: auto;
}

.imginner {
  min-width: 200px;
  min-height: 200px;
}
hr {
  border: 2px solid black;
}

p {
  min-width: 100px;
  min-height: 30px;
  text-align: center;
  margin-top: 10px;
}

.costcolor {
  color: blue;
  font-weight: bold;
}

.totalbox {
  width: 100%;
  height: 60px;
  border: 1px solid black;
  border-radius: 6px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: blue;
  font-weight: 600;
  font-size: 22px;
  margin-top: 20px;
  background: white;
}

.addcarBtn {
  width: 200px;
  height: 30px;
  border: 1px solid black;
  box-shadow: 1px 3px 3px 1px rgb(0, 0, 0, 0.6);
  margin-top: 10px;
  border-radius: 6px;
  color: black;
}
.postBtn {
  display: flex;
  justify-content: center;
}
.postBtn button {
  width: 100%;
  height: 50px;
  color: black;
  margin-top: 10px;
  border: 1px solid black;
  border-radius: 6px;
  font-size: 22px;
  font-weight: 600;
  background: white;
}

button:hover {
  background: black;
  color: white;
}
.opt-true {
  color: black;
}
.opt-false {
  color: gray;
  text-decoration: line-through;
}
</style>
