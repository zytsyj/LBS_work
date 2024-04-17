<template>
  <el-row class="tac">
    <el-col :span="12">
      <el-menu
          style="width: 200px; min-height: calc(100vh - 50px);"
          class="el-menu-vertical-demo"
      >

        <router-link to="member">
          <el-menu-item index="1">
            <el-icon><Avatar /></el-icon>
            <span>队员信息</span>
          </el-menu-item>
        </router-link>

        <router-link to="teams">
          <el-menu-item index="2">
            <el-icon><Notification /></el-icon>
            <span>队伍信息</span>
          </el-menu-item>
        </router-link>

      </el-menu>
    </el-col>
  </el-row>
</template>

<script setup>

import {Avatar, Notification} from "@element-plus/icons-vue";


</script>
