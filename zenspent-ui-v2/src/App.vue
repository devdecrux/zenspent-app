<script setup lang="ts">
import { RouterView } from 'vue-router'
import { SidebarProvider, SidebarTrigger } from '@/components/ui/sidebar'
import ZSidebar from '@/components/ZSidebar.vue'
</script>

<template>
  <SidebarProvider>
    <ZSidebar />
    <div class="m-2">
      <SidebarTrigger />
      <RouterView />
    </div>
  </SidebarProvider>
</template>
