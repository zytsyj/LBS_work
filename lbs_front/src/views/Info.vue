<script setup>
import request from "@/utils/request"
import {reactive} from "vue";
import {ElMessage} from "element-plus";
import Header from "@/components/header.vue";


let form = reactive({})
let str = sessionStorage.getItem("user") || "{}"
form = JSON.parse(str).data

function update() {
  request.put("/member", form).then(res => {
    console.log(res)
    if (res.code === '0') {
      ElMessage({
        type: "success",
        message: "更新成功"
      })
      sessionStorage.setItem("user", JSON.stringify(form))
    } else {
      ElMessage({
        type: "error",
        message: res.msg
      })
    }
  })
}
</script>

<template>
  <Header/>
  <div style="flex: 1">
    <el-card style="width: 80%; margin: 10px 10% auto auto">
      <template #header>
        <div class="card-header">
          <span>个人信息</span>
        </div>
      </template>
      <el-form-item label="用户名">
        <el-input v-model="form.username" disabled style="width: 80%"/>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickName" style="width: 80%"/>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" show-password style="width: 80%"/>
      </el-form-item>
      <template #footer>
        <div style="text-align: center">
          <el-button type="primary" @click="update">保存</el-button>
        </div>
      </template>
    </el-card>
  </div>
</template>

<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>