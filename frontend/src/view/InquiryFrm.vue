<template>
  <Nav />

  <body class="body">
    <div class="container">
      <div class="content">
        <div class="left-side">
          <div class="address details">
            <font-awesome-icon
              :icon="['fas', 'location-dot']"
              class="icon" />
            <div class="topic">
              Address
            </div>
            <div class="text-one">
              Busan, Jin-gu
            </div>
            <div class="text-two">
              668 Jungang-daero, A-One Plaza 6F
            </div>
            <div class="phone details">
              <font-awesome-icon
                :icon="['fas', 'phone']"
                class="icon" />
              <div class="topic">
                Phone
              </div>
              <div class="text-one">
                +82 010-1111-2222
              </div>
              <div class="text-two">
                +82 010-2222-1111
              </div>
            </div>

            <div class="email details">
              <font-awesome-icon
                :icon="['fas', 'envelope']"
                class="icon" />
              <div class="topic">
                Email
              </div>
              <div class="text-one">
                abcdefu@gmail.com
              </div>
              <div class="text-two">
                ufedcba@gmail.com
              </div>
            </div>
          </div>
        </div>

        <div class="right-side">
          <div class="topic-text">
            문의하기
          </div>
          <p>사이트에 문의사항이 있을시 양식에 맞춰서 보내주시면, 최선을 다해 도와드리겠습니다.</p>
          <form action="#">
            <div class="input-box">
              <input
                v-model="postemail.name"
                type="text"
                placeholder="이름을 입력하시오." />
            </div>

            <div class="input-box">
              <input
                v-model="postemail.email"
                type="email"
                placeholder="이메일을 입력하시오." />
            </div>

            <div class="input-box message-box">
              <textarea
                v-model="postemail.content"
                placeholder="내용을 입력하시오."></textarea>
            </div>

            <div class="button">
              <input
                type="button"
                value="보내기"
                @click="send" />
            </div>
          </form>
        </div>
      </div>
    </div>
  </body>
</template>

<script setup>
import Nav from '@/view/ToNav.vue'
import { URL } from '@/components/global'
import { reactive } from 'vue'
import router from '@/router'
import axios from 'axios'

const postemail = reactive({
  name: '',
  email: '',
  content: '',
})

const send = () => {
  let data = {
    name: postemail.name,
    email: postemail.email,
    content: postemail.content,
  }

  axios.post(URL + '/mail/inquiry', data)
    .then(res => {
      console.log('텍', res)
      if (res.data == 'mailerr') {
        throw new Error('메일전송에 실패했습니다.')
      }
      alert('메일전송 하였습니다. 최선을 다하여 해결하겠습니다.')
      router.push({
        path: '/inquiry',
      })
      window.location.reload(true)
    })
    .catch( (e) => {
      console.log(e)
    })
}
</script>

<style scoped>
@import '@/assets/inquiry.css';
</style>
