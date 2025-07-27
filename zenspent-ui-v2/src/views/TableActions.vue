<script setup lang="ts">
import { Edit, Trash2 } from 'lucide-vue-next'
import { Button } from '@/components/ui/button'
import type { Transaction } from '@/entities/Transaction.ts'

defineProps<{
  transaction: Transaction
}>()

const deleteItem = (id: number) => {
  // Logic to delete the row
  console.log('Deleting row with ID:', id)
}

const editItem = (transaction: Transaction) => {
  // Logic to delete the row
  console.log('Editing row with ID:', transaction)
}
</script>

<template>
  <Button v-on:click="editItem(transaction)" variant="outline" size="icon" class="mr-1">
    <Edit class="w-4 h-4" />
  </Button>
  <Button v-on:click="deleteItem(transaction.id)" variant="outline" size="icon">
    <Trash2 class="w-4 h-4" />
  </Button>
</template>

<style scoped></style>
