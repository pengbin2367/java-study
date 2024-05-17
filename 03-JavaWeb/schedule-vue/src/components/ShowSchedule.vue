<script setup lang="ts">

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
      <tr class="ltr">
        <td></td>
        <td >
          <input type="text">
        </td>
        <td >
          <input type="radio"  value="1"> 已完成
          <input type="radio"  value="0"> 未完成
        </td>
        <td class="buttonContainer">
          <button class="btn">删除</button>
          <button class="btn">保存修改</button>
        </td>
      </tr>
      <tr class="ltr buttonContainer" >
        <td colspan="4">
          <button class="btn">新增日程</button>
        </td>
      </tr>
    </table>
  </div>
</template>

<style scoped>
input {
  border: none;
  border-radius: 3px;
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
.ltr td{
  border: 1px solid  powderblue;
}
.buttonContainer{
  margin: 0 auto;
}

</style>