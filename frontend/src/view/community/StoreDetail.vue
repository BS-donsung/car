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
                {{ form.user }}의 {{ form?.carDto?.name }}
              </p>
              <div class="notice_sub_title">
                <p>작성자 : {{ form?.user }} </p>
              </div>
              <div class="notice_sub_title">
                <p>등록일 : {{ form.createdDate }}</p>
              </div>
            </div>
            <div>
              <PostSpk :spk="route.query.spk" />
            </div>
          </div>
          <div class="detail_button_wrap">
            <button
              type="button"
              class="writing_btn back_btn"
              @click="back">
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
              @click="deletepost">
              삭제
            </button>
          </div>

          <!-- 댓글공간 -->
          <Reply
            :bno="bno"
            :reply-dtos="form.replyDtos"
            :replycount="form.replyCount" />
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { URL } from '@/components/global'
import axios from 'axios'
import { onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'
import PostSpk from '@/components/PostSpk.vue'
import Reply from '@/components/Reply.vue'

const route = useRoute()
const spk = route.query.spk
const form = ref([])

const getDetail = async () => {
  console.log('spk : ',spk)
  try {
    const res = await axios.get(`${URL}/store/view/${spk}`)
    console.log('>> ',res.data)
    form.value = res.data
    
  } catch (error) {
    console.log(error)    
  }
}

onMounted(()=>{
  getDetail()
})
</script>
<style scoped>
</style>
