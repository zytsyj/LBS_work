<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0">
      <!--      功能区域-->
      <el-button-group type="primary">
        <el-button @click="add">新增</el-button>
      </el-button-group>
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="search" clearable placeholder="请输入关键字" style="width: 20%"></el-input>
      <el-button style="margin-left: 10px" type="primary" @click="load">查询</el-button>
    </div>
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column label="ID" prop="memberId" sortable/>
      <el-table-column label="名称" prop="name"/>
      <el-table-column label="密码" prop="password"/>
      <el-table-column label="队伍ID" prop="teamId" sortable/>
      <el-table-column label="位置-x" prop="memberx"/>
      <el-table-column label="位置-y" prop="membery"/>
      <el-table-column label="到终点距离" prop="distance"/>

      <el-table-column fixed="right" label="操作" width="200">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确定删除吗?" @confirm="handleDelete(scope.row.memberId)">
            <template #reference>
              <el-button size="small" type="danger">删除</el-button>
            </template>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">
      <el-dialog
          v-model="dialogVisible"
          title="Tips"
          width="30%"
      >
        <el-form :model="form" label-width="120px">
          <el-form-item label="ID">
            <el-input v-model="form.memberId" style="width: 80%" disabled/>
          </el-form-item>
          <el-form-item label="名称">
            <el-input v-model="form.name" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="队伍ID">
            <el-input v-model="form.teamId" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="位置-x">
            <el-input v-model="form.memberx" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="位置-y">
            <el-input v-model="form.membery" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="到终点距离^2">
            <el-input v-model="form.distance" style="width: 80%" disabled/>
          </el-form-item>

        </el-form>
        <template #footer>
          <div class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="save">
              确定
            </el-button>
          </div>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script setup>
// @ is an alias to /src
import request from "@/utils/request";
import {onUnmounted, reactive, ref} from "vue";
import {ElMessage} from "element-plus";

let form = ref({})
let search = ref('')

let dialogVisible = ref(false)

let tableData = ref([])


const timer=ref()
load()

// timer.value=window.setInterval(() => {
//   setTimeout(load(),0)
// },1000)
function load() {
  request.get("/member/all", {
    search: search.value
  }).then(res => {
    // console.log(res);
    tableData.value=res
  })
}

function add() {
  dialogVisible.value = true;
  console.log(dialogVisible)
  form.value = {};
}

function save() {

  if (form.value.memberId) {
    request.post("/member", form.value).then(res => {
      console.log(res)
      if (res.code === '0') {
        ElMessage({
          type: "success",
          message: "更新成功"
        })
      } else {
        ElMessage({
          type: "error",
          message: res.msg
        })
      }
    })

  } else {
    console.log(form.value)
    request.post("/member", form.value).then(res => {
      console.log(res)
      if (res.code === '0') {
        ElMessage({
          type: "success",
          message: "新增成功"
        })
      } else {
        ElMessage({
          type: "error",
          message: res.msg
        })
      }
    })
  }
  dialogVisible.value = false;

  load() // 刷新
}

function handleEdit(row) {
  form.value = JSON.parse(JSON.stringify(row));
  dialogVisible.value = true;
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
// onUnmounted(()=>{
//   clearInterval(timer.value)
// })

</script>
