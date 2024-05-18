<script setup lang="ts">
  import {useUserStore} from "@/stores/user";
  import router from "@/router";
  const userStore = useUserStore()

  let sysUser = userStore.sysUser

  function logout() {
    router.push('/login');

    // 等待一段时间后重置用户数据
    setTimeout(() => {
      // 重置用户数据
      userStore.sysUser = {
        uid: 0,
        username: ''
      };
      window.location.reload()
    }, 100);
  }
</script>

<template>
  <div id="container">
    <h1 id="title">欢迎使用日程管理系统</h1>
    <div id="tip">
      <span v-show="sysUser.username">欢迎{{ sysUser.username }}</span>
      <div id="btns">
        <button v-show="sysUser.username" class="btn" type="button" @click="logout">退出登录</button>
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