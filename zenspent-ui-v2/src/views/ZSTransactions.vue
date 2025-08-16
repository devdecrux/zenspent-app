<script setup lang="ts">
import axios from 'axios'
import { ref } from 'vue'
import type { PaginationResult } from '@/entities/PaginationResult.ts'
import type { Transaction } from '@/entities/Transaction.ts'
import ZTable from '@/components/ztable/ZTable.vue'
import { transactionColumns } from '@/components/ztable/transaction-columns.ts'
import { Dialog, DialogContent, DialogFooter, DialogHeader, DialogTrigger } from '@/components/ui/dialog'
import { Button } from '@/components/ui/button'
import { Select, SelectContent, SelectGroup, SelectItem, SelectLabel, SelectTrigger, SelectValue } from '@/components/ui/select'
import { Input } from '@/components/ui/input'
import { Popover, PopoverContent, PopoverTrigger } from '@/components/ui/popover'
import { cn } from '@/lib/utils.ts'
import { Calendar } from '@/components/ui/calendar'
import { type DateValue } from 'reka-ui'

import { CalendarIcon } from 'lucide-vue-next'

import { getLocalTimeZone } from '@internationalized/date'
import { useSelectMenuDataStore } from '@/stores/select_menu_data.ts'

const serverPageNumber = ref(0)
const serverPageSize = ref(10)

const date = ref<DateValue>()

const transactions = ref<PaginationResult<Transaction> | null>(null)

const loadTableData = () => {
  axios.defaults.withXSRFToken = true
  axios.defaults.withCredentials = true
  axios
    .get('/api/v1/transactions', {
      params: {
        pageNumber: serverPageNumber.value,
        pageSize: serverPageSize.value,
      },
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

function handlePaginationUpdate(pageIndex: number, pageSize: number) {
  serverPageNumber.value = pageIndex
  serverPageSize.value = pageSize
}

const df = new Intl.DateTimeFormat('bg-BG', {
  year: 'numeric',
  month: '2-digit',
  day: '2-digit',
})

const selectData = useSelectMenuDataStore()

const createTransaction = () => {
  console.log('Date selected: ', date.value?.toDate(getLocalTimeZone()))
}
</script>

<template>
  <div class="flex mb-2">
    <Dialog>
      <DialogTrigger as-child>
        <Button>Add Transaction</Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader> Create transaction</DialogHeader>
        <div class="flex flex-col lg:flex-row gap-2">
          <Select>
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Select a recipient" />
            </SelectTrigger>
            <SelectContent>
              <SelectGroup>
                <SelectLabel class="font-medium">Expense Accounts</SelectLabel>
                <SelectItem value="netflix">Netflix</SelectItem>
                <SelectItem value="car-leasing">Car leasing</SelectItem>
              </SelectGroup>
              <SelectGroup>
                <SelectLabel class="font-medium">Check Accounts</SelectLabel>
                <SelectItem value="dsk">DSK</SelectItem>
                <SelectItem value="revolut">Revolut</SelectItem>
              </SelectGroup>
            </SelectContent>
          </Select>
          <Select>
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Select a payer" />
            </SelectTrigger>
            <SelectContent>
              <SelectGroup>
                <SelectLabel class="font-medium">Expense Accounts</SelectLabel>
                <SelectItem value="netflix">Netflix</SelectItem>
                <SelectItem value="car-leasing">Car leasing</SelectItem>
              </SelectGroup>
              <SelectGroup>
                <SelectLabel class="font-medium">Check Accounts</SelectLabel>
                <SelectItem value="dsk">DSK</SelectItem>
                <SelectItem value="revolut">Revolut</SelectItem>
              </SelectGroup>
            </SelectContent>
          </Select>
        </div>
        <div class="flex flex-col lg:flex-row gap-2">
          <Input id="amount" type="text" placeholder="Amount" />
          <Select>
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Select a type" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem
                v-for="type in selectData.transactionTypes"
                :value="type.value"
                v-bind:key="type.value"
                >{{ type.label }}
              </SelectItem>
            </SelectContent>
          </Select>
        </div>
        <div class="flex flex-col lg:flex-row gap-2">
          <Select>
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Select a category" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="dining">Dining</SelectItem>
              <SelectItem value="cinema">Cinema</SelectItem>
            </SelectContent>
          </Select>
          <Popover>
            <PopoverTrigger as-child>
              <Button
                variant="outline"
                :class="
                  cn('w-1/2 justify-start text-left font-normal', !date && 'text-muted-foreground')
                "
              >
                <CalendarIcon class="mr-2 h-4 w-4" />
                {{ date ? df.format(date.toDate(getLocalTimeZone())) : 'Pick a date' }}
              </Button>
            </PopoverTrigger>
            <PopoverContent class="w-auto p-0">
              <Calendar v-model="date" initial-focus />
            </PopoverContent>
          </Popover>
        </div>
        <DialogFooter>
          <Button v-on:click="createTransaction">Save</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  </div>

  <ZTable
    :data="transactions?.content ?? []"
    :columns="transactionColumns"
    :total-items="transactions?.totalItems ?? 0"
    :total-pages="transactions?.totalPages ?? 0"
    :load-data-table="loadTableData"
    :server-page-number="serverPageNumber"
    :server-page-size="serverPageSize"
    @paginationUpdate="handlePaginationUpdate"
  />
</template>

<style scoped></style>
