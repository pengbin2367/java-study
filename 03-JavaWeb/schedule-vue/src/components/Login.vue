<script setup lang="ts">

import { ref, reactive } from "vue";
import {http} from "@/utils/request";
import router from "@/router";
import {useUserStore} from "@/stores/user";

let userStore = useUserStore()

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
  usernameMsg.value = '';
  return true;
}
function checkPassword() {
  console.log(loginUser)
  let passwordReg = /^[0-9]{6}$/;
  if (!passwordReg.test(loginUser.password)) {
    passwordMsg.value = '密码格式不正确';
    return false;
  }
  passwordMsg.value = '';
  return true;
}
async function doLogin() {
  let [flag1, flag2] = await Promise.all([checkUsername(), checkPassword()])
  if (flag1 && flag2) {
    let {data} = await http.post('user/login', loginUser)
    if (data.code === 200) {
      console.log(data)
      userStore.sysUser.uid = data.data.loginUser.uid
      userStore.sysUser.username = data.data.loginUser.username
      await router.push('/showSchedule')
    }
  }
}
function login() {
  doLogin()
}
function clearForm() {
  loginUser.username = ''
  loginUser.password = ''
  usernameMsg.value = ''
  passwordMsg.value = ''
}
</script>

<template>
  <div class="container">
    <form>
      <div class="form-control">
        <input type="text" name="username" id="username" v-model="loginUser.username" @blur="checkUsername()">
        <label>Username</label>
        <span class="tip" id="usernameRegTip">{{ usernameMsg }}</span>
      </div>
      <div class="form-control">
        <input type="password" name="password" id="password" v-model="loginUser.password" @blur="checkPassword()">
        <label>Password</label>
        <span class="tip" id="passwordRegTip">{{ passwordMsg }}</span>
      </div>
      <div class="btn" id="login" @click="login">Login</div>
      <p class="text">Don't have an account? </p><RouterLink to="/register" id="register">Register</RouterLink>
    </form>
  </div>
</template>

<style scoped>
* {
  box-sizing: border-box;
}
@media (max-width: 380px) {
  .form-control {
    width: 180px;
  }
}
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  background-color: rgba(0, 0, 0, 0.1);
  padding: 20px 40px;
  border-radius: 5px;
  width: 400px;
  margin: 0 auto;
}
.container > h1 {
  margin-bottom: 30px;
}
.container > form .form-control {
  margin: 20px 0 40px;
  width: 300px;
  position: relative;
}
.container > form .form-control > input {
  display: block;
  border: none;
  width: 100%;
  height: 30px;
  border-radius: 5px;
  font-size: 18px;
}
.container > form .form-control > input:focus,
.container > form .form-control > input:valid {
  outline: none;
}
.container > form .form-control > label {
  position: absolute;
  top: -20px;
  left: 0;
  pointer-events: none;
  color: white;
}
.container > form .btn {
  display: inline-block;
  background-color: lightblue;
  cursor: pointer;
  width: 100%;
  padding: 15px;
  font-size: 16px;
  font-family: inherit;
  border: none;
  border-radius: 5px;
  text-align: center;
  margin: 0 auto;
}
.container > form .btn:active {
  transform: scale(0.98);
}
.container > form .text {
  margin-top: 30px;
  display: inline-block;
}
.container > form .text > a {
  text-decoration: none;
  color: lightblue;
}
.tip {
  color: #d5ea4e;
  float: right;
  font-weight: 600;
  text-shadow: 0 0 5px white;
}
</style>