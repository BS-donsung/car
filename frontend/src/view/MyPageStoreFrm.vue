<template>
  <Nav />
  <PNav />
  <div class="contain">
    <div class="comp-box">
      <CompCID :cid="route.query.cid" />
    </div>
  </div>
</template>

<script setup>
import Nav from '@/view/ToNav.vue'
import PNav from '@/components/PrivateNav.vue'
import { useRoute } from 'vue-router'
import CompCID from '@/components/ComparCID.vue'

const route = useRoute()


</script>

<style scoped>
.contain{
  background: #F0F1F8;
  height: 100vh;
  width: 100vw;
  padding-top: 50px;
}
.comp-box {
    width: 50vw;
    margin: 0 auto;

}
.cidcontain {
    margin-top: 0;
}
</style>
