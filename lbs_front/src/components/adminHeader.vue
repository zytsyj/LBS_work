<template>
  <div style="height:50px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex;">
    <div style="width: 200px; padding-left: 30px; font-weight: bold; color: skyblue;">后台管理</div>
    <div style="flex: 1;"></div>
    <div style="width: 150px;">
      <el-dropdown style="display: contents;">
          <span class="el-dropdown-link">
            <el-icon><UserFilled /></el-icon>
            administrator
            <el-icon class="el-icon--right">
              <arrow-down/>
            </el-icon>
          </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('/login')">
              <el-icon class="el-icon--left"><SwitchButton /></el-icon>
              退出登录
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>
<script setup>
import {ArrowDown,  SwitchButton, UserFilled} from "@element-plus/icons-vue";



</script>

<style scoped>

</style>