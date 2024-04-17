<template>
  <div style="padding: 10px">
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column label="ID" prop="teamId" sortable/>
      <el-table-column label="比赛项目" prop="raceId" sortable/>
      <el-table-column label="得分" prop="score"/>
    </el-table>
    <div style="margin: 10px 0">
    </div>
  </div>
</template>

<script setup>
// @ is an alias to /src
import request from "@/utils/request";
import {onUnmounted, ref} from "vue";

let tableData = ref([])

const timer=ref()
load()

timer.value=window.setInterval(() => {
  setTimeout(load(),0)
},1000)
function load() {
  request.get("/team/all", {
  }).then(res => {
    console.log(res);
    tableData.value=res
    console.log(res[0].teamId);
  })
}

onUnmounted(()=>{
  clearInterval(timer.value)
})


</script>
