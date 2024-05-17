<script setup lang="ts">

</script>

<template>
  <div id="container">
    <h1 id="title">欢迎使用日程管理系统</h1>
    <div id="tip">
      <span>欢迎xxx</span>
      <div id="btns">
        <button class="btn">退出登录</button>
        <RouterLink to="/showSchedule" class="btn">查看我的日程</RouterLink>
        <RouterLink to="/login" class="btn" id="login" type="submit">登录</RouterLink>
        <RouterLink to="/register" class="btn" id="register">去注册</RouterLink>
      </div>
    </div>
  </div>
</template>

<style scoped>
#container {
  margin-bottom: 50px;
}
#title {
  margin-top: 150px;
  font-size: 48px;
  color: #d6d6c1;
}
#tip {
  display: flex;
  justify-content: center;
}
#tip span{
  font-size: 26px;
  letter-spacing: 5px;
  color: #d6d6c1;
}
#btns {
  display: flex;
  flex-wrap: wrap;
}
.btn {
  text-align: center;
  display: block;
  margin: 0 10px;
  letter-spacing: 2px;
  font-size: 18px;
  border-radius: 5px;
  background-color: #7cdbef;
  border: none;
  box-shadow: 1px 2px 10px 0 rgba(0, 0, 0, 0.3);
}
.btn:hover {
  opacity: 0.8;
}
.btn:active {
  transform: scale(0.98);
}
.btn:focus {
  outline: none;
}
</style>