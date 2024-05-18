<script setup lang="ts">

import {ref, reactive, onMounted} from 'vue'
import {useUserStore} from "@/stores/user";
import { http } from '@/utils/request';

const userStore = useUserStore()

let scheduleList = reactive([{
  sid: 1,
  title: '1111111111111111111111111111111',
  completed: 0,
  isActive: false
},{
  sid: 2,
  title: '2222222222',
  completed: 1,
  isActive: false
}])

let {uid} = userStore.sysUser

onMounted(async () => {
  await showSchedule()
})

async function handlerDelete(index: number) {

}
async function handlerUpdate(index: number) {
  console.log(scheduleList[index])
}
async function handlerSave() {

}
async function showSchedule() {
  let {data} = await http.get(`schedule/getAllByUid?uid=${uid}`)
  console.log(data)
}

</script>

<template>
  <div>
    <h3 class="ht">您的日程如下</h3>
    <table class="tab" cellspacing="0px">
      <tr class="ltr">
        <th>编号</th>
        <th>内容</th>
        <th>进度</th>
        <th>操作</th>
      </tr>
      <tr v-for="(schedule,index) in scheduleList" :key="index" class="ltr">
        <td v-text="index+1"></td>
        <td><input id="title" type="text" v-model="schedule.title"
                   :class="{active: schedule.isActive}"
                   @click="schedule.isActive = true"
                   @blur="schedule.isActive = false" ></td>
        <td >
          <input class="btnRadio" type="radio" value="1" v-model="schedule.completed"> 已完成
          <input class="btnRadio" type="radio" value="0" v-model="schedule.completed"> 未完成
        </td>
        <td class="buttonContainer">
          <button class="btn" @click="handlerDelete(index)">删除</button>
          <button class="btn" @click="handlerUpdate(index)">保存修改</button>
        </td>
      </tr>
      <tr class="ltr buttonContainer" >
        <td colspan="4">
          <button class="btn" @click="handlerSave">新增日程</button>
        </td>
      </tr>
    </table>
  </div>
</template>

<style scoped>
input {
  border: none;
  border-radius: 3px;
  width: 80%;
}
input:focus {
  outline: none;
}
.btn {
  margin: 0 10px;
  letter-spacing: 2px;
  font-size: 14px;
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
.ht{
  text-align: center;
  color: #155557;
  font-size: 36px;
}
.tab{
  width: 80%;
  border: 5px solid cadetblue;
  margin: 0 auto;
  border-radius: 5px;
}
.btnRadio {
  width: 20px;
}
.ltr td{
  border: 1px solid  powderblue;
}
.buttonContainer{
  margin: 0 auto;
}
#title {
  background: transparent;
}
.active {
  background: whitesmoke !important;
}
</style>