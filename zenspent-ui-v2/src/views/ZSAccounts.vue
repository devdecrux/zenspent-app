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
import ZTable from '@/components/ztable/ZTable.vue'
import { ref } from 'vue'
import type { PaginationResult } from '@/entities/PaginationResult.ts'
import type { AssetAccount } from '@/entities/AssetAccount.ts'
import axios from 'axios'
import { assetAccountColumns } from '@/components/ztable/asset-account-columns.ts'

const selectData = useSelectMenuDataStore()

const serverPageNumber = ref(0)
const serverPageSize = ref(10)

const assetAccounts = ref<PaginationResult<AssetAccount> | null>(null)

function handlePaginationUpdate(pageIndex: number, pageSize: number) {
  serverPageNumber.value = pageIndex
  serverPageSize.value = pageSize
}

const loadTableData = () => {
  axios.defaults.withXSRFToken = true
  axios.defaults.withCredentials = true
  axios
    .get('/api/v1/asset-accounts', {
      params: {
        pageNumber: serverPageNumber.value,
        pageSize: serverPageSize.value,
      },
    })
    .then((response) => {
      if (response.status === 200) {
        assetAccounts.value = response.data
      }
    })
    .catch((error) => {
      console.error('Error loading asset accounts:', error)
    })
}
</script>

<template>
  <div class="flex mb-2">
    <Dialog>
      <DialogTrigger as-child>
        <Button>Add Asset Account</Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader> Create Asset Account</DialogHeader>
        <div class="flex flex-col lg:flex-row gap-2">
          <Input id="name" type="text" placeholder="Name" />
          <Input id="description" type="text" placeholder="Description" />
        </div>
        <div class="flex flex-col lg:flex-row gap-2">
          <Input id="balance" type="text" placeholder="Balance" />
          <Select>
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Select a type" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem
                v-for="type in selectData.assetAccountTypes"
                :value="type.value"
                v-bind:key="type.value"
                >{{ type.label }}
              </SelectItem>
            </SelectContent>
          </Select>
        </div>
        <DialogFooter>
          <Button>Save</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  </div>

  <ZTable
    :data="assetAccounts?.content ?? []"
    :columns="assetAccountColumns"
    :total-items="assetAccounts?.totalItems ?? 0"
    :total-pages="assetAccounts?.totalPages ?? 0"
    :load-data-table="loadTableData"
    :server-page-number="serverPageNumber"
    :server-page-size="serverPageSize"
    @paginationUpdate="handlePaginationUpdate"
  />
</template>
