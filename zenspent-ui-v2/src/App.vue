<script setup lang="ts">
import { useRoute } from 'vue-router'
import { computed, onMounted } from 'vue'
import { SidebarProvider, SidebarTrigger } from '@/components/ui/sidebar'
import SidebarComponent from '@/components/SidebarComponent.vue'
import { loadCSRFToken } from '@/utils/LoadCSRFToken'

const route = useRoute()
const isLoginOrRegistrationPage = computed(() => {
  return route.fullPath === '/registration' || route.fullPath === '/login'
})

onMounted(() => {
  loadCSRFToken()
})
</script>

<template>
  <SidebarProvider v-if="!isLoginOrRegistrationPage">
    <SidebarComponent />
    <div class="flex flex-col flex-1">
      <SidebarTrigger class="lg:hidden" />
      <div class="p-2">
        <RouterView />
      </div>
    </div>
  </SidebarProvider>
  <RouterView v-else />
</template>
