import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { TransactionType } from '@/entities/TransactionType.ts'
import axios from 'axios'
import type { AccountType } from '@/entities/AccountType.ts'
import type { Account } from '@/entities/Account.ts'

export const useSelectMenuDataStore = defineStore('select_menu_data', () => {
  const transactionTypes = ref<TransactionType[]>([])
  const accountTypes = ref<AccountType[]>([])
  const accounts = ref<Account[]>([])
  const accountsGroupedByType = ref<Record<string, Account[]>>({})

  function loadTransactionTypes() {
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
    accounts.value = []
  }

  function loadAccounts() {
    axios.defaults.withXSRFToken = true
    axios.defaults.withCredentials = true
    axios
      .get('/api/v1/accounts')
      .then((response) => {
        if (response.status === 200) {
          accounts.value = response.data.content
          console.log('Accounts from backend', accounts.value)
          groupAccountsByType(accounts.value)
        }
      })
      .catch((error) => {
        console.error('Error loading accounts:', error)
      })
  }

  function groupAccountsByType(accountsList: Account[]) {
    const grouped: Record<string, Account[]> = {}
    accountTypes.value.forEach((type) => {
      const filtered = accountsList.filter((account: Account) => type.value === account.type)
      if (filtered.length > 0) {
        grouped[type.value] = filtered
      }
    })
    accountsGroupedByType.value = grouped
  }

  return {
    transactionTypes,
    accountTypes,
    accounts,
    accountsGroupedByType,
    loadTransactionTypes,
    loadAccountTypes,
    loadAccounts,
    clearLoadedData,
  }
})
