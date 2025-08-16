<script setup lang="ts" generic="TData, TValue">
import { FlexRender, getCoreRowModel, type PaginationState, useVueTable } from '@tanstack/vue-table'

import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'
import { transactionColumns } from '@/components/ztable/transaction-columns.ts'
import axios from 'axios'
import { onMounted, ref } from 'vue'
import type { PaginationResult } from '@/entities/PaginationResult.ts'
import type { Transaction } from '@/entities/Transaction.ts'
import { Button } from '@/components/ui/button'

const transactions = ref<PaginationResult<Transaction> | null>(null)
const serverPageNumber = ref(0)
const serverPageSize = ref(10)

function setPagination({ pageIndex, pageSize }: PaginationState): PaginationState {
  serverPageNumber.value = pageIndex
  serverPageSize.value = pageSize

  return { pageIndex, pageSize }
}

const table = useVueTable({
  get data() {
    return transactions.value?.content ?? []
  },
  get columns() {
    return transactionColumns
  },
  getCoreRowModel: getCoreRowModel(),
  manualPagination: true,
  get rowCount() {
    return transactions.value?.totalItems ?? 0
  },
  get pageCount() {
    return transactions.value?.totalPages ?? 0
  },
  state: {
    get pagination() {
      return {
        pageIndex: serverPageNumber.value,
        pageSize: serverPageSize.value
      }
    }
  },
  onPaginationChange: (updater) => {
    if (typeof updater === 'function') {
      setPagination(
        updater({
          pageIndex: serverPageNumber.value,
          pageSize: serverPageSize.value
        })
      )
    } else {
      setPagination(updater)
    }
    loadTransactions()
  }
})

const loadTransactions = () => {
  axios.defaults.withXSRFToken = true
  axios.defaults.withCredentials = true
  axios
    .get('/api/v1/transactions', {
      params: {
        pageNumber: serverPageNumber.value,
        pageSize: serverPageSize.value
      }
    })
    .then((response) => {
      if (response.status === 200) {
        transactions.value = response.data
      }
    })
    .catch((error) => {
      console.error('Error loading transactions:', error)
    })
}
onMounted(() => {
  loadTransactions()
  console.log('table.getCanPreviousPage(): ' + table.getCanPreviousPage())
  console.log('table.getCanPreviousPage(): ' + table.getCanNextPage())
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
            <TableCell :colspan="transactionColumns.length" class="h-24 text-center">
              No results.
            </TableCell
            >
          </TableRow>
        </template>
      </TableBody>
    </Table>
  </div>
  <div class="flex items-center justify-center mt-4 gap-2">
    <Button
      variant="outline"
      size="sm"
      :disabled="!table.getCanPreviousPage()"
      @click="table.previousPage()"
    >
      Previous
    </Button>
    <Button
      variant="outline"
      size="sm"
      :disabled="!table.getCanNextPage()"
      @click="table.nextPage()"
    >
      Next
    </Button>
  </div>
</template>
