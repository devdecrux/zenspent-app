<template>
  <div
    class="fixed inset-0 z-50 grid place-content-center bg-black/50 p-4"
    role="dialog"
    aria-modal="true"
    aria-labelledby="modalTitle"
  >
    <div class="w-full max-w rounded-lg bg-white p-6 shadow-lg dark:bg-gray-900">
      <div class="flex items-start justify-between">
        <h2 id="modalTitle" class="text-xl font-bold text-gray-900 sm:text-2xl dark:text-white">
          Add a transaction
        </h2>

        <button
          type="button"
          v-on:click="$emit('close-create-transaction-modal')"
          class="-me-4 -mt-4 rounded-full p-2 text-gray-400 transition-colors hover:bg-gray-50 hover:text-gray-600 focus:outline-none dark:text-gray-500 dark:hover:bg-gray-800 dark:hover:text-gray-300"
          aria-label="Close"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="size-5"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M6 18L18 6M6 6l12 12"
            />
          </svg>
        </button>
      </div>

      <div class="mt-3 flex flex-col lg:flex-row gap-4">
        <div class="flex-1">
          <label for="Recipient">
            <span class="text-sm font-medium text-gray-700 dark:text-gray-200"> Recipient </span>

            <select
              name="Recipient"
              id="Recipient"
              class="mt-0.5 w-full rounded border-gray-300 shadow-sm sm:text-sm dark:border-gray-600 dark:bg-gray-900 dark:text-white"
            >
              <option value="">Please select</option>
              <option value="JM">John Mayer</option>
              <option value="SRV">Stevie Ray Vaughn</option>
              <option value="JH">Jimi Hendrix</option>
              <option value="BBK">B.B King</option>
              <option value="AK">Albert King</option>
              <option value="BG">Buddy Guy</option>
              <option value="EC">Eric Clapton</option>
            </select>
          </label>
        </div>

        <div class="flex-1">
          <label for="Date">
            <span class="text-sm font-medium text-gray-700 dark:text-gray-200"> Date </span>

            <input
              type="date"
              id="Date"
              class="mt-0.5 w-full rounded border-gray-300 shadow-sm sm:text-sm dark:border-gray-600 dark:bg-gray-900 dark:text-white"
            />
          </label>
        </div>
      </div>

      <div class="mt-3 flex flex-col lg:flex-row gap-4">
        <div class="flex-1">
          <label for="Type">
            <span class="text-sm font-medium text-gray-700 dark:text-gray-200"> Type </span>

            <select
              id="Type"
              class="mt-0.5 w-full rounded border-gray-300 shadow-sm sm:text-sm dark:border-gray-600 dark:bg-gray-900 dark:text-white"
            >
              <option value="WITHDRAWAL">WITHDRAWAL</option>
              <option value="INCOME">INCOME</option>
              <option value="SALARY">SALARY</option>
              <option value="REFUND">REFUND</option>
              <option value="TRANSFER">TRANSFER</option>
            </select>
          </label>
        </div>

        <div class="flex-1">
          <label for="Category">
            <span class="text-sm font-medium text-gray-700 dark:text-gray-200"> Category </span>

            <select
              id="Category"
              class="mt-0.5 w-full rounded border-gray-300 shadow-sm sm:text-sm dark:border-gray-600 dark:bg-gray-900 dark:text-white"
            >
              <option value="">Please select</option>
              <option value="JM">John Mayer</option>
              <option value="SRV">Stevie Ray Vaughn</option>
              <option value="JH">Jimi Hendrix</option>
              <option value="BBK">B.B King</option>
              <option value="AK">Albert King</option>
              <option value="BG">Buddy Guy</option>
              <option value="EC">Eric Clapton</option>
            </select>
          </label>
        </div>
      </div>

      <div class="mt-3 flex flex-col lg:flex-row gap-4">
        <div class="flex-1">
          <label for="Payer">
            <span class="text-sm font-medium text-gray-700 dark:text-gray-200"> Payer </span>

            <select
              name="Payer"
              id="Payer"
              class="mt-0.5 w-full rounded border-gray-300 shadow-sm sm:text-sm dark:border-gray-600 dark:bg-gray-900 dark:text-white"
            >
              <option value="">Please select</option>
              <option value="JM">John Mayer</option>
              <option value="SRV">Stevie Ray Vaughn</option>
              <option value="JH">Jimi Hendrix</option>
              <option value="BBK">B.B King</option>
              <option value="AK">Albert King</option>
              <option value="BG">Buddy Guy</option>
              <option value="EC">Eric Clapton</option>
            </select>
          </label>
        </div>

        <div class="flex-1">
          <label for="Date">
            <span class="text-sm font-medium text-gray-700 dark:text-gray-200"> Date </span>

            <input
              type="date"
              id="Date"
              class="mt-0.5 w-full rounded border-gray-300 shadow-sm sm:text-sm dark:border-gray-600 dark:bg-gray-900 dark:text-white"
            />
          </label>
        </div>
      </div>

      <footer class="mt-6 flex justify-end gap-2">
        <button
          v-on:click="$emit('close-create-transaction-modal')"
          type="button"
          class="rounded bg-gray-100 px-4 py-2 text-sm font-medium text-gray-700 transition-colors hover:bg-gray-200 dark:bg-gray-800 dark:text-gray-200 dark:hover:bg-gray-700"
        >
          Cancel
        </button>

        <button
          v-on:click="$emit('close-create-transaction-modal')"
          class="px-4 py-2 tracking-wide text-gray-200 capitalize transition-colors duration-300 transform bg-emerald-900 border border-emerald-950 rounded-lg hover:bg-emerald-800"
        >
          Add
        </button>
      </footer>
    </div>
  </div>
</template>

<script setup lang="ts"></script>
