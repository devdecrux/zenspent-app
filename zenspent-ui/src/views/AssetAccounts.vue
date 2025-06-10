<script setup lang="ts">

import { useRoute } from 'vue-router'
</script>

<template>
  {{ useRoute().params.assetAccountId }}
</template>