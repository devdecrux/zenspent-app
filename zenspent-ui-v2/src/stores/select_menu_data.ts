import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { TransactionType } from '@/entities/TransactionType.ts'
import axios from 'axios'
import type { AssetAccountType } from '@/entities/AssetAccountType.ts'

export const useSelectMenuDataStore = defineStore('select_menu_data', () => {
  const transactionTypes = ref<TransactionType[]>([])
  const assetAccountTypes = ref<AssetAccountType[]>([])

  function loadTransactionTypes() {
    console.log('Transaction types: ', transactionTypes.value)
    if (transactionTypes.value.length > 0) {
      return
    }
    axios.defaults.withXSRFToken = true
    axios.defaults.withCredentials = true
    axios
      .get('/api/v1/transactions/types')
      .then((response) => {
        if (response.status === 200) {
          transactionTypes.value = response.data
        }
      })
      .catch((error) => {
        console.error('Error loading transaction types:', error)
      })
  }

  function loadAssetAccountTypes() {
    console.log('Asset Account types: ', assetAccountTypes.value)
    if (transactionTypes.value.length > 0) {
      return
    }
    axios.defaults.withXSRFToken = true
    axios.defaults.withCredentials = true
    axios
      .get('/api/v1/asset-accounts/types')
      .then((response) => {
        if (response.status === 200) {
          assetAccountTypes.value = response.data
        }
      })
      .catch((error) => {
        console.error('Error loading asset account types:', error)
      })
  }

  function clearLoadedData() {
    transactionTypes.value = []
    assetAccountTypes.value = []
  }

  return {
    transactionTypes,
    assetAccountTypes,
    loadTransactionTypes,
    loadAssetAccountTypes,
    clearLoadedData
  }
})
