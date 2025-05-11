<template>

  <div id="login-form" class="flex flex-col justify-center items-center min-h-screen space-y-2">
    <div id="email-input">
      <div>
        <label for="email" class="block text-sm text-gray-500 dark:text-gray-300">Email Address</label>

        <div class="relative flex items-center mt-2">
          <span class="absolute">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
                 class="w-6 h-6 mx-3 text-gray-400 dark:text-gray-500">
                <path stroke-linecap="round" stroke-linejoin="round"
                      d="M21.75 6.75v10.5a2.25 2.25 0 01-2.25 2.25h-15a2.25 2.25 0 01-2.25-2.25V6.75m19.5 0A2.25 2.25 0 0019.5 4.5h-15a2.25 2.25 0 00-2.25 2.25m19.5 0v.243a2.25 2.25 0 01-1.07 1.916l-7.5 4.615a2.25 2.25 0 01-2.36 0L3.32 8.91a2.25 2.25 0 01-1.07-1.916V6.75"/>
            </svg>
          </span>

          <input v-on:keyup.enter="login" v-model="email" type="email" placeholder="john@example.com"
                 class="block w-full py-2.5 text-gray-700 placeholder-gray-400/70 bg-white border border-gray-200 rounded-lg pl-11 pr-5 rtl:pr-11 rtl:pl-5 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-600 focus:border-blue-400 dark:focus:border-blue-300 focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40">
        </div>
      </div>
    </div>

    <div id="password-input">
      <div>
        <div class="flex items-center justify-between">
          <label for="password" class="block text-sm text-gray-500 dark:text-gray-300">Password</label>
          <a href="#" class="text-xs text-gray-600 hover:underline dark:text-gray-400">Forget Password?</a>
        </div>

        <div class="relative flex items-center mt-2">
          <button class="absolute right-0 focus:outline-none rtl:left-0 rtl:right-auto">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor"
                 class="w-6 h-6 mx-4 text-gray-400 transition-colors duration-300 dark:text-gray-500 hover:text-gray-500 dark:hover:text-gray-400">
              <path d="M12 15a3 3 0 100-6 3 3 0 000 6z"/>
              <path fill-rule="evenodd"
                    d="M1.323 11.447C2.811 6.976 7.028 3.75 12.001 3.75c4.97 0 9.185 3.223 10.675 7.69.12.362.12.752 0 1.113-1.487 4.471-5.705 7.697-10.677 7.697-4.97 0-9.186-3.223-10.675-7.69a1.762 1.762 0 010-1.113zM17.25 12a5.25 5.25 0 11-10.5 0 5.25 5.25 0 0110.5 0z"
                    clip-rule="evenodd"/>
            </svg>
          </button>

          <input v-on:keyup.enter="login" v-model="password" type="password" placeholder="********"
                 class="block w-full py-2.5 text-gray-700 placeholder-gray-400/70 bg-white border border-gray-200 rounded-lg pl-5 pr-11 rtl:pr-5 rtl:pl-11 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-600 focus:border-blue-400 dark:focus:border-blue-300 focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40">
        </div>
      </div>
    </div>

    <div id="login-button">
      <button v-on:click="login" class="inline-block rounded-full text-slate-400 bg-slate-900 p-3 hover:bg-slate-800 focus:ring-3 focus:outline-hidden">
        <svg class="size-5 shadow-sm rtl:rotate-180" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 5l7 7m0 0l-7 7m7-7H3"/>
        </svg>
      </button>
    </div>
  </div>

</template>

<script setup lang="ts">
import {ref} from 'vue'
import axios from 'axios'
import router from "@/router/index.js";
import {useUserStore} from "@/stores/user.ts";

const email = ref('')
const password = ref('')
let isAlert = ref(false);

const userStore = useUserStore();

const login = () => {
  axios.defaults.withXSRFToken = true;
  axios.defaults.withCredentials = true;
  axios.post('/login', {
    username: email.value,
    password: password.value,
  }, {
    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
  }).then(response => {
    if (response.status === 200) {
      loadUserData();
    }
  }).catch(error => {
    if (error.status === 400) {
      isAlert.value = true;
    }
  })
}

const loadUserData = () => {
  axios.defaults.withXSRFToken = true;
  axios.defaults.withCredentials = true;
  axios.get('/api/v1/user')
      .then(response => {
        if (response.status === 200) {
          userStore.setUser(response.data);
          router.push("/dashboard")
        }
      }).catch(error => {
        if (error.status === 400) {
          isAlert.value = true;
        }
  })
}
</script>