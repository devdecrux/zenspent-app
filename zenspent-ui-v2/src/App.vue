<script setup lang="ts">
import { useRoute } from 'vue-router'
import { computed } from 'vue'
import { SidebarProvider, SidebarTrigger } from '@/components/ui/sidebar'
import ZSidebar from '@/components/ZSidebar.vue'

const route = useRoute()
const isLoginOrRegistrationPage = computed(() => {
  return route.fullPath === '/registration' || route.fullPath === '/login'
})
</script>

<template>
  <SidebarProvider v-if="!isLoginOrRegistrationPage">
    <ZSidebar />
    <div class="flex flex-col flex-1">
      <SidebarTrigger class="lg:hidden" />
      <div class="p-2">
        <RouterView />
      </div>
    </div>
  </SidebarProvider>
  <RouterView v-else />
</template>
