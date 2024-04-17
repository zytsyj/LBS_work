import { createRouter, createWebHistory } from 'vue-router'
import Member from "@/views/Member.vue";
import Login from "@/views/Login.vue";
import Layout from "@/layout/Layout.vue";
import Info from "@/views/Info.vue";
import Register from "@/views/Register.vue";
import Teams from "@/views/Teams.vue";

import adminLayout from "@/layout/adminLayout.vue";
import adminMember from "@/views/adminMember.vue";
import adminTeam from "@/views/adminTeam.vue";
import adminRace from "@/views/adminRace.vue";
import test from "@/views/test.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      redirect: '/login',
      component: Layout,
      children:[
        {
          path: 'member',
          name: 'member',
          component: Member
        },
        {
          path: 'teams',
          name: 'teams',
          component: Teams
        },
      ]
    },
    {
      path: '/admin/',
      name: 'admin',
      redirect: '/admin/member',
      component: adminLayout,
      children:[
        {
          path: 'member',
          name: 'adminMember',
          component: adminMember
        },
        {
          path: 'team',
          name: 'adminTeam',
          component: adminTeam
        },
        {
          path: 'race',
          name: 'adminRace',
          component: adminRace
        },
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/info',
      name: 'info',
      component: Info
    },
    {
      path: '/test',
      name: 'test',
      component: test
    }
  ]
})

export default router
