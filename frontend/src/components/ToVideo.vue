<template>
  <div class="vedio-warp">
    <div class="title">
      <div class="vedio-title">
        영상
      </div>
      <button class="addvedio">
        더보기 >
      </button>
    </div>
    <div class="vedio-container">
      <div class="vedio">
        <iframe
          width="500"
          height="300"
          :src="videoUrl1"
          frameborder="1"
          allow="autoplay; encrypted-media"
          allowfullscreen></iframe>
      </div>
      <div class="vedio">
        <iframe
          width="500"
          height="300"
          :src="videoUrl2"
          frameborder="1"
          allow="autoplay; encrypted-media"
          allowfullscreen></iframe>
      </div>
      <div class="vedio">
        <iframe
          width="500"
          height="300"
          :src="videoUrl3"
          frameborder="1"
          allow="autoplay; encrypted-media"
          allowfullscreen></iframe>
      </div>
    </div>
  </div>
</template>

<script setup>
// 메모장에 키코드 복사 후 실행
</script>

<style scoped>
.vedio-warp {
  width: 100%;
  height: 500px;
}
.vedio-container {
  width: 100%;
  height: 450px;
  display: flex;
  background: black;
}

.title {
  display: flex;
  width: 100%;
  height: 30px;
}

.vedio-title {
  width: 100%;
  text-align: start;
  margin-left: 50px;
  font-size: 18px;
  font-weight: 700;
}

.addvedio {
  width: 80px;
  height: 100%;
  font-size: 12px;
  color: #cacaca;
}

.vedio {
  width: 550px;
  height: 350px;
  padding-top: 4%;
}

.addvedio {
  background: none;
  border: none;
  cursor: pointer;
}
</style>
