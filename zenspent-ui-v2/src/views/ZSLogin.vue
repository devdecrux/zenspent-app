<script setup lang="ts">
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Label } from '@/components/ui/label'
import { Input } from '@/components/ui/input'
import { Button } from '@/components/ui/button'
import { ref } from 'vue'
import { useUserStore } from '@/stores/user.ts'
import axios from 'axios'
import router from '@/router'
import { useSelectMenuDataStore } from '@/stores/select_menu_data.ts'

const email = ref('')
const password = ref('')
const isAlert = ref(false)

const userStore = useUserStore()
const loadSelectData = useSelectMenuDataStore()

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
        initSelectData()
        router.push('/dashboard')
      }
    })
    .catch((error) => {
      if (error.status === 400) {
        isAlert.value = true
      }
    })
}

const initSelectData = () => {
  if (userStore.isAuthenticated) {
    if (loadSelectData.transactionTypes.length === 0) {
      loadSelectData.loadTransactionTypes()
    }
    if (loadSelectData.transactionTypes.length === 0) {
      loadSelectData.loadAccountTypes()
    }
  }
}
</script>

<template>
  <div class="flex h-screen w-full items-center justify-center">
    <Card class="min-w-sm max-w-sm">
      <CardHeader>
        <CardTitle class="text-2xl"> Login</CardTitle>
        <CardDescription> Enter your email below to login to your account</CardDescription>
      </CardHeader>
      <CardContent>
        <form @submit.prevent="login">
          <div class="grid gap-4">
            <div class="grid gap-2">
              <Label for="email">Email</Label>
              <Input
                v-model="email"
                id="email"
                type="email"
                placeholder="email@example.com"
                required
              />
            </div>
            <div class="grid gap-2">
              <div class="flex items-center">
                <Label for="password">Password</Label>
                <a href="#" class="ml-auto inline-block text-sm underline">
                  Forgot your password?
                </a>
              </div>
              <Input v-model="password" id="password" type="password" required />
            </div>
            <Button class="w-full">Login</Button>
          </div>
        </form>
        <div class="mt-4 text-center text-sm">
          Don't have an account?
          <RouterLink to="/registration" class="underline"> Sign up</RouterLink>
        </div>
      </CardContent>
    </Card>
  </div>
</template>

<style scoped></style>
