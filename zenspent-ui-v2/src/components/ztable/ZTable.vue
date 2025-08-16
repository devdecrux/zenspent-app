<script setup lang="ts" generic="TData, TValue">
import { type ColumnDef, FlexRender, getCoreRowModel, type PaginationState, useVueTable } from '@tanstack/vue-table'
import { Button } from '@/components/ui/button'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'
import { onMounted } from 'vue'

const props = defineProps<{
  data: TData[]
  columns: ColumnDef<TData, TValue>[]
  totalItems: number
  totalPages: number
  loadDataTable: () => void
  serverPageNumber: number
  serverPageSize: number
}>()

const emit = defineEmits<{
  (event: 'paginationUpdate', pageIndex: number, pageSize: number): void
}>()

function setPagination({ pageIndex, pageSize }: PaginationState): PaginationState {
  emit('paginationUpdate', pageIndex, pageSize)

  return { pageIndex, pageSize }
}

const table = useVueTable({
  get data() {
    return props.data ?? []
    // return transactions.value?.content ?? []
  },
  get columns() {
    return props.columns
  },
  getCoreRowModel: getCoreRowModel(),
  manualPagination: true,
  get rowCount() {
    return props.totalItems ?? 0
    // return transactions.value?.totalItems ?? 0
  },
  get pageCount() {
    return props.totalPages ?? 10
    // return transactions.value?.totalPages ?? 0
  },
  state: {
    get pagination() {
      return {
        pageIndex: props.serverPageNumber,
        pageSize: props.serverPageSize
      }
    }
  },
  onPaginationChange: (updater) => {
    if (typeof updater === 'function') {
      setPagination(
        updater({
          pageIndex: props.serverPageNumber,
          pageSize: props.serverPageSize
        })
      )
    } else {
      setPagination(updater)
    }
    props.loadDataTable()
  }
})

onMounted(() => {
  props.loadDataTable()
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

<style scoped></style>
