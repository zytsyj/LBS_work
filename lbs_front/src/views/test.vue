<template>
  这是自动更新界面
  <el-button @click="Reset" type="primary">Reset</el-button>
</template>

<script setup>
import request from "@/utils/request.js";
import {onMounted, onUnmounted, ref} from "vue";

function autoUpdate(){
  request.get('/member/all').then(res=>{
    for(let i=0;i<res.length;i++){
      res[i].memberx+=res[i].memberx<res[i].desx?parseInt(Math.random()*5):-parseInt(Math.random()*5)
      res[i].membery+=res[i].membery<res[i].desy?parseInt(Math.random()*5):-parseInt(Math.random()*5)
      request.post('/member',res[i])
    }
  })
}
function Reset(){
  request.post("/member/database").then(res=>{})
}
const timer=ref()
onMounted(()=>{
  timer.value=window.setInterval(() => {
  setTimeout(autoUpdate(),0)
},1000)
})

onUnmounted(()=>{
  clearInterval(timer.value)
})
</script>