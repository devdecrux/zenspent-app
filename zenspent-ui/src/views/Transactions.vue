<template>
  <div>
    <add-transaction-modal
      v-if="isCreateTransactionModalOpen"
      @close-create-transaction-modal="isCreateTransactionModalOpen = false"
    ></add-transaction-modal>

    <div class="m-5 flex justify-end">
      <button
        v-on:click="isCreateTransactionModalOpen = !isCreateTransactionModalOpen"
        class="px-4 py-2 tracking-wide text-gray-200 capitalize transition-colors duration-300 transform bg-emerald-900 border border-emerald-950 rounded-lg hover:bg-emerald-800"
      >
        Add Transaction
      </button>
    </div>

    <div class="m-5 overflow-x-auto rounded border border-gray-900">
      <table class="min-w-full divide-y-2 divide-gray-900">
        <thead class="ltr:text-right rtl:text-right bg-gray-900">
          <tr class="text-gray-300">
            <th class="px-3 py-2 whitespace-nowrap">Recipient</th>
            <th class="px-3 py-2 whitespace-nowrap">Payer</th>
            <th class="px-3 py-2 whitespace-nowrap">Amount</th>
            <th class="px-3 py-2 whitespace-nowrap">Category</th>
            <th class="px-3 py-2 whitespace-nowrap">Date</th>
            <th class="px-3 py-2 whitespace-nowrap">User</th>
          </tr>
        </thead>

        <tbody class="divide-y divide-gray-600 ltr:text-right rtl:text-right">
          <tr v-for="transaction in transactions" class="text-gray-300 bg-gray-800">
            <td class="px-3 py-2 whitespace-nowrap">
              <RouterLink
                v-if="transaction.recipient?.assetAccountId"
                :to="{
                  name: 'asset-accounts',
                  params: { assetAccountId: transaction.recipient.assetAccountId },
                }"
                class="flex flex-row gap-2 items-center justify-end hover:underline hover:text-blue-500"
              >
                {{ transaction.recipient?.name }}
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  height="1em"
                  fill="currentColor"
                  viewBox="0 0 512 512"
                >
                  <path
                    d="M 304 24 Q 306 46 328 48 L 430 48 L 430 48 L 207 271 L 207 271 Q 193 288 207 305 Q 224 319 241 305 L 464 82 L 464 82 L 464 184 L 464 184 Q 466 206 488 208 Q 510 206 512 184 L 512 24 L 512 24 Q 510 2 488 0 L 328 0 L 328 0 Q 306 2 304 24 L 304 24 Z M 72 32 Q 41 33 21 53 L 21 53 L 21 53 Q 1 73 0 104 L 0 440 L 0 440 Q 1 471 21 491 Q 41 511 72 512 L 408 512 L 408 512 Q 439 511 459 491 Q 479 471 480 440 L 480 312 L 480 312 Q 478 290 456 288 Q 434 290 432 312 L 432 440 L 432 440 Q 430 462 408 464 L 72 464 L 72 464 Q 50 462 48 440 L 48 104 L 48 104 Q 50 82 72 80 L 200 80 L 200 80 Q 222 78 224 56 Q 222 34 200 32 L 72 32 L 72 32 Z"
                  />
                </svg>
              </RouterLink>
              <span v-else class="justify-end">
                {{ transaction.recipient?.name }}
              </span>
            </td>
            <td class="px-3 py-2 whitespace-nowrap">
              <RouterLink
                v-if="transaction.payer?.assetAccountId"
                :to="{
                  name: 'asset-accounts',
                  params: { assetAccountId: transaction.payer.assetAccountId },
                }"
                class="flex flex-row gap-2 items-center justify-end hover:underline hover:text-blue-500"
              >
                {{ transaction.payer?.name }}
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  height="1em"
                  fill="currentColor"
                  viewBox="0 0 512 512"
                >
                  <path
                    d="M 304 24 Q 306 46 328 48 L 430 48 L 430 48 L 207 271 L 207 271 Q 193 288 207 305 Q 224 319 241 305 L 464 82 L 464 82 L 464 184 L 464 184 Q 466 206 488 208 Q 510 206 512 184 L 512 24 L 512 24 Q 510 2 488 0 L 328 0 L 328 0 Q 306 2 304 24 L 304 24 Z M 72 32 Q 41 33 21 53 L 21 53 L 21 53 Q 1 73 0 104 L 0 440 L 0 440 Q 1 471 21 491 Q 41 511 72 512 L 408 512 L 408 512 Q 439 511 459 491 Q 479 471 480 440 L 480 312 L 480 312 Q 478 290 456 288 Q 434 290 432 312 L 432 440 L 432 440 Q 430 462 408 464 L 72 464 L 72 464 Q 50 462 48 440 L 48 104 L 48 104 Q 50 82 72 80 L 200 80 L 200 80 Q 222 78 224 56 Q 222 34 200 32 L 72 32 L 72 32 Z"
                  />
                </svg>
              </RouterLink>
              <span v-else class="justify-end">
                {{ transaction.payer?.name }}
              </span>
            </td>
            <td
              class="px-3 py-2 whitespace-nowrap justify-end"
              :class="transactionTypeColor(transaction.type)"
            >
              {{ transaction.amount }}BGN
            </td>
            <td class="px-3 py-2 whitespace-nowrap justify-end">
              {{ transaction.category }}
            </td>
            <td class="px-3 py-2 whitespace-nowrap justify-end">
              {{ transaction.date }}
            </td>
            <td class="px-3 py-2 whitespace-nowrap">
              <div class="flex justify-end items-center gap-2">
                <span class="text-gray-300">
                  {{ transaction.user?.firstName + ' ' + transaction.user?.lastName }}
                </span>
                <!--              <img-->
                <!--                  alt=""-->
                <!--                  src="https://images.unsplash.com/photo-1600486913747-55e5470d6f40?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80"-->
                <!--                  class="size-10 rounded-full object-cover"-->
                <!--              />-->
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <ul class="flex justify-center gap-1 text-gray-300">
      <li>
        <a
          href="#"
          class="grid size-8 place-content-center rounded border border-gray-900 transition-colors hover:bg-gray-800 rtl:rotate-180 bg-gray-900"
          aria-label="Previous page"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="size-4"
            viewBox="0 0 20 20"
            fill="currentColor"
          >
            <path
              fill-rule="evenodd"
              d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"
              clip-rule="evenodd"
            />
          </svg>
        </a>
      </li>

      <li>
        <a
          class="block size-8 rounded border text-center text-sm/8 font-medium border-gray-900 transition-colors hover:bg-gray-800 rtl:rotate-180 bg-gray-900"
        >
          1
        </a>
      </li>

      <li
        class="block size-8 rounded border text-center text-sm/8 font-medium border-gray-900 transition-colors hover:bg-gray-800 rtl:rotate-180 bg-gray-900"
      >
        2
      </li>

      <li>
        <a
          class="block size-8 rounded border text-center text-sm/8 font-medium border-gray-900 transition-colors hover:bg-gray-800 rtl:rotate-180 bg-gray-900"
        >
          3
        </a>
      </li>

      <li>
        <a
          class="block size-8 rounded border text-center text-sm/8 font-medium border-gray-900 transition-colors hover:bg-gray-800 rtl:rotate-180 bg-gray-900"
        >
          4
        </a>
      </li>

      <li>
        <a
          href="#"
          class="grid size-8 place-content-center rounded border border-gray-900 transition-colors hover:bg-gray-800 rtl:rotate-180 bg-gray-900"
          aria-label="Next page"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="size-4"
            viewBox="0 0 20 20"
            fill="currentColor"
          >
            <path
              fill-rule="evenodd"
              d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
              clip-rule="evenodd"
            />
          </svg>
        </a>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import type { Transaction } from '@/entities/Transaction.ts'
import axios from 'axios'
import AddTransactionModal from '@/components/AddTransactionModal.vue'

let isAlert = ref(false)
let isCreateTransactionModalOpen = ref(false)

const transactions = ref<Transaction[]>([])

const loadTransactions = () => {
  axios.defaults.withXSRFToken = true
  axios.defaults.withCredentials = true
  axios
    .get('/api/v1/transactions')
    .then((response) => {
      if (response.status === 200) {
        transactions.value = response.data
      }
    })
    .catch((error) => {
      if (error.status === 400) {
        isAlert.value = true
      }
    })
}

const transactionTypeColor = (type: string) => {
  switch (type) {
    case 'WITHDRAWAL':
      return 'text-red-600'
    case 'TRANSFER':
      return 'text-gray-600'
    default:
      return 'text-emerald-600'
  }
}

onMounted(() => {
  loadTransactions()
})
</script>
