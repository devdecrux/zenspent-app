<script setup lang="ts">

</script>

<template>
  <div id="registration-form" class="flex flex-col justify-center items-center min-h-screen space-y-2">
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

          <input type="email" placeholder="john@example.com"
                 class="block w-full py-2.5 text-gray-700 placeholder-gray-400/70 bg-white border border-gray-200 rounded-lg pl-11 pr-5 rtl:pr-11 rtl:pl-5 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-600 focus:border-blue-400 dark:focus:border-blue-300 focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40">
        </div>
      </div>
    </div>
    <div id="username-input">
      <div>
        <label for="email" class="block text-sm text-gray-500 dark:text-gray-300">Username</label>

        <div class="relative flex items-center mt-2">
        <span class="absolute">
          <svg xmlns="http://www.w3.org/2000/svg" height="1em" fill="currentColor" viewBox="0 0 512 512" class="w-6 h-6 mx-3 text-gray-400 dark:text-gray-500">
            <path d="M 352 128 Q 352 102 339 80 L 339 80 L 339 80 Q 326 58 304 45 Q 281 32 256 32 Q 231 32 208 45 Q 186 58 173 80 Q 160 102 160 128 Q 160 154 173 176 Q 186 198 208 211 Q 231 224 256 224 Q 281 224 304 211 Q 326 198 339 176 Q 352 154 352 128 L 352 128 Z M 128 128 Q 128 93 145 64 L 145 64 L 145 64 Q 162 35 192 17 Q 222 0 256 0 Q 290 0 320 17 Q 350 35 367 64 Q 384 93 384 128 Q 384 163 367 192 Q 350 221 320 239 Q 290 256 256 256 Q 222 256 192 239 Q 162 221 145 192 Q 128 163 128 128 L 128 128 Z M 64 480 L 448 480 L 64 480 L 448 480 Q 446 419 404 378 Q 363 338 302 336 L 210 336 L 210 336 Q 149 338 108 378 Q 66 419 64 480 L 64 480 Z M 32 482 Q 34 407 84 356 L 84 356 L 84 356 Q 135 306 210 304 L 302 304 L 302 304 Q 377 306 428 356 Q 478 407 480 482 Q 480 495 471 503 Q 463 512 450 512 L 62 512 L 62 512 Q 49 512 41 503 Q 32 495 32 482 L 32 482 Z" />
          </svg>
        </span>

          <input type="email" placeholder="user"
                 class="block w-full py-2.5 text-gray-700 placeholder-gray-400/70 bg-white border border-gray-200 rounded-lg pl-11 pr-5 rtl:pr-11 rtl:pl-5 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-600 focus:border-blue-400 dark:focus:border-blue-300 focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40">
        </div>
      </div>
    </div>

    <div id="password-input">
      <div>
        <div class="flex items-center justify-between">
          <label for="password" class="block text-sm text-gray-500 dark:text-gray-300">Password</label>
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

          <input type="password" placeholder="********"
                 class="block w-full py-2.5 text-gray-700 placeholder-gray-400/70 bg-white border border-gray-200 rounded-lg pl-5 pr-11 rtl:pr-5 rtl:pl-11 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-600 focus:border-blue-400 dark:focus:border-blue-300 focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40">
        </div>
      </div>
    </div>

    <div id="login-button">
      <a
          class="inline-block rounded-full text-slate-400 bg-slate-900 p-3 hover:bg-slate-800 focus:ring-3 focus:outline-hidden"
          href="#"
      >

        <svg
            class="size-5 shadow-sm rtl:rotate-180"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
        >
          <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M14 5l7 7m0 0l-7 7m7-7H3"
          />
        </svg>
      </a>
    </div>
  </div>
</template>
