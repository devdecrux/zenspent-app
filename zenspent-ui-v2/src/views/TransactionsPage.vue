<script setup lang="ts">
import axios from 'axios'
import { onMounted, ref } from 'vue'
import type { PaginationResult } from '@/entities/PaginationResult.ts'
import type { Transaction } from '@/entities/Transaction.ts'
import TableComponent from '@/components/ztable/TableComponent.vue'
import { transactionColumns } from '@/components/ztable/transaction-columns.ts'
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTrigger,
} from '@/components/ui/dialog'
import { Button } from '@/components/ui/button'
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectLabel,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select'
import { Input } from '@/components/ui/input'
import { Popover, PopoverContent, PopoverTrigger } from '@/components/ui/popover'
import { cn } from '@/lib/utils.ts'
import { Calendar } from '@/components/ui/calendar'
import { type DateValue } from 'reka-ui'

import { CalendarIcon } from 'lucide-vue-next'

import { getLocalTimeZone } from '@internationalized/date'
import { useSelectMenuDataStore } from '@/stores/select_menu_data.ts'
import type { TransactionParticipant } from '@/entities/TransactionParticipant.ts'
import type { User } from '@/entities/User.ts'

const serverPageNumber = ref(0)
const serverPageSize = ref(10)

const dialogState = ref(false)

// TODO: Change date to string and use only one variable not two - transaction.date and date
const date = ref<DateValue>()

const transactions = ref<PaginationResult<Transaction> | null>(null)

const payer = ref<TransactionParticipant>({
  accountId: '',
  name: '',
})

const recipient = ref<TransactionParticipant>({
  accountId: '',
  name: '',
})

const user = ref<User>({
  username: '',
  email: '',
  firstName: '',
  lastName: '',
})

const transaction = ref<Transaction>({
  id: '',
  amount: '',
  type: '',
  date: '',
  category: '',
  recipient: recipient.value,
  payer: payer.value,
  user: user.value,
})

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

const createTransaction = () => {
  axios.defaults.withXSRFToken = true
  axios.defaults.withCredentials = true
  transaction.value.date = date.value ? date.value?.toString() : ''
  axios
    .post('/api/v1/transactions', transaction.value)
    .then((response) => {
      if (response.status === 201) {
        // TODO: only add the new transaction to the list instead of reloading all data?
        loadTableData() // Refresh the table data
        // reset the account form
        transaction.value = {
          id: '',
          amount: '',
          type: '',
          date: '',
          category: '',
          recipient: { accountId: '', name: '' },
          payer: { accountId: '', name: '' },
          user: { username: '', email: '', firstName: '', lastName: '' },
        }
        date.value = undefined
        dialogState.value = false
      }
    })
    .catch((error) => {
      console.error('Error creating account:', error)
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

const selectMenuData = useSelectMenuDataStore()

onMounted(() => {
  selectMenuData.loadAccounts()
  selectMenuData.loadTransactionTypes()
})
</script>

<template>
  <div class="flex mb-2">
    <Dialog v-model:open="dialogState">
      <DialogTrigger as-child>
        <Button>Add Transaction</Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader> Create Transaction</DialogHeader>
        <div class="flex flex-col lg:flex-row gap-2">
          <Select v-model="transaction.recipient.accountId">
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Select a recipient" />
            </SelectTrigger>
            <SelectContent>
              <SelectGroup
                v-for="[type, accounts] in Object.entries(selectMenuData.accountsGroupedByType)"
                :key="type"
              >
                <SelectLabel class="font-medium">{{ type }}</SelectLabel>
                <SelectItem v-for="account in accounts" :key="account.id" :value="account.id">
                  {{ account.name }}
                </SelectItem>
              </SelectGroup>
            </SelectContent>
          </Select>
          <Select v-model="transaction.payer.accountId">
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Select a payer" />
            </SelectTrigger>
            <SelectContent>
              <SelectGroup
                v-for="[type, accounts] in Object.entries(selectMenuData.accountsGroupedByType)"
                :key="type"
              >
                <SelectLabel class="font-medium">{{ type }}</SelectLabel>
                <SelectItem v-for="account in accounts" :key="account.id" :value="account.id">
                  {{ account.name }}
                </SelectItem>
              </SelectGroup>
            </SelectContent>
          </Select>
        </div>
        <div class="flex flex-col lg:flex-row gap-2">
          <Input id="amount" type="text" placeholder="Amount" v-model="transaction.amount" />
          <Select v-model="transaction.type">
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Select a type" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem
                v-for="type in selectMenuData.transactionTypes"
                :value="type.value"
                v-bind:key="type.value"
                >{{ type.label }}
              </SelectItem>
            </SelectContent>
          </Select>
        </div>
        <div class="flex flex-col lg:flex-row gap-2">
          <Select v-model="transaction.category">
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

  <TableComponent
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
