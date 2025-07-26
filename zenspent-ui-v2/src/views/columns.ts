import { h } from 'vue'
import type { ColumnDef } from '@tanstack/vue-table'
import type { Transaction } from '@/entities/Transaction.ts'

export const columns: ColumnDef<Transaction>[] = [
  {
    accessorKey: 'category',
    header: () => h('div', { class: 'text-right' }, 'Category'),
    cell: ({ row }) => {
      const category = row.getValue('category') as string
      return h('div', { class: 'text-right font-bold' }, category)
    },
  },
  {
    accessorKey: 'amount',
    header: () => h('div', { class: 'text-right' }, 'Amount'),
    cell: ({ row }) => {
      const amount = row.getValue('amount') as bigint
      const formattedAmount = new Intl.NumberFormat('bg-BG', {
        style: 'currency',
        currency: 'BGN',
      }).format(amount)
      return h('div', { class: 'text-right font-bold' }, formattedAmount)
    },
  },
]
