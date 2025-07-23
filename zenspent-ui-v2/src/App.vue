<script setup lang="ts">
import { useRoute } from 'vue-router'
import { computed, onMounted } from 'vue'
import { SidebarProvider, SidebarTrigger } from '@/components/ui/sidebar'
import ZSidebar from '@/components/ZSidebar.vue'
import axios from 'axios'

const route = useRoute()
const isLoginOrRegistrationPage = computed(() => {
  return route.fullPath === '/registration' || route.fullPath === '/login'
})

onMounted(() => {
  const appMode = import.meta.env.VITE_APP_MODE
  console.log('appMode: ' + appMode)
  if (appMode != null && appMode == 'development') {
    loadCSRFToken()
  }
})

const loadCSRFToken = () => {
  axios.defaults.withXSRFToken = true
  axios.defaults.withCredentials = true
  axios.get('/api/v1/internal/csrf-token')
}
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
