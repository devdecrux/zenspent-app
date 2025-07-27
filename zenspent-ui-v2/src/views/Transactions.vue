<script setup lang="ts">
import axios from 'axios'
import { ref } from 'vue'
import type { PaginationResult } from '@/entities/PaginationResult.ts'
import type { Transaction } from '@/entities/Transaction.ts'
import ZTable from '@/components/ZTable.vue'
import { columns } from '@/views/columns.ts'
import { DateFormatter, type DateValue } from '@internationalized/date'
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTrigger,
} from '@/components/ui/dialog'
import { Button } from '@/components/ui/button'

const date = ref<DateValue>()
let serverPageNumber = ref(0)
let serverPageSize = ref(10)

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

const df = new DateFormatter('bg-BG', {
  day: '2-digit',
  month: '2-digit',
  year: '2-digit',
})
</script>

<template>
  <div class="flex mb-2">
    <Dialog>
      <DialogTrigger as-child>
        <Button>Add Transaction</Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader> Create transaction</DialogHeader>
        <!--        <div class="flex flex-col lg:flex-row gap-2">-->
        <!--          <Select>-->
        <!--            <SelectTrigger class="w-full">-->
        <!--              <SelectValue placeholder="Select a recipient" />-->
        <!--            </SelectTrigger>-->
        <!--            <SelectContent class="pl-1 pt-1">-->
        <!--              <SelectGroup>-->
        <!--                <SelectLabel class="font-medium">Expense Accounts</SelectLabel>-->
        <!--                <SelectItem value="id1">Netflix</SelectItem>-->
        <!--                <SelectItem value="id2">Car leasing</SelectItem>-->
        <!--              </SelectGroup>-->
        <!--              <SelectGroup>-->
        <!--                <SelectLabel class="font-medium">Check Accounts</SelectLabel>-->
        <!--                <SelectItem value="new-id-1">DSK razplashatelna smetka</SelectItem>-->
        <!--                <SelectItem value="new-id-2">Revolut smetka</SelectItem>-->
        <!--              </SelectGroup>-->
        <!--            </SelectContent>-->
        <!--          </Select>-->
        <!--          <Select>-->
        <!--            <SelectTrigger class="w-full">-->
        <!--              <SelectValue placeholder="Select a payer" />-->
        <!--            </SelectTrigger>-->
        <!--            <SelectContent class="pl-1 pt-1">-->
        <!--              <SelectGroup>-->
        <!--                <SelectLabel class="font-medium">Expense Accounts</SelectLabel>-->
        <!--                <SelectItem value="id1">Netflix</SelectItem>-->
        <!--                <SelectItem value="id2">Car leasing</SelectItem>-->
        <!--              </SelectGroup>-->
        <!--              <SelectGroup>-->
        <!--                <SelectLabel class="font-medium">Check Accounts</SelectLabel>-->
        <!--                <SelectItem value="new-id-1">DSK razplashatelna smetka</SelectItem>-->
        <!--                <SelectItem value="new-id-2">Revolut smetka</SelectItem>-->
        <!--              </SelectGroup>-->
        <!--            </SelectContent>-->
        <!--          </Select>-->
        <!--        </div>-->
        <!--        <div class="flex flex-col lg:flex-row gap-2">-->
        <!--          <Input id="Amount" type="text" placeholder="Amount" />-->
        <!--          <Select>-->
        <!--            <SelectTrigger class="w-full">-->
        <!--              <SelectValue placeholder="Type" />-->
        <!--            </SelectTrigger>-->
        <!--            <SelectContent class="pl-1 pt-1">-->
        <!--              <SelectItem value="SALARY">Salary</SelectItem>-->
        <!--              <SelectItem value="WITHDRAWAL">WITHDRAWAL</SelectItem>-->
        <!--            </SelectContent>-->
        <!--          </Select>-->
        <!--        </div>-->
        <!--        <div class="flex flex-col lg:flex-row gap-2">-->
        <!--          <Popover>-->
        <!--            <PopoverTrigger as-child>-->
        <!--              <Button-->
        <!--                variant="outline"-->
        <!--                :class="-->
        <!--                  cn('w-1/2 justify-start text-left font-normal', !date && 'text-muted-foreground')-->
        <!--                "-->
        <!--              >-->
        <!--                <CalendarIcon class="mr-2 h-4 w-4" />-->
        <!--                {{ date ? df.format(date.toDate(getLocalTimeZone())) : 'Pick a date' }}-->
        <!--              </Button>-->
        <!--            </PopoverTrigger>-->
        <!--            <PopoverContent class="w-auto p-0">-->
        <!--              <Calendar v-model="date" initial-focus />-->
        <!--            </PopoverContent>-->
        <!--          </Popover>-->
        <!--        </div>-->
        <DialogFooter>
          <Button>Save</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  </div>

  <ZTable
    :data="transactions?.content ?? []"
    :columns="columns"
    :total-items="transactions?.totalItems ?? 0"
    :total-pages="transactions?.totalPages ?? 0"
    :load-data-table="loadTableData"
    :server-page-number="serverPageNumber"
    :server-page-size="serverPageSize"
    @paginationUpdate="handlePaginationUpdate"
  />
</template>

<style scoped></style>
