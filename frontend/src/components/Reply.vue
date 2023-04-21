<template>
  <div class="wrap">
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
  replies: Array
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

function getbno() {
  return props.bno
}

onMounted(() => {
  getbno()
})
</script>

<style scoped>
.wrap {
  width: 100%;
  display: flex;
  justify-content: center;
  /* transform: translateX(10%); */
  margin-top: 20px;
}

.reply-title {
  width: 100%;
  height: 30px;
  text-align: left;
  margin-left: 10px;
  font-size: 16px;
}

.reply-container {
  position: relative;
  width: 100%;
  height: 150px;
  border: 1px solid #dbdbdb;
  border-radius: 6px;
}

.reply-container .nick-text,
.reply-container .reply-text {
  margin-top: 20px;
  display: flex;
  text-align: left;
  width: 100%;
}

.nick-text {
  font-size: 14px;
  font-weight: 600;
  margin-left: 20px;
}

textarea {
  resize: none;
  width: 100%;
  height: 45px;
  border: 1px solid #dbdbdb;
  padding: 0px 10px 0 10px;
  font-size: 13px;
  line-height: 38px;
  font-family: 'Poppins', sans-serif;
  overflow: hidden;
  border: none;
}

textarea:focus {
  border: 1px solid #dbdbdb;
  box-shadow: 0 0 10px -5px #bebebe;
}

.addBtn {
  width: 100%;
  height: 30px;
  margin-top: 20px;
  padding-right: 20px;
}

button {
  float: right;
  border: none;
  background: none;
  color: #bebebe;
  font-size: 13px;
}
</style>
