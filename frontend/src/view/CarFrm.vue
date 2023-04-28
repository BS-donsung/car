<template>
  <div class="contain">
    <div class="car-body">
      <CompCID :cid="route.query.cid" />
      <div id="review-contain">
        <div class="reviews-write-box">
          <textarea
            v-model="content"
            maxlength="100"
            @keydown.enter.exact.prevent="postReviews"
            @keydown.enter.shift.exact.prevent="content += '\n'"
            @input="inputHandler"></textarea>
          <button @click="postReviews">
            글쓰기
          </button>
        </div>
        <div class="reviews-box">
          <div
            v-for="review in reviews"
            :key="review"
            class="review">
            <div>
              <span class="nickname">
                {{ review.nickname }}
              </span>
              |
              <font-awesome-icon
                :icon="['far', 'thumbs-up']"
                class="icon" />
              <font-awesome-icon
                :icon="['far', 'thumbs-down']"
                class="icon" />
            <!-- TODO : 여기에서 추천 개수, 추천별 정렬, 추천기능 추가할것 -->
            <!-- TODO : infinite scroll을 통해 10개씩 긁어올것 -->
            </div>
            <p>{{ review.content }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'
import CompCID from '@/components/ComparCID.vue'
import { onMounted, ref } from 'vue'
import { URL } from '@/components/global'
import axios from 'axios'

const route = useRoute()

const reviews = ref([])

const content = ref('')

const getReviews = () => {
  const credentials = {
    withCredentials: 'include',
  }

  axios
    .get(`${URL}/review/all?cid=${route.query.cid}`, credentials)
    .then((res) => res.data)
    .then((body) => (reviews.value = body))
    .catch((err) => console.log('데이터가 없다', err))
}
const postReviews = () => {
  const body = {
    content: content.value,
    cid: route.query.cid,
  }
  const credentials = {
    withCredentials: 'include',
  }

  axios
    .post(`${URL}/review/save`, body, credentials)
    .catch((err) => console.log('데이터를 못넣어따', err))
  window.location.reload(true)
}

const inputHandler = (e) => {
  const target = e.currentTarget
  const max = e.currentTarget.getAttribute('maxlength')
  if (target.value.length > max) {
    target.value = target.value.slice(0, max)
  }
  content.value = target.value
}
onMounted(() => {
  getReviews()
})
</script>

<style scoped>
.contain{
  background: #F0F1F8;
}
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
#review-contain{
  width: 70vh;
  background: #fff;
  border-radius: 6px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
}

.reviews-box {
  padding: 15px;
  margin-top: 3em;
  display: grid;
  font-size: 12px;
}
.review {
  border-bottom: 1px solid #ebeef1;
  text-align: left;
}
.review div {
  font-weight: lighter;
}
.review p {
  margin-top: 0.5em;
}
.nickname {
  font-weight: bold;
}
.reviews-write-box {
  font-size: 15px;
  padding: 15px;
  background-color: #fafafa;
  border-radius: 4px;
  text-align: left;
}
.reviews-write-box textarea {
  font-size: 15px;
  width: 460px;
  height: 80px;
  resize: none;
  outline: none;
  overflow: hidden;
  background-color: #fafafa;
  border: none;
  -webkit-box-shadow: none;
}

.reviews-write-box button {
  margin-left: 10px;
  width: 130px;
  height: 80px;
  position: absolute;
  color: white;
  background-color: #B3CDFF;
  border: none;
}
</style>
