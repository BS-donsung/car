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
        </div>
      </div>
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
