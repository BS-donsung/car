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
          @input="checkRows"></textarea>
      </div>
      <div class="addBtn">
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
        :key="reply"
        class="innerText">
        <div
          v-if="ismodify"
          class="modifyif">
          <div class="inneruser">
            {{ reply?.user?.nickname }}({{ reply?.user?.username }})
          </div>
          <div
            v-if="ismodify"
            class="innercontent">
            {{ reply?.text }}
          </div>
          <div
            v-if="!ismodify"
            class="modifycontent">
            <textarea
              ref="textArea"
              v-model="reply.text"
              @input="checkRows"></textarea>
          </div>
          <div class="innerCreate">
            <div class="createDate">
              {{ reply?.formattedCreatedDate }}
            </div>
            <div class="reBtn">
              <button
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
              class="replyedit"
              @click="modifyreply(da)">
              수정
            </button>
            <button
              class="replyedit"
              @click="removereply(reply.rno)">
              삭제
            </button>
          </div>
        </div>
      </div>
    </div>

    {{ props.replies }}
  </div>
</template>

<script setup>
import { onMounted, ref, reactive } from 'vue'
import { defineProps } from 'vue'
import { URL } from '@/components/global'
import router from '@/router'
import axios from 'axios'

const text = ref('')
const textArea = ref()
const contArea = ref()
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


function checkRows() {
  const element = document.querySelector('textarea')
  const maxWidth = 450
  // const lineHeight = parseInt(getComputedStyle(element).lineHeight, 10)
  const maxLines = 3
  const maxChars = Math.floor(maxWidth / (getComputedStyle(element).fontSize.slice(0, -2) * 0.6))
  let newText = ''
  let lines = 0
  text.value.split('\n').forEach(line => {
    if (line.length > maxChars) {
      let startIndex = 0
      while (startIndex < line.length) {
        newText += line.substring(startIndex, startIndex + maxChars) + '\n'
        startIndex += maxChars
        lines++
        if (lines === maxLines) {
          break
        }
      }
    } else {
      newText += line + '\n'
      lines++
    }
    if (lines === maxLines) {
      return
    }
  })
  text.value = newText.slice(0, -1)
  textArea.value.style.height = '1px'
  textArea.value.style.height = (12 + textArea.value.scrollHeight) + 'px'
  contArea.value.style.height = '1px'
  contArea.value.style.height = (12 + contArea.value.scrollHeight) + 'px'
}

const isRed = ref(false)
function good() {
  isRed.value = !isRed.value
}

const modifyreply = da => {
  da.ismodify = !da.ismodify
}
</script>

<style scoped>
@import '@/assets/reply.css';
</style>
