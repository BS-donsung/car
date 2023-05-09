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
            장바구니
          </h3>
          <table class="box">
            <thead>
              <th class="th_num">
                번호
              </th>
              <th class="th_title">
                차량
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
                v-for="form in posts"
                :key="form.bno"
                style="cursor: pointer;"
                @click="toDetail(form.bno)">
                <p
                  class="notice_date"
                  style="text-align: center;">
                  {{ form.bno }}
                </p>
                <td
                  class="td_title">
                  {{ form.title }}
                </td>
                <td class="text_center">
                  {{ form.nickname }}
                </td>
                <td class="text_center">
                  {{ form.createdDate }}
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
import { onMounted, ref, computed } from 'vue'
import { URL } from '@/components/global'
import axios from 'axios'
import { useRouter } from 'vue-router'
import AppPagination from '@/components/AppPagination.vue'

const totalCount = ref(0)
const forms = ref([])
const params = ref({
  _page: 1,
  _order: 'desc',
  _limit: 10,
})

const pageCount = computed(() => {
  return Math.ceil(totalCount.value / params.value._limit)
})
const posts = computed(() => {
  return forms.value.filter((post, index) => {
    const { _limit, _page } = params.value
    const p = _page - 1
    const length = p * _limit
    return (index >= length && index < length + _limit)
  })
})

const getStores = async (params) => {
  const res = await axios.get(`${URL}/board?type=1`,{params:{params}})
  forms.value = res.data
  totalCount.value = res.data.length
}

const router = useRouter()
const toDetail = bno => {
  router.push({
    path: '/community/store/detail',
    query: {
      'bno': bno
    }
  })
}

onMounted(()=>{
  getStores()
})
</script>

<style scoped>
.navfrm{
  color: black;
}
</style>
