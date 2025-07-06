<script setup lang="ts">
import { RouterView, useRoute } from 'vue-router'
import SideBar from '@/components/SideBar.vue'
import NavBar from '@/components/NavBar.vue'
import { computed, onMounted } from 'vue'
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
  <NavBar v-if="!isLoginOrRegistrationPage" />
  <div class="lg:flex overflow-hidden">
    <SideBar v-if="!isLoginOrRegistrationPage" />
    <RouterView class="bg-slate-700 grow h-screen overflow-y-hidden lg:overflow-x-hidden" />
  </div>
</template>
