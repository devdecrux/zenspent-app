import { h } from 'vue'
import type { ColumnDef } from '@tanstack/vue-table'
import ZsTableActions from '@/components/ZSTableActions.vue'
import type { Account } from '@/entities/Account.ts'
import { Edit, Trash2 } from 'lucide-vue-next'

export const accountColumns: ColumnDef<Account>[] = [
  {
    accessorKey: 'id',
    header: () => h('div', { class: 'text-right' }, 'Id'),
    cell: ({ row }) => {
      const id = row.getValue('id') as number
      return h('div', { class: 'text-right font-bold' }, id)
    },
  },
  {
    accessorKey: 'name',
    header: () => h('div', { class: 'text-right' }, 'Name'),
    cell: ({ row }) => {
      const name = row.getValue('name') as string
      return h('div', { class: 'text-right font-bold' }, name)
    },
  },
  {
    accessorKey: 'balance',
    header: () => h('div', { class: 'text-right' }, 'Balance'),
    cell: ({ row }) => {
      const balance = row.getValue('balance') as bigint
      const formattedBalance = new Intl.NumberFormat('bg-BG', {
        style: 'currency',
        currency: 'BGN',
      }).format(balance)
      return h('div', { class: 'text-right font-bold' }, formattedBalance)
    },
  },
  {
    accessorKey: 'description',
    header: () => h('div', { class: 'text-right' }, 'Description'),
    cell: ({ row }) => {
      const description = row.getValue('description') as string
      return h('div', { class: 'text-right font-bold' }, description)
    },
  },
  {
    id: 'actions',
    enableHiding: false,
    cell: ({ row }) => {
      const account = row.original
      return h(
        'div',
        { class: 'text-right' },
        h(ZsTableActions, {
          actions: tableActions(account),
        }),
      )
    },
  },
]

const tableActions = (rowData: Account) => [
  {
    label: 'Edit',
    icon: Edit,
    onClick: () => editItem(rowData as Account),
  },
  {
    label: 'Delete',
    icon: Trash2,
    onClick: () => deleteItem(rowData.id),
  },
]

function deleteItem(id: number) {
  // TODO: Logic to delete the row
  console.log('Deleting row with ID:', id)
}

function editItem(account: Account) {
  // TODO: Logic to edit the item
  console.log('Editing row:', account)
}
