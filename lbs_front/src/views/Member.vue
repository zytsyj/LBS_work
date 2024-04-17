<template>
  <div style="padding: 10px">
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column label="ID" prop="memberId" sortable/>
      <el-table-column label="名称" prop="name"/>
      <el-table-column label="队伍ID" prop="teamId" sortable/>
      <el-table-column label="位置-x" prop="memberx"/>
      <el-table-column label="位置-y" prop="membery"/>
      <el-table-column label="到终点距离" prop="distance"/>
      <el-table-column label="desx" prop="desx"/>
      <el-table-column label="desy" prop="desy"/>

      <el-table-column fixed="right" label="操作" width="200">
        <template #default="scope">
          <el-popconfirm title="确定删除吗?" @confirm="handleDelete(scope.row.memberId)">
            <template #reference>
              <el-button size="small" type="danger">删除</el-button>
            </template>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
// @ is an alias to /src
import request from "@/utils/request";
import {onMounted, onUnmounted, ref} from "vue";
import {ElMessage} from "element-plus";


let tableData = ref([])

let allowTeam = JSON.parse(sessionStorage.getItem("user")).data.teamId
const timer = ref()

onMounted(()=>{
  load()

  timer.value=window.setInterval(() => {
    setTimeout(load(),0)
  },1000)
})

function load() {
  request.get("/team/" + allowTeam).then(res => {
    console.log(allowTeam)
    console.log(res);
    tableData.value = res
    console.log(res[0].teamId);
  })
}

function handleDelete(id) {
  console.log(id);
  request.delete("/member/delete/" + id).then(res => {
    if (res.code === '0') {
      ElMessage({
        type: "success",
        message: "删除成功"
      })
    } else {
      ElMessage({
        type: "error",
        message: res.msg
      })
    }
    load() // 刷新
  })
}

onUnmounted(()=>{
  clearInterval(timer.value)
})

</script>
