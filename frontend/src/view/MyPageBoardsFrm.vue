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
            제목
          </th>
          <th class="th_date">
            등록일자
          </th>
        </thead>

        <tbody>
          <tr
            v-for="board in data.boards"
            :key="board"
            style="cursor: pointer;"
            @click="toDetail(board.bno)">
            <td
              class="notice_date"
              style="text-align: center;">
              {{ board.bno }}
            </td>
            <td
              class="td_title">
              {{ board.title }}
            </td>
            <td class="text_center">
              {{ board.createdDate }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import PNav from '@/components/PrivateNav.vue'
import axios from 'axios'
import { reactive, onMounted } from 'vue'
import { URL } from '@/components/global'
import { useCompStore } from '@/store'
import { useRouter } from 'vue-router'

const store = useCompStore()
const router = useRouter()
// 대충 board comp 가져다가 붙여버리기
const data = reactive({
  boards: [
    
  ],
})
const toDetail = bno => {
  router.push({
    path: '/community/detail',
    query: {
      'bno': bno
    }
  })
}
const getBoard = () => {
  axios.get(`${URL}/board/mypage/${store.getUser().username}`)
  .then((res) => data.boards = res.data)
}
onMounted(() => {
  getBoard()
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
}
</style>
