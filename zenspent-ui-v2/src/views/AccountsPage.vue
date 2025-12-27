<script setup lang="ts">
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select'
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTrigger,
} from '@/components/ui/dialog'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { useSelectMenuDataStore } from '@/stores/select_menu_data.ts'
import TableComponent from '@/components/ztable/TableComponent.vue'
import { onMounted, ref } from 'vue'
import type { PaginationResult } from '@/entities/PaginationResult.ts'
import type { Account } from '@/entities/Account.ts'
import axios from 'axios'
import { accountColumns } from '@/components/ztable/account-columns.ts'

const selectMenuData = useSelectMenuDataStore()

const dialogState = ref(false)

const serverPageNumber = ref(0)
const serverPageSize = ref(10)

const accounts = ref<PaginationResult<Account> | null>(null)
const account = ref<Account>({
  id: '',
  name: '',
  balance: '',
  type: '',
  description: '',
})

const loadTableData = () => {
  axios.defaults.withXSRFToken = true
  axios.defaults.withCredentials = true
  axios
    .get('/api/v1/accounts', {
      params: {
        pageNumber: serverPageNumber.value,
        pageSize: serverPageSize.value,
      },
    })
    .then((response) => {
      if (response.status === 200) {
        accounts.value = response.data
      }
    })
    .catch((error) => {
      console.error('Error loading accounts:', error)
    })
}

const createAccount = () => {
  axios.defaults.withXSRFToken = true
  axios.defaults.withCredentials = true
  axios
    .post('/api/v1/accounts', account.value)
    .then((response) => {
      if (response.status === 201) {
        // TODO: only add the new account to the list instead of reloading all data?
        loadTableData() // Refresh the table data
        // reset the account form
        account.value = { id: '', name: '', balance: '', type: '', description: '' }
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

onMounted(() => {
  selectMenuData.loadAccountTypes()
})
</script>

<template>
  <div class="flex mb-2">
    <Dialog v-model:open="dialogState">
      <DialogTrigger as-child>
        <Button>Add Account</Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader> Create Account</DialogHeader>
        <div class="flex flex-col lg:flex-row gap-2">
          <Input id="name" type="text" placeholder="Name" v-model="account.name" />
          <Input
            id="description"
            type="text"
            placeholder="Description"
            v-model="account.description"
          />
        </div>
        <div class="flex flex-col lg:flex-row gap-2">
          <Input id="balance" type="text" placeholder="Balance" v-model="account.balance" />
          <Select v-model="account.type">
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Select a type" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem
                v-for="type in selectMenuData.accountTypes"
                :value="type.value"
                v-bind:key="type.value"
                >{{ type.label }}
              </SelectItem>
            </SelectContent>
          </Select>
        </div>
        <DialogFooter>
          <Button v-on:click="createAccount">Save</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  </div>

  <TableComponent
    :data="accounts?.content ?? []"
    :columns="accountColumns"
    :total-items="accounts?.totalItems ?? 0"
    :total-pages="accounts?.totalPages ?? 0"
    :load-data-table="loadTableData"
    :server-page-number="serverPageNumber"
    :server-page-size="serverPageSize"
    @paginationUpdate="handlePaginationUpdate"
  />
</template>
