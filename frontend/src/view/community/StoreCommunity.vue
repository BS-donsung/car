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
                v-for="form in forms"
                :key="form.spk"
                style="cursor: pointer;"
                @click="toDetail(form.spk)">
                <p
                  class="notice_date"
                  style="text-align: center;">
                  {{ form.spk }}
                </p>
                <td
                  class="td_title">
                  {{ form.carDto.name }}
                </td>
                <td class="text_center">
                  {{ form.user }}
                </td>
                <td class="text_center">
                  {{ form.createdDate }}
                </td>
              </tr>
            </tbody>
          </table>
        <!-- <AppPagination
          :current-page="params._page"
          :page-count="pageCount"
          @page="page => (params._page = page)" /> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { URL } from '@/components/global'
import axios from 'axios'
import { useRouter } from 'vue-router'

const totalCount = ref(0)
const forms = ref([])

// import AppPagination from '@/components/AppPagination.vue'
const getStores = async (params) => {
  const res = await axios.get(`${URL}/store/allstores`,{params:{params}})
  forms.value = res.data
  console.log('allPosts:',forms.value)
  totalCount.value = res.data.length
}

const router = useRouter()
const toDetail = spk => {
  router.push({
    path: '/community/store/detail',
    query: {
      'spk': spk
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
