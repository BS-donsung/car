<template>
  <h1>니 차 살래?</h1>
  <select v-model="selected">
    <option
      disabled
      value="">
      Please select one
    </option>
    <option>현대</option>
    <option>기아</option>
    <option>쉐보레</option>
  </select>

  <select v-model="selected2">
    <option
      disabled
      value="">
      Please select one
    </option>
    <option>경차</option>
    <option>소형차</option>
    <option>중형차</option>
  </select>

  <select v-model="selected3">
    <option
      disabled
      value="">
      Please select one
    </option>
    <option>아벤떼</option>
    <option>소나타</option>
    <option>그랑죠</option>
  </select><br />

  <span>Selected: {{ selected }}  {{ selected2 }}  {{ selected3 }}</span>
</template>

<script setup>
import { ref } from 'vue'
const selected = ref('None')
const selected2 = ref('None')
const selected3 = ref('None')
</script>

<style>
</style>
