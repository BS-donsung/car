<template>
  <Nav />
  <div class="comp-container">
    <div class="comp-contain">
      <div class="containgrid">
        <div>
          <CompCID :cid="store.CarInfomation[0]" />
        </div>
        <div class="mid">
        </div>
        <div>
          <CompCID :cid="store.CarInfomation[1]" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import CompCID from '@/components/ComparCID.vue'
import { useCompStore } from '@/store/index'
import Nav from '@/view/ToNav.vue'


const store = useCompStore()


</script>

<style scoped>
.comp-container {
  width: 100%;
  height: 130vh;
  background: #F0F1F8;
}
.comp-contain {
  display: inline;
  display: grid;
  grid-template-rows: auto;
}

.containgrid {
  width: 100vh;
  height: 100vh;
  display: grid;
  grid-template-columns: 0.4fr 0.2fr 0.4fr;
  margin: 0 auto;
}
</style>
