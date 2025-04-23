<script setup lang="ts">
import {RouterView} from 'vue-router'
import SideBar from '@/components/SideBar.vue'
import NavBar from '@/components/NavBar.vue'
</script>

<template>
  <NavBar />
  <div class="lg:flex overflow-hidden">
    <SideBar />
    <RouterView class="dark:bg-slate-700 grow h-screen overflow-y-hidden lg:overflow-x-hidden"/>
  </div>
</template>
