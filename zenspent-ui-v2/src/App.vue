<script setup lang="ts">
import { useRoute } from 'vue-router'
import { computed, onMounted } from 'vue'
import { SidebarProvider, SidebarTrigger } from '@/components/ui/sidebar'
import ZSidebar from '@/components/ZSidebar.vue'
import { loadCSRFToken } from '@/utils/LoadCSRFToken'
import { useUserStore } from '@/stores/user.ts'
import { useSelectMenuDataStore } from '@/stores/select_menu_data.ts'

const route = useRoute()
const isLoginOrRegistrationPage = computed(() => {
  return route.fullPath === '/registration' || route.fullPath === '/login'
})

const userStore = useUserStore()
const loadSelectData = useSelectMenuDataStore()

onMounted(() => {
  const appMode = import.meta.env.VITE_APP_MODE
  console.log('appMode: ' + appMode)
  console.log('sessionStorage: ', sessionStorage)
  if (appMode != null && appMode == 'development') {
    loadCSRFToken()
  }
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
