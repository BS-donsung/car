<template>
  <PNav />
  <div class="contain">
    <div class="myBody">
      <table class="box">
        <thead>
          <th class="th_num">
            번호
          </th>
          <th class="th_title">
            내용
          </th>
          <th class="th_date">
            등록일자
          </th>
        </thead>

        <tbody>
          <tr
            v-for="reply in data.replies"
            :key="reply"
            style="cursor: pointer;"
            @click="toDetail(reply.bno)">
            <td
              class="notice_date"
              style="text-align: center;">
              {{ reply.rno }}
            </td>
            <td
              class="td_title">
              {{ reply.text }}
            </td>
            <td class="text_center">
              {{ reply.createdDate }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import PNav from '@/components/PrivateNav.vue'
import { URL,credentials } from '@/components/global'
import { reactive, onMounted } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const router = useRouter()
// 대충 board comp 가져다가 붙여버리기
const data = reactive({
  replies: [

  ]
})
const toDetail = bno => {
  router.push({
    path: '/community/detail',
    query: {
      'bno': bno
    }
  })
}
const getReplies = () => {
  axios.get(`${URL}/reply/myreplies`,credentials)
  .then((res) => data.replies = res.data)
  console.log(data.replies)
}
onMounted(() => {
  getReplies()
})
</script>

<style scoped>
.contain {
  background: #f0f1f8;
  height: 100vh;
  padding-top: 50px;
}
.myBody {
  margin: 0 auto;
  display: grid;
  align-content: center;
  width: 70%;
  background: #fff;
  border-radius: 6px;
  padding: 20px 60px 40px 40px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
}</style>
