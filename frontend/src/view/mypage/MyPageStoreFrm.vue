<template>
  <PNav />
  <div class="contain">
    <div class="comp-box">
      <PostSpk :spk="route.query.spk" />
    </div>
    <div class="postBtn">
      <button @click="poststore">
        글쓰기
      </button>
    </div>
    <div class="postBtn">
      <button @click="deletestore">
        삭제하기
      </button>
    </div>
  </div>
</template>

<script setup>
import PNav from '@/components/PrivateNav.vue'
import { useRoute } from 'vue-router'
import PostSpk from '@/components/mypage/PostSpk.vue'
import { useCompStore } from '@/store'
import axios from 'axios'
import { URL,credentials } from '@/components/global'

const route = useRoute()
const store = useCompStore()

const poststore = async () => {
  let notice_data = {
    title: `${store.getUser().username}님의 차량`,
    content: '',
    type: 1,
    spk: route.query.spk
  }
  try {
    await axios.post(`${URL}/board/post`,notice_data,credentials)
    window.location.href = 'http://carmunis.com:8080/community/store'
  } catch (error) {
    console.log(error)
  }
}
const deletestore = async () => {
  try {
    await axios.delete(`${URL}/store/delete?spk=${route.query.spk}`,credentials)
    window.location.href = 'http://carmunis.com:8080/mypage/stores'
  } catch (error) {
    console.log(error)
  }
}
</script>

<style scoped>
.contain{
  background: #F0F1F8;
  height: 120vh;
  width: 100vw;
  padding-top: 50px;
}
.comp-box {
    width: 50vw;
    margin: 0 auto;

}
.cidcontain {
    margin-top: 0;
}
.postBtn{
  width: 400px;
  margin: 0 auto;
}
button {
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
</style>
