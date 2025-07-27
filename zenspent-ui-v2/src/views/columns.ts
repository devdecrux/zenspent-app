import { h } from 'vue'
import type { ColumnDef } from '@tanstack/vue-table'
import type { Transaction } from '@/entities/Transaction.ts'
import TableActions from '@/views/TableActions.vue'
import type { TransactionParticipant } from '@/entities/TransactionParticipant.ts'
import type { User } from '@/entities/User.ts'

export const columns: ColumnDef<Transaction>[] = [
  {
    accessorKey: 'id',
    header: () => h('div', { class: 'text-right' }, 'Id'),
    cell: ({ row }) => {
      const id = row.getValue('id') as number
      return h('div', { class: 'text-right font-bold' }, id)
    },
  },
  {
    accessorKey: 'recipient',
    header: () => h('div', { class: 'text-right' }, 'Recipient'),
    cell: ({ row }) => {
      const recipient = row.getValue('recipient') as TransactionParticipant
      return h('div', { class: 'text-right font-bold' }, recipient.name)
    },
  },
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
  {
    accessorKey: 'date',
    header: () => h('div', { class: 'text-right' }, 'Date'),
    cell: ({ row }) => {
      const date = row.getValue('date') as string
      const formattedDate = new Intl.DateTimeFormat('bg-BG', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
      }).format(new Date(date))
      return h('div', { class: 'text-right font-bold' }, formattedDate)
    },
  },
  {
    accessorKey: 'payer',
    header: () => h('div', { class: 'text-right' }, 'Payer'),
    cell: ({ row }) => {
      const payer = row.getValue('payer') as TransactionParticipant
      return h('div', { class: 'text-right font-bold' }, payer.name)
    },
  },
  {
    accessorKey: 'user',
    header: () => h('div', { class: 'text-right' }, 'User'),
    cell: ({ row }) => {
      const user = row.getValue('user') as User
      return h('div', { class: 'text-right font-bold' }, user.firstName + ' ' + user.lastName)
    },
  },
  {
    id: 'actions',
    enableHiding: false,
    cell: ({ row }) => {
      const transaction = row.original
      return h(
        'div',
        { class: 'text-right' },
        h(TableActions, {
          transaction,
        }),
      )
    },
  },
]
