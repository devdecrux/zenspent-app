<script setup lang="ts">
import { Button } from '@/components/ui/button'
import type { Component } from 'vue'

defineProps<{
  actions: Array<{
    label: string
    icon: Component
    onClick: () => void
  }>
}>()
</script>

<template>
  <Button
    v-for="action in actions"
    :key="action.label"
    @click="action.onClick"
    variant="outline"
    size="icon"
    class="mr-1"
    :aria-label="action.label"
  >
    <component :is="action.icon" class="w-4 h-4" />
  </Button>
</template>

<style scoped></style>
