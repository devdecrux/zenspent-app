import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { TransactionType } from '@/entities/TransactionType.ts'
import axios from 'axios'
import type { AccountType } from '@/entities/AccountType.ts'

export const useSelectMenuDataStore = defineStore('select_menu_data', () => {
  const transactionTypes = ref<TransactionType[]>([])
  const accountTypes = ref<AccountType[]>([])

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

  function loadAccountTypes() {
    console.log('Account types: ', accountTypes.value)
    if (transactionTypes.value.length > 0) {
      return
    }
    axios.defaults.withXSRFToken = true
    axios.defaults.withCredentials = true
    axios
      .get('/api/v1/accounts/types')
      .then((response) => {
        if (response.status === 200) {
          accountTypes.value = response.data
        }
      })
      .catch((error) => {
        console.error('Error loading account types:', error)
      })
  }

  function clearLoadedData() {
    transactionTypes.value = []
    accountTypes.value = []
  }

  return {
    transactionTypes,
    accountTypes: accountTypes,
    loadTransactionTypes,
    loadAccountTypes: loadAccountTypes,
    clearLoadedData,
  }
})
