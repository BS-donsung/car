<template>
  <div>
    <nav class="private-body">
      <router-link to="/mypage">
        <button>내정보</button>
      </router-link>
      <router-link to="/mypage/boards">
        <button>작성글</button>
      </router-link>
      <router-link to="/mypage/replies">
        <button>작성댓글</button>
      </router-link>
      <router-link to="/mypage/alerts">
        <button>알림(미구현)</button>
      </router-link>
      <router-link to="/mypage/stores">
        <button>장바구니</button>
      </router-link>
    </nav>
  </div>
</template>

<script setup></script>

<style scoped>
.private-body {
  
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  text-align: center;
  border: none;
}
a button {
  width: 100%;
  height: 100%;
  border: none;
}
a.router-link-exact-active button,
a button:hover {
  background-color: #8EA7E9;
  color: white;
  font-weight: bold;
}
</style>
