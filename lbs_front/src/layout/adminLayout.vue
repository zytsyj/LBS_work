<script setup>

import AdminHeader from "@/components/adminHeader.vue";
import AdminAside from "@/components/adminAside.vue";
</script>

<template>
  <!--    头部-->
  <adminHeader/>
  <!--主体-->
  <div style="display: flex">
    <!--      侧边栏-->
    <adminAside/>
    <!--      内容区域-->
    <RouterView style="flex: 1"/>
  </div>
</template>

<style scoped>

</style>