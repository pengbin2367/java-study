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

  usernameMsg.value = 'OK';
  return true;
}
function checkPassword() {
  let passwordReg = /^[0-9]{6}$/;
  if (!passwordReg.test(regisUser.password)) {
    passwordMsg.value = '密码格式不正确';
    return false;
  }
  passwordMsg.value = 'OK';
  return true;
}
function checkRepeatPassword() {
  if (regisUser.password !== repPassword.value) {
    repPasswordMsg.value = '两次密码不必配';
    return false;
  }
  repPasswordMsg.value = 'OK';
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
  <form id="content">
    <label>
      <span id="usr">请输入用户名：</span>
      <input type="text" name="username" id="username" v-model="regisUser.username" @blur="checkUsername">
    </label>
    <span class="tip" id="usernameRegTip">{{usernameMsg}}</span>
    <label>
      <span id="pwd">请输入密码：</span>
      <input type="password" name="password" id="password" v-model="regisUser.password" @blur="checkPassword">
    </label>
    <span class="tip" id="passwordRegTip">{{passwordMsg}}</span>
    <label>
      <span id="rpwd">请确认密码：</span>
      <input type="password" name="repeatPassword" id="repeatPassword" v-model="repPassword" @blur="checkRepeatPassword">
    </label>
    <span class="tip" id="repeatPasswordRegTip">{{repPasswordMsg}}</span>
    <div id="btns">
      <button class="btn" id="login" type="button" @click="handleRegister">注册</button>
      <button class="btn" id="reset" type="button" @click="clearForm">重置</button>
      <RouterLink to="/login" class="btn" id="register">去登录</RouterLink>
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
#usr, #pwd, #rpwd {
  display: inline-block;
  width: 150px;
  font-size: 20px;
}
#usr {
  letter-spacing: 1px;
}
#pwd, #rpwd {
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