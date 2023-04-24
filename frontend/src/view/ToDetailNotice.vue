<template>
  <div id="wrap">
    <div class="container notice_container">
      <!-- <Sidebar /> -->
      <div class="notice_write">
        <div class="wright_wrap">
          <h3 class="box_title">
            커뮤니티
          </h3>
          <div class="notice_content_wrap">
            <div class="notice_top">
              <p class="notice_title">
                {{ form.title }}
              </p>
              <div class="notice_sub_title">
                <p>작성자 : {{ form?.writerDto?.username }} </p>
              </div>
              <div class="notice_sub_title">
                <p>등록일 : {{ form.createdDate }}</p>
              </div>
            </div>
            <div
              class="content_box"
              v-html="form.content">
            </div>
          </div>
          <div class="detail_button_wrap">
            <button
              type="button"
              class="writing_btn back_btn"
              @click="back()">
              목록
            </button>
            <button
              type="button"
              class="writing_btn edit_btn"
              @click="editpage(bno)">
              수정
            </button>
            <button
              type="button"
              class="writing_btn del_btn"
              @click="deletepost()">
              삭제
            </button>
          </div>

          <!-- 댓글공간 -->
          <Reply
            :bno="bno"
            :replies="form.replies"
            :replycount="form.replyCount" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { onMounted, ref } from 'vue'
import axios from 'axios'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
import { URL } from '@/components/global'
import Reply from '@/components/Reply.vue'


const form = ref([])
const route = useRoute()
const router = useRouter()
const bno = route.params.bno

// 페이지 상세보기
const fetchDetail = async () => {
  try {
    await axios.get(URL + `/board/view/${bno}`)
      .then((res) => {
        console.log('데', res.data)
        form.value = res.data
        console.log('상세보기 페이지 성공')
      })
  } catch (error) {
    alert('정보를 불러올수없습니다.')
    console.log(error)
  }
}

const back = () => {
  router.push({
    path: '/community',
  })
}

const editpage = bno => {
  router.push({
    name: 'editnotice',
    params: {
      bno
    }
  })
}

// 게시글 삭제
const deletepost = async () => {
  try {
    if (confirm('삭제하시겠습니까?')) {
      await axios.delete(URL + `/board/delete/${bno}`)
        .then((res) => {
          console.log(res.data)
          form.value = res.data
        })
      router.push({ name: 'community' })
    }
  } catch (error) {
    alert('삭제실패')
    console.log(error)
  }
}


onMounted(() => {
  fetchDetail()
})
</script>

<style scoped>
@import "@/assets/notice.css";

#wrap {
  display: flex;
  flex-direction: column;
  background: #F0F1F8;
}
</style>
