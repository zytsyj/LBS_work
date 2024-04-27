<script setup>
import * as echarts from "echarts";
import {onMounted, reactive, ref} from "vue";
import request from "@/utils/request.js";

let data = ref([]);

// 定义不同聚类的颜色
const CLUSTER_COLORS = [
  'rgb(255, 99, 132)',    // 红色
  'rgb(54, 162, 235)',    // 蓝色
  'rgb(255, 205, 86)',    // 黄色
  'rgb(75, 192, 192)',    // 青色
  'rgb(153, 102, 255)',   // 紫色
  'rgb(255, 159, 64)',    // 橙色
  'rgb(255, 0, 255)',     // 品红
  'rgb(0, 255, 0)',       // 绿色
  'rgb(0, 255, 255)',     // 青色
  'rgb(128, 128, 128)'    // 灰色
];
let raceList=ref([])
let memberList=ref([])
let clusterName=ref([
    "比赛"
])

let initId = ref(0)
//初始化函数
const pieces = []
for (let i = 0; i < clusterName.value.length; i++) {
  pieces.push({
    value: i,
    label: clusterName.value[i],
    color: CLUSTER_COLORS[i]
  });
}

const option = ref({
  xAxis: {},
  yAxis: {},
  visualMap: {
    type: 'piecewise',
    top: 'middle',
    min: 0,
    max: data.value.reduce((max, item) => Math.min(max, item.cluster), 0), // 最大聚类数
    left: 10,
    splitNumber: CLUSTER_COLORS.length,
    dimension: 'cluster', // 聚类维度
    pieces: pieces // 引用分段信息变量
  },
  tooltip: {
    formatter: function(params) {
      return params.data.name; // 在鼠标悬停时显示点的名称
    }
  },
  grid:{
    left: 120
  },
  series: {
    type: 'scatter',
    encode: { tooltip: [0, 1] },
    symbolSize: 15,
    itemStyle: {
      borderColor: '#555'
    },
    data: null
  }
});

function selectRace(id){
  if(id === 0){
    option.value.series.data=data.value.map(item => ({
      value: item.value,
      itemStyle: {
        color: CLUSTER_COLORS[item.cluster] // 根据聚类标签确定颜色
      },
      name: item.name // 设置点的名称
    }))
  } else {
    initId.value = id
    option.value.series.data= data.value.filter(item => item.raceId === id).map(item => ({
      value: item.value,
      itemStyle: {
        color: CLUSTER_COLORS[item.cluster] // 根据聚类标签确定颜色
      },
      name: item.name // 设置点的名称
    }));
  }

  Chart.setOption(option.value);
}
function load(){
  let raceF=false, memberF=false
  data.value=[]
  request.get("/race/all").then((res)=>{
    console.log(res);
    raceList.value=res
    insertRace()
    raceF=true;
    if (raceF && memberF) {
      selectRace(initId.value);
    }
  })
  request.get("/member/all").then((res)=>{
    console.log(res);
    memberList.value=res
    insertMember()
    memberF=true;
    if (raceF && memberF) {
      selectRace(initId.value);
    }
  })

}

let timer = ref()
let Chart = reactive()

function init(){
  option.value.series.data=data.value.map(item => ({
    value: item.value,
    itemStyle: {
      color: CLUSTER_COLORS[item.cluster] // 根据聚类标签确定颜色
    },
    name: item.name // 设置点的名称
  }))
  console.log(3)
  console.log(option.value.series.data)
  Chart.setOption(option.value)
}
function insertRace(){
  for(let item in raceList.value){
    let value=[raceList.value[item].desx,raceList.value[item].desy];
    let name="比赛"+raceList.value[item].raceId+"终点";
    console.log(item)
    data.value.push({value:value,cluster:0,name:name,raceId:raceList.value[item].raceId});
  }
}

function insertMember(){
  let cluster=0;
  let prevTeam=0;
  for(let i=0;i<memberList.value.length;i++){
    if(prevTeam !== memberList.value[i].teamId){
      cluster++;
      prevTeam=memberList.value[i].teamId;
      clusterName.value.push("队伍"+memberList.value[i].teamId);
    }
    let value=[memberList.value[i].memberx, memberList.value[i].membery];
    let name="选手"+memberList.value[i].memberId
    console.log(memberList.value[i])
    data.value.push({value:value,cluster:cluster,name:name, raceId: memberList.value[i].raceid})
  }
}


onMounted(()=>{
  Chart = echarts.init(document.getElementById("main"));
  load()
  console.log(data.value)
  console.log(option.value)

  timer.value=window.setInterval(() => {
    setTimeout(load(),0)
  },1000)
})

</script>

<template>
  <div style="padding: 10px">
    <el-table :data="raceList" style="width: 100%">
      <el-table-column prop="raceId" label="ID" width="180" />
      <el-table-column prop="desx" label="终点-x" width="180" />
      <el-table-column prop="desy" label="终点-y" width="180" />
      <el-table-column prop="winnerId" label="胜者" />
    </el-table>
    <br/>
    <div style="display: flex">
      <div style="display: flex">
        <el-button-group>
          <el-button @click="load()" type="primary">手动加载(测试用)</el-button>
          <el-button @click="selectRace(0)" type="info">所有比赛</el-button>
          <el-button v-for="race in raceList" @click="selectRace(race.raceId)" type="info">比赛{{race.raceId}}</el-button>
        </el-button-group>

      </div>
    </div>

    <div class="echarts-box" style="width: 650px;height: 650px">
      <div id="main" :style="{ width: '100%', height: '100%' }"></div>
    </div>
  </div>
</template>

<style scoped>

</style>