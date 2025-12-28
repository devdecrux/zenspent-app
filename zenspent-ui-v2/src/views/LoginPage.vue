<script setup lang="ts">
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Button } from '@/components/ui/button'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu'
import { Moon, Sun } from 'lucide-vue-next'
import { Label } from '@/components/ui/label'
import { Input } from '@/components/ui/input'
import { ref } from 'vue'
import { useUserStore } from '@/stores/user.ts'
import axios from 'axios'
import router from '@/router'
import { useColorMode } from '@vueuse/core'
import ThemeMenu from '@/components/ThemeMenu.vue'

const email = ref('')
const password = ref('')
const isAlert = ref(false)

const userStore = useUserStore()
useColorMode()

const login = () => {
  axios.defaults.withXSRFToken = true
  axios.defaults.withCredentials = true
  axios
    .post(
      '/api/v1/user/login',
      {
        username: email.value,
        password: password.value,
      },
      {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      },
    )
    .then((response) => {
      if (response.status === 200) {
        loadUserData()
      }
    })
    .catch((error) => {
      if (error.status === 400) {
        isAlert.value = true
      }
    })
}

const loadUserData = () => {
  axios.defaults.withXSRFToken = true
  axios.defaults.withCredentials = true
  axios
    .get('/api/v1/user')
    .then((response) => {
      if (response.status === 200) {
        userStore.setUser(response.data)
        router.push('/dashboard')
      }
    })
    .catch((error) => {
      if (error.status === 400) {
        isAlert.value = true
      }
    })
}
</script>

<template>
  <div
    class="relative flex h-screen w-full items-center justify-center bg-background text-foreground"
  >
    <div class="absolute top-6 right-6">
      <DropdownMenu>
        <DropdownMenuTrigger as-child>
          <Button variant="outline" size="icon" aria-label="Theme">
            <Sun class="h-4 w-4 rotate-0 scale-100 transition-all dark:-rotate-90 dark:scale-0" />
            <Moon
              class="absolute h-4 w-4 rotate-90 scale-0 transition-all dark:rotate-0 dark:scale-100"
            />
          </Button>
        </DropdownMenuTrigger>
        <DropdownMenuContent align="end">
          <ThemeMenu />
        </DropdownMenuContent>
      </DropdownMenu>
    </div>
    <Card
      class="min-w-sm max-w-sm border-[#9ccfad] bg-[#a8e0b7] text-[#2f463a] shadow-[0_10px_30px_rgba(0,0,0,0.18)] dark:border-border dark:bg-card dark:text-card-foreground"
    >
      <CardHeader>
        <CardTitle class="text-2xl text-[#1f3a2f] dark:text-card-foreground"> Login</CardTitle>
        <CardDescription class="text-[#355043] dark:text-muted-foreground">
          Enter your email below to login to your account
        </CardDescription>
      </CardHeader>
      <CardContent>
        <form @submit.prevent="login">
          <div class="grid gap-4">
            <div class="grid gap-2">
              <Label class="text-[#1f3a2f] dark:text-foreground" for="email">Email</Label>
              <Input
                v-model="email"
                id="email"
                type="email"
                placeholder="email@example.com"
                class="border-[#7fbf9a] focus-visible:border-[#6fb08a] focus-visible:ring-[#6fb08a]/40 dark:border-border dark:focus-visible:ring-ring/50"
                required
              />
            </div>
            <div class="grid gap-2">
              <div class="flex items-center">
                <Label class="text-[#1f3a2f] dark:text-foreground" for="password">Password</Label>
                <a
                  href="#"
                  class="ml-auto inline-block text-sm text-[#3f7f5c] underline dark:text-primary"
                >
                  Forgot your password?
                </a>
              </div>
              <Input
                v-model="password"
                id="password"
                type="password"
                class="border-[#7fbf9a] focus-visible:border-[#6fb08a] focus-visible:ring-[#6fb08a]/40 dark:border-border dark:focus-visible:ring-ring/50"
                required
              />
            </div>
            <Button class="w-full hover:bg-[#bee4c7] dark:hover:bg-primary/90">Login</Button>
          </div>
        </form>
        <div class="mt-4 text-center text-sm">
          Don't have an account?
          <RouterLink to="/registration" class="text-[#3f7f5c] underline dark:text-primary">
            Sign up
          </RouterLink>
        </div>
      </CardContent>
    </Card>
  </div>
</template>

<style scoped></style>
