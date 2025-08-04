import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { TransactionType } from '@/entities/TransactionType.ts'
import axios from 'axios'

export const useSelectMenuDataStore = defineStore('select_menu_data', () => {
  const transactionTypes = ref<TransactionType[]>([])

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

  function clearLoadedData() {
    transactionTypes.value = []
  }

  return { transactionTypes, loadTransactionTypes, clearLoadedData }
})
