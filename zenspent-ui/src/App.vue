<script setup lang="ts">
import {RouterView} from 'vue-router'
import SideBar from "@/components/SideBar.vue";
import NavBar from "@/components/NavBar.vue";
</script>

<template>
  <NavBar />
  <SideBar />
  <RouterView />
</template>
