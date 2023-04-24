<template>
  <div class="wrap">
    <div class="countreply">
      <button
        class="icons"
        @click="good">
        <font-awesome-icon
          :icon="['far', 'heart']"
          :class="{hearticons:isRed}" />
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
        <div class="inneruser">
          {{ reply?.user?.nickname }}({{ reply?.user?.username }})
        </div>
        <div class="innercontent">
          {{ reply?.text }}
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
      </div>
    </div>
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
</script>

<style scoped>
@import '@/assets/reply.css';
</style>
