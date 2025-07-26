<script setup lang="ts">
import { onMounted } from 'vue'

onMounted(() => {
  console.log('localStorage from dashboard: ', localStorage)
})
</script>

<template>
  <h1 class="font-bold">This is a Dashboard</h1>
</template>

<style scoped></style>
