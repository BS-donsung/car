<template>
  <div class="column-warp">
    <div class="column-contain">
      <div
        v-for="post in posts"
        :key="post"
        class="column-box"> 
        <div class="column-img">
          <img
            :src="`/img/column${post.thumbnail}`"
            alt="" />
          <div class="text-contain">
            <div class="text-top">
              {{ post.title }}
            </div>
            <div class="text-bottom">
              <div
                class="sub-text"
                v-html="post.content">
              </div>
            </div>
          </div>
        </div>
      </div>
      <AppPagination
        :current-page="params._page"
        :page-count="pageCount"
        @page="page => (params._page = page)" />
    </div>
  </div>
</template>

<script setup>
import { URL } from '@/components/global'
import axios from 'axios'
import AppPagination from '@/components/AppPagination.vue'
import { ref, computed, onMounted } from 'vue'

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
const getColums = async (params) => {
  const res = await axios.get(`${URL}/board?type=2`, {params:{params}})
  forms.value = res.data
  totalCount.value = res.data.length
}
onMounted(()=>{
  getColums()
})
</script>

<style scoped>
.column-warp {
  width: 100%;
  height: 100vh;
  margin: 0 auto;
  background: #F0F1F8;
  padding-top: 30px;
}

.column-contain {
  width: 70%;
  height: 100%;
  margin: 0 auto;
  overflow: hidden;
  background: #fff;
  border-radius: 6px;
  padding: 20px 60px 40px 40px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
}

.column-box {
  width: 90%;
  height: 24%;
  margin: 0 auto;
  margin-top: 30px;
  background: #fff;
  border-radius: 6px;
  /* padding: 20px 60px 40px 40px; */
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
}

.column-img {
  display: flex;
  width: 100%;
  height: 100%;
}

.column-img img {
  width: 200px;
  height: 100%;
  /* background: black; */
  border-radius: 6px;
  margin-right: 5px;
}

.text-contain {
  width: 500px;
  height: 100%;
  flex-direction: column;
  display: flex;
  background: orange;
}

.text-top {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  font-weight: 700;
  background: #fff;
  /* cursor: pointer; */
}

.text-bottom {
  flex: 1;
  background: #fff;
  display: flex;
  align-items: center;
}

.sub-text {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>
