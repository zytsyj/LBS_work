<script setup>
import {Lock, User} from "@element-plus/icons-vue";
import request from "@/utils/request";
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";

let form = reactive({})
let formRef = ref()
let rules = {
  name: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
  ]
}

function login() {
  console.log(form)
  formRef.value.validate((valid) => {
    if (valid) {
      request.post("/member/login", form).then(res => {
        console.log(res)
        if (res.code === '0') {
          ElMessage({
            type: "success",
            message: "登录成功"
          })
          sessionStorage.setItem("user", JSON.stringify(res))
          router.push({path: '/member'})
          console.log(sessionStorage.getItem("user"))
        } else {
          ElMessage({
            type: "error",
            message: res.msg
          })
        }
      })
    }
  })
}
</script>

<template>
  <div style="width: 100%; height: 100vh;background: darkcyan; overflow: hidden">
    <div style="width: 400px; margin: 150px auto">
      <div style="color: #cccccc; font-size: 30px;text-align: center">
        欢迎登录！
      </div>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="auto" style="max-width: 600px">
        <el-form-item prop="name">
          <el-input v-model="form.name" :prefix-icon="User"/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" :prefix-icon="Lock" show-password/>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="login">登录</el-button>
        </el-form-item>
        <el-form-item label="没有账号？">
          <el-button style="width: 100%" type="primary" @click="$router.push('/register')">点击注册</el-button>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="$router.push('/admin')">管理员入口</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style scoped>

</style>