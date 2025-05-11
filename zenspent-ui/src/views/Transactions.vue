<template>

  <div>

    <div class="m-5 overflow-x-auto rounded border border-gray-900">

      <table class="min-w-full divide-y-2 divide-gray-900">

        <thead class="ltr:text-right rtl:text-right bg-gray-900">

        <tr class="text-gray-300">
          <th class="px-3 py-2 whitespace-nowrap">Description</th>
          <th class="px-3 py-2 whitespace-nowrap">Amount</th>
          <th class="px-3 py-2 whitespace-nowrap">Category</th>
          <th class="px-3 py-2 whitespace-nowrap">Date</th>
          <th class="px-3 py-2 whitespace-nowrap">User</th>
        </tr>

        </thead>

        <tbody class="divide-y divide-gray-600 ltr:text-right rtl:text-right">

        <tr v-for="transaction in transactions" class="text-gray-300 bg-gray-800">
          <td class="px-3 py-2 whitespace-nowrap">
            {{transaction.description}}
          </td>
          <td class="px-3 py-2 whitespace-nowrap text-emerald-600" :class="transaction.type === 'WITHDRAWAL' ? 'text-red-600' : 'text-emerald-600'">
            {{transaction.amount}}BGN
          </td>
          <td class="px-3 py-2 whitespace-nowrap">
            {{transaction.category}}
          </td>
          <td class="px-3 py-2 whitespace-nowrap">
            {{transaction.date}}
          </td>
          <td class="px-3 py-2 whitespace-nowrap">
            <div class="flex justify-end items-center gap-2">
              <span class="text-gray-300">
                {{transaction.user?.firstName + " " + transaction.user?.lastName}}
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
        <a href="#" class="grid size-8 place-content-center rounded border border-gray-900 transition-colors hover:bg-gray-800 rtl:rotate-180 bg-gray-900" aria-label="Previous page">
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
        <a class="block size-8 rounded border text-center text-sm/8 font-medium border-gray-900 transition-colors hover:bg-gray-800 rtl:rotate-180 bg-gray-900">
          1
        </a>
      </li>

      <li class="block size-8 rounded border text-center text-sm/8 font-medium border-gray-900 transition-colors hover:bg-gray-800 rtl:rotate-180 bg-gray-900">
        2
      </li>

      <li>
        <a class="block size-8 rounded border text-center text-sm/8 font-medium border-gray-900 transition-colors hover:bg-gray-800 rtl:rotate-180 bg-gray-900">
          3
        </a>
      </li>

      <li>
        <a class="block size-8 rounded border text-center text-sm/8 font-medium border-gray-900 transition-colors hover:bg-gray-800 rtl:rotate-180 bg-gray-900">
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

import {onMounted, ref} from "vue";
import type {Transaction} from "@/entities/Transaction.ts";
import axios from "axios";

let isAlert = ref(false);

const transactions = ref<Transaction[]>([]);

const loadTransactions = () => {
  axios.defaults.withXSRFToken = true;
  axios.defaults.withCredentials = true;
  axios.get('/api/v1/transactions')
      .then(response => {
        if (response.status === 200) {
          transactions.value = response.data;
        }
      }).catch(error => {
    if (error.status === 400) {
      isAlert.value = true;
    }
  })
}

onMounted(() => {
  loadTransactions();
})

</script>