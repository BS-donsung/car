<template>
  <div id="wrap">
    <div class="container notice_container">
      <nav>
        <router-link
          to="/community?"
          class="navfrm">
          차량정보
        </router-link>
        <router-link
          to="/community/store"
          class="navfrm">
          차량정보
        </router-link>
      </nav>
      <div class="wright_wrap">
        <div class="ListContainer">
          <h3 class="box_title">
            커뮤니티
          </h3>
          <button
            type="button"
            class="writing_btn btn_po"
            @click="toWrite()">
            글쓰기
          </button>
          <table class="box">
            <thead>
              <th class="th_num">
                번호
              </th>
              <th class="th_title">
                제목
              </th>
              <th class="th_name">
                작성자
              </th>
              <th class="th_date">
                등록일자
              </th>
            </thead>

            <tbody>
              <tr
                v-for="forms in posts"
                :key="forms.bno"
                style="cursor: pointer;"
                @click="toDetail(forms.bno)">
                <p
                  class="notice_date"
                  style="text-align: center;">
                  {{ forms.bno }}
                </p>
                <td
                  class="td_title">
                  {{ forms.title }}
                </td>
                <td class="text_center">
                  {{ forms.nickname }}
                </td>
                <td class="text_center">
                  {{ forms.createdDate }}
                </td>
              </tr>
            </tbody>
          </table>
          <AppPagination
            :current-page="params._page"
            :page-count="pageCount"
            @page="page => (params._page = page)" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import axios from 'axios'
import { ref, computed, onMounted } from 'vue'
import { URL } from '@/components/global'
import AppPagination from '@/components/AppPagination.vue'


const form = ref([])
const params = ref({
  _page: 1,
  _order: 'desc',
  _limit: 10,
})
const totalCount = ref(0)
const pageCount = computed(() => {
  return Math.ceil(totalCount.value / params.value._limit)
})
const posts = computed(() => {
  return form.value.filter((post, index) => {
    const { _limit, _page } = params.value
    const p = _page - 1
    const length = p * _limit
    return (index >= length && index < length + _limit)
  })
})

const getPosts = async (params) => {
  const res = await axios.get(`${URL}/board`, {
    params: {
      params
    }
  })
  console.log('데이터',res.data)
  const allPosts = res.data
  form.value = allPosts
  totalCount.value = allPosts.length
}

onMounted(()=>{
  getPosts()
})

const router = useRouter()
const toWrite = () => {
  router.push({
    path: '/community/create',
  })
}

const toDetail = bno => {
  router.push({
    path: '/community/detail',
    query: {
      'bno': bno
    }
  })
}
</script>

<style scoped>
@import "@/assets/notice.css";
.comm-warp {
  width: 100%;
  height: 130vh;
  background: #F0F1F8;
}
#wrap {
  display: flex;
  height: 100vh;
  flex-direction: column;
  background: #F0F1F8;
  /* height: 100%; */
  /* flex-direction: column; */
}

.writing_btn {
  cursor: pointer;
  display: flex;
  float: right;
  margin-bottom: 20px;
}
.navfrm {
  color: black;
}
</style>
