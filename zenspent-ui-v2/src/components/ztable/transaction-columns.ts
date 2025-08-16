import { h } from 'vue'
import type { ColumnDef } from '@tanstack/vue-table'
import type { Transaction } from '@/entities/Transaction.ts'
import TableActions from '@/views/TableActions.vue'
import type { TransactionParticipant } from '@/entities/TransactionParticipant.ts'
import type { User } from '@/entities/User.ts'
import { Edit, Trash2 } from 'lucide-vue-next'

export const transactionColumns: ColumnDef<Transaction>[] = [
  {
    accessorKey: 'id',
    header: () => h('div', { class: 'text-right' }, 'Id'),
    cell: ({ row }) => {
      const id = row.getValue('id') as number
      return h('div', { class: 'text-right font-bold' }, id)
    }
  },
  {
    accessorKey: 'recipient',
    header: () => h('div', { class: 'text-right' }, 'Recipient'),
    cell: ({ row }) => {
      const recipient = row.getValue('recipient') as TransactionParticipant
      return h('div', { class: 'text-right font-bold' }, recipient.name)
    }
  },
  {
    accessorKey: 'payer',
    header: () => h('div', { class: 'text-right' }, 'Payer'),
    cell: ({ row }) => {
      const payer = row.getValue('payer') as TransactionParticipant
      return h('div', { class: 'text-right font-bold' }, payer.name)
    }
  },
  {
    accessorKey: 'amount',
    header: () => h('div', { class: 'text-right' }, 'Amount'),
    cell: ({ row }) => {
      const amount = row.getValue('amount') as bigint
      const formattedAmount = new Intl.NumberFormat('bg-BG', {
        style: 'currency',
        currency: 'BGN'
      }).format(amount)
      return h('div', { class: 'text-right font-bold' }, formattedAmount)
    }
  },
  {
    accessorKey: 'category',
    header: () => h('div', { class: 'text-right' }, 'Category'),
    cell: ({ row }) => {
      const category = row.getValue('category') as string
      return h('div', { class: 'text-right font-bold' }, category)
    }
  },
  {
    accessorKey: 'date',
    header: () => h('div', { class: 'text-right' }, 'Date'),
    cell: ({ row }) => {
      const date = row.getValue('date') as string
      const formattedDate = new Intl.DateTimeFormat('bg-BG', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
      }).format(new Date(date))
      return h('div', { class: 'text-right font-bold' }, formattedDate)
    }
  },
  {
    accessorKey: 'user',
    header: () => h('div', { class: 'text-right' }, 'User'),
    cell: ({ row }) => {
      const user = row.getValue('user') as User
      return h('div', { class: 'text-right font-bold' }, user.firstName + ' ' + user.lastName)
    }
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
          actions: tableActions(transaction)
        })
      )
    }
  }
]

const tableActions = (rowData: Transaction) => [
  {
    label: 'Edit',
    icon: Edit,
    onClick: () => editItem(rowData as Transaction)
  },
  {
    label: 'Delete',
    icon: Trash2,
    onClick: () => deleteItem(rowData.id)
  }
]

function deleteItem(id: number) {
  // TODO: Logic to delete the row
  console.log('Deleting row with ID:', id)
}

function editItem(transaction: Transaction) {
  // TODO: Logic to edit the item
  console.log('Editing row:', transaction)
}
