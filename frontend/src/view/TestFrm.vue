<template>
  <div
    v-for="da in data"
    :key="da">
    <div v-if="da.isModify">
      {{ da.content }}
      <button @click="clickModify(da)">
        수정
      </button>
    </div>
    <div v-if="!da.isModify">
      <textarea
        v-model="da.content"></textarea>
      <button @click="clickModify(da)">
        수정
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref,onMounted } from 'vue'


const data = ref([
  {content : 'hi'},
  {content : 'hihi'},
])

const clickModify = da =>{
  da.isModify = !da.isModify
}
const setModify = () => {
  for (const da of data.value) {
    da['isModify'] = true
    console.log(da.content)
  }
}
onMounted(()=>{
  setModify()
})
</script>

<style>

</style>
