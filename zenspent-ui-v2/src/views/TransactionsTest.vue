<script setup lang="ts" generic="TData, TValue">
import type { ColumnDef } from '@tanstack/vue-table'
import { FlexRender, getCoreRowModel, useVueTable } from '@tanstack/vue-table'

import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '@/components/ui/table'
import { columns } from '@/views/columns.ts'

const props = defineProps<{
  columns: ColumnDef<TData, TValue>[]
  data: TData[]
}>()

const transactions = [
  {
    transactionId: 201,
    recipient: { name: 'Lucas', assetAccountId: 401 },
    type: 'Credit',
    amount: 1200n,
    category: 'Consulting',
    date: '2024-06-15',
    payer: { name: 'Tech Ltd', assetAccountId: 501 },
    user: { username: 'lucas', email: 'lucas@mail.com', firstName: 'Lucas', lastName: 'King' },
  },
  {
    transactionId: 202,
    recipient: { name: 'Mia', assetAccountId: 402 },
    type: 'Debit',
    amount: 300n,
    category: 'Dining',
    date: '2024-06-16',
    payer: { name: 'Mia', assetAccountId: 402 },
    user: { username: 'mia', email: 'mia@mail.com', firstName: 'Mia', lastName: 'Moore' },
  },
  {
    transactionId: 203,
    recipient: { name: 'Ethan', assetAccountId: 403 },
    type: 'Credit',
    amount: 450n,
    category: 'Bonus',
    date: '2024-06-17',
    payer: { name: 'HR Dept', assetAccountId: 502 },
    user: { username: 'ethan', email: 'ethan@mail.com', firstName: 'Ethan', lastName: 'Wright' },
  },
]

const table = useVueTable({
  get data() {
    return transactions
  },
  get columns() {
    return columns
  },
  getCoreRowModel: getCoreRowModel(),
})
</script>

<template>
  <div class="border rounded-md">
    <Table>
      <TableHeader>
        <TableRow v-for="headerGroup in table.getHeaderGroups()" :key="headerGroup.id">
          <TableHead v-for="header in headerGroup.headers" :key="header.id">
            <FlexRender
              v-if="!header.isPlaceholder"
              :render="header.column.columnDef.header"
              :props="header.getContext()"
            />
          </TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        <template v-if="table.getRowModel().rows?.length">
          <TableRow
            v-for="row in table.getRowModel().rows"
            :key="row.id"
            :data-state="row.getIsSelected() ? 'selected' : undefined"
          >
            <TableCell v-for="cell in row.getVisibleCells()" :key="cell.id">
              <FlexRender :render="cell.column.columnDef.cell" :props="cell.getContext()" />
            </TableCell>
          </TableRow>
        </template>
        <template v-else>
          <TableRow>
            <TableCell :colspan="columns.length" class="h-24 text-center"> No results.</TableCell>
          </TableRow>
        </template>
      </TableBody>
    </Table>
  </div>
</template>
