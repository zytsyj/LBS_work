<script setup>
import {Key, Lock, User} from "@element-plus/icons-vue";
import request from "@/utils/request";
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";

let form = reactive({})
const formRef = ref()
let router = useRouter()
let rules = {
  name: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 6, max: 15, message: '长度在6和15之间！', trigger: 'blur'},
  ],
  confirm: [
    {required: true, message: '请确认密码', trigger: 'blur'},
    {min: 6, max: 15, message: '长度在6和15之间！', trigger: 'blur'},
  ]
}

function register() {
  formRef.value.validate((valid) => {
    if (valid) {
      if (form.password !== form.confirm) {
        ElMessage({
          type: "error",
          message: "两次输入密码不一致"
        })
        return
      }
      request.get("/team/" + form.teamId).then(res => {
        console.log(res)
        if (res !== '') {
          console.log(1)
          request.post("/member", form).then(res => {
            if (res.code === '0') {
              console.log(4)
              ElMessage({
                type: "success",
                message: "注册成功"
              })
              router.push({path: '/login'})
            } else {
              console.log(2)
              ElMessage({
                type: "error",
                message: res.msg
              })
            }
          })
        } else {
          console.log(3)
          ElMessage({
            type: "error",
            message: "没有相应的队伍！"
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
        欢迎注册！
      </div>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="auto" style="max-width: 600px">
        <el-form-item prop="name">
          <el-input v-model="form.name" :prefix-icon="User"/>
        </el-form-item>
        <el-form-item prop="teamId">
          <el-input v-model="form.teamId" :prefix-icon="User"/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" :prefix-icon="Lock" show-password/>
        </el-form-item>
        <el-form-item prop="confirm">
          <el-input v-model="form.confirm" :prefix-icon="Key" show-password/>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="register">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style scoped>

</style>