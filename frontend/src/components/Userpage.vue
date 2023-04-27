<template>
  <div class="user-container">
    <ul class="user-contain">
      <li class="user-item">
        <router-link
          to="/"
          class="userfrm">
          로그인
        </router-link>
      </li>
      <li class="user-item">
        <router-link
          to="/"
          class="userfrm">
          회원가입
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script setup>

</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.user-container {
  width: 100%;
  height: 30px;
  display: flex;
}

.user-contain {
  width: 100%;
  list-style: none;
  display: flex;
  justify-content: end;
  margin-right: 30px;
}

.user-item {
  width: 70px;
  height: 30px;
  font-size: 20px;
  font-weight: 700;
}

.userfrm {
  font-size: 12px;
  color: #aeaeae;
}
</style>
