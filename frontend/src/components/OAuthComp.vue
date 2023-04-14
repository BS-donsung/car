<template>
  <div class="media-options">
    <a href="#">
      <font-awesome-icon
        :icon="['fab', 'google']"
        style="color: #006af5;"
        class="i" />
      <span>Login with Google</span>
    </a>

    <a href="#">
      <font-awesome-icon
        :icon="['fas', 'k']"
        style="color: #faf200;"
        class="i" />
      <span>Login with KaKao</span>
    </a>

    <a href="#">
      <font-awesome-icon
        :icon="['fab', 'neos']"
        style="color: #62f500;"
        class="i" />
      <span>Login with Naver</span>
    </a>
  </div>
</template>

<script setup>

</script>

<style scoped>
.media-options {
  display: flex;
  align-items: center;
  flex-direction: column;
}

.media-options a {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  width: 200%;
  height: 50px;
  border: 2px solid #fff;
  margin: 10px 0;
  background: transparent;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  border-radius: 40px;
  color: #fff;
  text-decoration: none;
}

.media-options a:hover {
  background: rgba(255, 255, 255, 0.1);
}

.media-options a .i {
  font-size: 16px;
  margin: 0 8px 1.5px 0;
}

.media-options a span {
  font-size: 16px;
  font-weight: 500;
}
</style>
