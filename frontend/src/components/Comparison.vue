<template>
  <Nav /> 
  <div class="container">
    <div class="coninner">
      <table>
        <tr class="innerimg">
          <td class="image1">
            img1
          </td>
          <td class="v-img">
            차량
          </td>
          <td class="image2">
            img2
          </td>
        </tr>
        <!-- 요약 part -->
        <tr>
          <td
            class="specifications"
            colspan="3"
            style="border-bottom: 2px solid black;">
            요약
          </td>
        </tr>
        <tr>
          <td>
            15,000,000
          </td>
          <td>
            가격
          </td>
          <td>
            20,000,000
          </td>
        </tr>
        <tr>
          <td>
            준중형 세단
          </td>
          <td>
            차종
          </td>
          <td>
            준중형 세단
          </td>
        </tr>
        <tr>
          <td>
            2023.01.01
          </td>
          <td>
            출시일
          </td>
          <td>
            2023.02.01
          </td>
        </tr>
        <tr>
          <td>
            가솔린
          </td>
          <td>
            연료
          </td>
          <td>
            LPG
          </td>
        </tr>
        <tr>
          <td>
            15.3km/L
          </td>
          <td>
            연비
          </td>
          <td>
            15.2km/L
          </td>
        </tr>
        <!-- 제원 part -->
        <tr>
          <td
            class="
            specifications"
            colspan="3"
            style="border-bottom: 2px solid black;">
            제원
          </td>
        </tr>
        <tr>
          <td>
            4,710mm / 1,825mm
          </td>
          <td>
            전장 / 전폭
          </td>
          <td>
            4,645mm / 1,800mm
          </td>
        </tr>
        <tr>
          <td>
            경차
          </td>
          <td>
            차종
          </td>
          <td>
            중형차
          </td>
        </tr>
        <tr>
          <td>
            2023.01.01
          </td>
          <td>
            출시일
          </td>
          <td>
            2023.02.01
          </td>
        </tr>
        <tr>
          <td>
            가솔린
          </td>
          <td>
            연료
          </td>
          <td>
            LPG
          </td>
        </tr>
        <tr>
          <td>
            17.5km/L
          </td>
          <td>
            연비
          </td>
          <td>
            20.0km/L
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script setup>
import Nav from '@/components/ToNav.vue'
</script>

<style scoped>
.container {
  width: 100%;
  height: 100vh;
  position: relative;
  margin-top: 8%;
}

.coninner {
  position: absolute;
  transform: translate(50%);
  border: 1px solid black;
}

table {
  border-spacing: 50px 10px;
  text-align: center;
  margin-top: 5%;
}

.v-img {
  width: 50px;
  height: 50px;
}

.image1,
.image2 {
  width: 300px;
  height: 300px;
  border: 1px solid black;
}

.specifications {
  width: 100%;
  height: 15px;
  text-align: left;
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 5px;
}
</style>
