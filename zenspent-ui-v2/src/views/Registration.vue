<script setup lang="ts">
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Label } from '@/components/ui/label'
import { Input } from '@/components/ui/input'
import { Button } from '@/components/ui/button'
</script>

<template>
  <div class="flex h-screen w-full items-center justify-center">
    <Card class="mx-auto min-w-sm max-w-sm">
      <CardHeader>
        <CardTitle class="text-xl"> Sign Up</CardTitle>
        <CardDescription> Enter your information to create an account</CardDescription>
      </CardHeader>
      <CardContent>
        <div class="grid gap-4">
          <div class="grid grid-cols-2 gap-4">
            <div class="grid gap-2">
              <Label for="first-name">First name</Label>
              <Input id="first-name" placeholder="John" required />
            </div>
            <div class="grid gap-2">
              <Label for="last-name">Last name</Label>
              <Input id="last-name" placeholder="Doe" required />
            </div>
          </div>
          <div class="grid gap-2">
            <Label for="email">Email</Label>
            <Input id="email" type="email" placeholder="email@example.com" required />
          </div>
          <div class="grid gap-2">
            <Label for="password">Password</Label>
            <Input id="password" type="password" />
          </div>
          <Button type="submit" class="w-full"> Create an account</Button>
        </div>
        <div class="mt-4 text-center text-sm">
          Already have an account?
          <RouterLink to="/login" class="underline"> Sign in</RouterLink>
        </div>
      </CardContent>
    </Card>
  </div>
</template>

<style scoped></style>
