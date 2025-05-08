<script setup lang="ts">
import {RouterView, useRoute} from 'vue-router'
import SideBar from '@/components/SideBar.vue'
import NavBar from "@/components/NavBar.vue";
import {computed} from "vue";

const route = useRoute()
const isLoginOrRegistrationPage = computed(() => {
  return route.fullPath === '/registration' || route.fullPath === '/login';
});
</script>

<template>
  <NavBar v-if="!isLoginOrRegistrationPage"/>
  <div class="lg:flex overflow-hidden">
    <SideBar v-if="!isLoginOrRegistrationPage"/>
    <RouterView class="bg-slate-700 grow h-screen overflow-y-hidden lg:overflow-x-hidden" />
  </div>
</template>
