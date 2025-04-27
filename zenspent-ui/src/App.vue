<script setup lang="ts">
import {RouterView, useRoute} from 'vue-router'
import SideBar from '@/components/SideBar.vue'
import NavBar from "@/components/NavBar.vue";

const route = useRoute()
const isLoginOrRegistration = route.fullPath === '/registration' || route.fullPath === '/login'
</script>

<template>
  <NavBar/>
  <div class="lg:flex overflow-hidden">
    <SideBar v-if="!isLoginOrRegistration"/>
    <RouterView class="dark:bg-slate-700 grow h-screen overflow-y-hidden lg:overflow-x-hidden" />
  </div>
</template>
