<script setup lang="ts"></script>

<template>
  <section class="bg-background text-foreground">
    <div class="container flex min-h-screen items-center px-6 py-12 mx-auto">
      <div class="flex max-w-sm flex-col items-center text-center mx-auto">
        <p class="rounded-full bg-secondary/60 p-3 text-sm font-medium text-foreground">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="2"
            stroke="currentColor"
            class="w-6 h-6"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M12 9v3.75m9-.75a9 9 0 11-18 0 9 9 0 0118 0zm-9 3.75h.008v.008H12v-.008z"
            />
          </svg>
        </p>
        <div class="flex flex-row text-2xl font-semibold md:text-3xl">
          <span>404 | Page not found</span>
        </div>
        <p class="mt-4 text-muted-foreground">
          The page you are looking for doesn't exist. Here are some helpful links:
        </p>

        <div class="mt-6 flex w-full shrink-0 items-center gap-x-3 sm:w-auto">
          <button
            class="flex w-1/2 items-center justify-center gap-x-2 rounded-lg border border-border bg-card px-5 py-2 text-sm text-card-foreground transition-colors duration-200 hover:bg-accent hover:text-accent-foreground sm:w-auto"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke-width="1.5"
              stroke="currentColor"
              class="w-5 h-5 rtl:rotate-180"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                d="M6.75 15.75L3 12m0 0l3.75-3.75M3 12h18"
              />
            </svg>

            <span>Go back</span>
          </button>

          <RouterLink
            to="/dashboard"
            class="w-1/2 shrink-0 rounded-lg bg-primary px-5 py-2 text-sm tracking-wide text-primary-foreground transition-colors duration-200 hover:bg-primary/90 sm:w-auto"
          >
            Take me home
          </RouterLink>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped></style>
