<script setup lang="ts">
import {reactive, ref} from "vue";
import {http} from "@/utils/request";
import router from "@/router";

let regisUser = reactive({
  username: '',
  password: '',
})

let usernameMsg = ref('')
let passwordMsg = ref('')
let repPassword = ref('')
let repPasswordMsg = ref('')

async function checkUsername() {
  let usernameReg = /^[a-zA-Z0-9]{5,10}$/;
  if (!usernameReg.test(regisUser.username)) {
    usernameMsg.value = '用户名格式不正确';
    return false;
  }

  let {data} = await http.post(`user/checkUsernameUsed?username=${regisUser.username}`)
  console.log(data)
  if (data.code !== 200) {
    usernameMsg.value = data.message
    return false
  }

  usernameMsg.value = '';
  return true;
}
function checkPassword() {
  let passwordReg = /^[0-9]{6}$/;
  if (!passwordReg.test(regisUser.password)) {
    passwordMsg.value = '密码格式不正确';
    return false;
  }
  passwordMsg.value = '';
  return true;
}
function checkRepeatPassword() {
  if (regisUser.password !== repPassword.value) {
    repPasswordMsg.value = '两次密码不必配';
    return false;
  }
  repPasswordMsg.value = '';
  return true;
}
async function handleRegister() {
  let [flag1, flag2, flag3] = await Promise.all([checkUsername(), checkPassword(), checkRepeatPassword()])
  if (flag1 && flag2 && flag3) {
    let {data} = await http.post('user/register', regisUser)
    if (data.code === 200) {
      await router.push('/login')
    }
  }
}
function clearForm() {
  regisUser.username = ''
  regisUser.password = ''
  usernameMsg.value = ''
  passwordMsg.value = ''
  repPassword.value = ''
  repPasswordMsg.value = ''
}
</script>

<template>
  <div class="container">
    <form>
      <div class="form-control">
        <input type="text" name="username" id="username" v-model="regisUser.username" @blur="checkUsername()">
        <label>Username</label>
        <span class="tip" id="usernameRegTip">{{ usernameMsg }}</span>
      </div>
      <div class="form-control">
        <input type="password" name="password" id="password" v-model="regisUser.password" @blur="checkPassword()">
        <label>Password</label>
        <span class="tip" id="passwordRegTip">{{ passwordMsg }}</span>
      </div>
      <div class="form-control">
        <input type="password" name="repeatPassword" id="repeatPassword" v-model="repPassword" @blur="checkRepeatPassword()">
        <label>Password</label>
        <span class="tip" id="passwordRegTip">{{ repPasswordMsg }}</span>
      </div>
      <div class="btn" id="login" @click="handleRegister">Register</div>
      <p class="text">Have an account? </p><RouterLink to="/login" id="register">Login</RouterLink>
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