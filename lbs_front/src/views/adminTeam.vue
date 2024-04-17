<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0">
      <!--      功能区域-->
      <el-button-group type="primary">
        <el-button @click="add">新增</el-button>
      </el-button-group>
    </div>
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column label="ID" prop="teamId" sortable/>
      <el-table-column label="比赛项目" prop="raceId" sortable/>
      <el-table-column label="得分" prop="score"/>

      <el-table-column fixed="right" label="操作" width="200">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确定删除吗?" @confirm="handleDelete(scope.row.teamId)">
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
            <el-input v-model="form.teamId" style="width: 80%" disabled/>
          </el-form-item>
          <el-form-item label="比萨项目">
            <el-input v-model="form.raceId" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="得分">
            <el-input v-model="form.score" style="width: 80%"/>
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

let dialogVisible = ref(false)

let tableData = ref([])


const timer=ref()
load()

timer.value=window.setInterval(() => {
  setTimeout(load(),0)
},1000)
function load() {
  request.get("/team/all").then(res => {
    console.log(res);
    tableData.value=res
    console.log(res[0].teamId);
  })
}

function add() {
  dialogVisible.value = true;
  console.log(dialogVisible)
  form.value = {};
}

function save() {

  if (form.value.teamId) {
    request.post("/team", form.value).then(res => {
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
    request.post("/team", form.value).then(res => {
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
  request.delete("/team/delete/" + id).then(res => {
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
