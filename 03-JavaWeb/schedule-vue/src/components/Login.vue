<script setup lang="ts">

import { ref, reactive } from "vue";

let loginUser = reactive({
  username: '',
  password: ''
})

let usernameMsg = ref('')
let passwordMsg = ref('')

function checkUsername() {
  let usernameReg = /^[a-zA-Z0-9]{5,10}$/;
  if (!usernameReg.test(loginUser.username)) {
    usernameMsg.value = '用户名格式不正确';
    return false;
  }
  usernameMsg.value = 'OK';
  return true;
}
function checkPassword() {
  console.log(loginUser)
  let passwordReg = /^[0-9]{6}$/;
  if (!passwordReg.test(loginUser.password)) {
    passwordMsg.value = '密码格式不正确';
    return false;
  }
  passwordMsg.value = 'OK';
  return true;
}
</script>

<template>
  <form id="content" method="post" action="/user/login">
    <label>
      <span id="usr">请输入用户名：</span>
      <input type="text" name="username" id="username" v-model="loginUser.username" @blur="checkUsername()">
    </label>
    <span class="tip" id="usernameRegTip">{{ usernameMsg }}</span>
    <label>
      <span id="pwd">请输入密码：</span>
      <input type="password" name="password" id="password" v-model="loginUser.password" @blur="checkPassword()">
    </label>
    <span class="tip" id="passwordRegTip">{{ passwordMsg }}</span>
    <div id="btns">
      <button class="btn" id="login" type="submit">登录</button>
      <button class="btn" id="reset" type="reset">重置</button>
      <RouterLink to="/register" class="btn" id="register">去注册</RouterLink>
    </div>
  </form>
</template>

<style scoped>
#content {
  margin: 30px auto;
  width: 400px;
  text-align: center;
  display: flex;
  flex-direction: column;
}
label {
  margin-bottom: 15px;
}
#usr, #pwd {
  display: inline-block;
  width: 150px;
  font-size: 20px;
}
#usr {
  letter-spacing: 1px;
}
#pwd {
  margin-top: 15px;
  letter-spacing: 4px;
}
input {
  width: 200px;
  height: 28px;
  border: none;
  border-radius: 3px;
}
input:focus {
  outline: none;
}
#btns {
  margin: 20px auto;
  text-align: center;
  display: flex;
  flex-wrap: wrap;
}
.btn {
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
.tip {
  color: red;
  margin-left: 20px;
}
</style>