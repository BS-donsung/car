<template>
  <Nav />
  <div class="container">
    <div class="content">
      <div class="left-side">
        <div class="address details">
          <font-awesome-icon :icon="['fas', 'location-dot']" />
          <div class="topic">
            Address
          </div>
          <div class="text-one">
            Busan, Jin-gu
          </div>
          <div class="text-two">
            668 Jungang-daero, A-One Plaza 6F
          </div>
        </div>

        <div class="phone details">
          <font-awesome-icon :icon="['fas', 'phone']" />
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
          <font-awesome-icon :icon="['fas', 'envelope']" />
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

      <div class="right-side">
        <div class="topic-text">
          문의하기
        </div>
        <p>사이트에 문의사항이 있을시 양식에 맞춰서 보내주시면, 최선을 다해 도와드리겠습니다.</p>
      </div>

      <form action="#">
        <div class="input-box">
          <input
            type="text"
            placeholder="이름을 입력하시오." />
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import Nav from '@/view/ToNav.vue'
</script>

<style scoped>
.container {
  margin-top: 150px;
}
</style>
