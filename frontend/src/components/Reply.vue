<template>
  <div class="wrap">
    <div class="countreply">
      <button
        class="icons"
        @click="good">
        <font-awesome-icon
          :icon="['far', 'heart']"
          :class="{ hearticons: isRed }" />
      </button> 좋아요
      <div class="icons">
        <font-awesome-icon
          :icon="['far', 'comment-dots']"
          class="replyicons" />
      </div>
      댓글 {{ replycount }}
    </div>
    <hr style="color: #bebebe; width: 100%;" />
    <br />
    <div class="reply-title">
      <h4>
        댓글
      </h4>
    </div>

    <div
      ref="contArea"
      class="reply-container">
      <div class="nick-text">
        {{ 'username1' }}
      </div>
      <div class="reply-text">
        <textarea
          ref="textArea"
          v-model="reply_data.text"
          placeholder="댓글을 남겨보세요"
          maxlength="100"
          @keydown.enter.exact.prevent="postReviews"
          @keydown.enter.shift.exact.prevent="reply_data.text += '\n'"
          @input="inputHandler"></textarea>
      </div>
      <div class="addreplyBtn">
        <button @click="addreply">
          댓글달기
        </button>
      </div>
    </div>
    <br />
    <hr style="color: #bebebe; width: 100%;" />

    <div class="reply-content">
      <div
        v-for="reply in replies"
        ref="innerTextArea"
        :key="reply"
        class="innerText">
        <div
          v-if="reply.ismodify"
          class="inneruser">
          {{ reply?.user?.nickname }}({{ reply?.user?.username }})
        </div>
        <div
          v-if="reply.ismodify"
          class="innercontent">
          {{ reply?.text }}
        </div>
        <div class="innerCreate">
          <div
            v-if="reply.ismodify"
            class="createDate">
            {{ reply?.formattedCreatedDate }}
          </div>
          <div class="reBtn">
            <button
              v-if="reply.ismodify"
              class="rewrite"
              @click="writeReply">
              답글쓰기
            </button>
          </div>
        </div>
        <div
          v-if="reply?.user.username === username"
          class="inneredit">
          <button
            v-if="reply.ismodify"
            class="replyedit"
            @click="modifyreply(reply)">
            수정
          </button>
          <button
            v-if="reply.ismodify"
            class="replyedit"
            @click="removereply(reply.rno)">
            삭제
          </button>
        </div>


        <!-- 수정 event -->
        <div class="remodifyFrm">
          <div
            v-if="!reply.ismodify"
            class="inneruser">
            {{ reply?.user?.nickname }}({{ reply?.user?.username }})
          </div>

          <div
            v-if="!reply.ismodify"
            class="modifycontent">
            <textarea
              ref="textArea"
              v-model="reply.text"
              maxlength="100"
              @keydown.enter.exact.prevent="postReviews"
              @keydown.enter.shift.exact.prevent="reply.text += '\n'"
              @input="inputHandler"></textarea>
          </div>

          <div class="innerCreate">
            <div
              v-if="!reply.ismodify"
              class="createDate">
              {{ reply?.formattedCreatedDate }}
            </div>
          </div>

          <div class="inneredit">
            <button
              v-if="!reply.ismodify"
              class="replyedit"
              @click="remodifyreply(reply)">
              수정완료
            </button>
            <button
              v-if="!reply.ismodify"
              class="replyedit"
              @click="removereply(reply.rno)">
              삭제
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- {{ props.replies }} -->
  </div>
</template>

<script setup>
import { onMounted, ref, reactive } from 'vue'
import { defineProps } from 'vue'
import { URL } from '@/components/global'
import router from '@/router'
import axios from 'axios'


const textArea = ref()
const contArea = ref()
const innerTextArea = ref()
const reply_form = ref()
const username = ref('username1')

const props = defineProps({
  bno: Number,
  replies: Array,
  replycount: Number
})

// 댓글 저장
const reply_data = reactive({
  username: 'username1',
  text: '',
  bno: props.bno
})

const addreply = () => {
  let data = {
    username: reply_data.username,
    text: reply_data.text,
    bno: props.bno
  }
  console.log(data)

  axios.post(URL + '/reply/post', data)
    .then((res) => {
      console.log(res.data)
      reply_form.value = res.data
      console.log('확인', reply_form.value)
    })
    .catch('댓글저장 실패')
  router.push({
    name: 'detailnotice'
  })
  window.location.reload(true)
}
console.log('댓글저장성공')

// 댓글삭제
const removereply = async (rno) => {
  if (confirm('삭제하시겠습니까?')) {
    await axios.delete(URL + `/reply/delete/${rno}`)
      .then(() => {
        console.log('해당게시글이 삭제되었습니다.')
        window.location.reload(true)
      })
      .catch(() => {
        console.log('해당 댓글을 삭제할 수 없습니다.')
      })
  }
}

function getbno() {
  return props.bno
}


onMounted(() => {
  getbno()
})


const inputHandler = (e) => {
  const target = e.currentTarget
  const max = e.currentTarget.getAttribute('maxlength')
  if (target.value.length > max) {
    target.value = target.value.slice(0, max)
  }
  target.style.height = '1px'
  target.style.height = (12 + target.scrollHeight) + 'px'
  contArea.value.style.height = '1px'
  contArea.value.style.height = (12 + contArea.value.scrollHeight) + 'px'
}

const isRed = ref(false)
function good() {
  isRed.value = !isRed.value
}

// 수정전
const modifyreply = reply => {
  reply.ismodify = !reply.ismodify
}
// 수정후
const remodifyreply = reply => {
  reply.ismodify = !reply.ismodify
}
</script>

<style scoped>
@import '@/assets/reply.css';
</style>
