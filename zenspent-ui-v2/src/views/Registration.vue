<script setup lang="ts">
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Label } from '@/components/ui/label'
import { Input } from '@/components/ui/input'
import { Button } from '@/components/ui/button'
import { ref } from 'vue'
import axios from 'axios'
import router from '@/router'

const firstName = ref('')
const lastName = ref('')
const password = ref('')
const email = ref('')
let isAlert = ref(false)

const register = () => {
  axios.defaults.withXSRFToken = true
  axios.defaults.withCredentials = true
  axios
    .post(
      '/api/v1/user/register',
      {
        password: password.value,
        email: email.value,
        firstName: firstName.value,
        lastName: lastName.value,
      },
      {
        headers: { 'Content-Type': 'application/json' },
      },
    )
    .then((response) => {
      if (response.status === 200) {
        router.push('/login')
      }
    })
    .catch((error) => {
      if (error.status === 400 || error.status === 500) {
        isAlert.value = true
      }
    })
}
</script>

<template>
  <div class="flex h-screen w-full items-center justify-center">
    <Card class="mx-auto min-w-sm max-w-sm">
      <CardHeader>
        <CardTitle class="text-xl"> Sign Up</CardTitle>
        <CardDescription> Enter your information to create an account</CardDescription>
      </CardHeader>
      <CardContent>
        <form @submit.prevent="register">
          <div class="grid gap-4">
            <div class="grid grid-cols-2 gap-4">
              <div class="grid gap-2">
                <Label for="first-name">First name</Label>
                <Input v-model="firstName" id="first-name" placeholder="John" required />
              </div>
              <div class="grid gap-2">
                <Label for="last-name">Last name</Label>
                <Input v-model="lastName" id="last-name" placeholder="Doe" required />
              </div>
            </div>
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
              <Label for="password">Password</Label>
              <Input v-model="password" id="password" type="password" />
            </div>
            <Button type="submit" class="w-full"> Create an account</Button>
          </div>
        </form>
        <div class="mt-4 text-center text-sm">
          Already have an account?
          <RouterLink to="/login" class="underline"> Sign in</RouterLink>
        </div>
      </CardContent>
    </Card>
  </div>
</template>

<style scoped></style>
