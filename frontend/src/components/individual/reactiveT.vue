<template>
  <select 

    v-model="state.selectedItem"
    @change="change">
    <option

      v-for="each in state.items"
      :key="each"
      :value="each"
      v-text="each"></option>
  </select>

  <div>{{ state.selectedItem }}</div>
</template>

<script>

  import { reactive } from 'vue'

  export default {

      setup() {

          const state =  reactive({ 
              
              items : ['one!', 'two~', 'three!', 'four~'],
              selectedItem : 'three!'

          })    

          function change() {

              console.log(state.selectedItem)

          }

          return { state, change }

      }

  }

</script>
