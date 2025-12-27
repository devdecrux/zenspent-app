<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
import { useUserStore } from '@/stores/user.ts'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Button } from '@/components/ui/button'
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar'

const userStore = useUserStore()
const selectedFile = ref<File | null>(null)
const isUploading = ref(false)
const uploadError = ref('')

const onFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement
  selectedFile.value = target.files?.[0] ?? null
  uploadError.value = ''
}

const refreshUser = () => {
  axios.defaults.withXSRFToken = true
  axios.defaults.withCredentials = true
  return axios.get('/api/v1/user').then((response) => {
    if (response.status === 200) {
      userStore.setUser(response.data)
    }
  })
}

const uploadAvatar = () => {
  if (!selectedFile.value) {
    uploadError.value = 'Please choose a file to upload.'
    return
  }
  isUploading.value = true
  uploadError.value = ''
  const formData = new FormData()
  formData.append('avatar', selectedFile.value)
  axios.defaults.withXSRFToken = true
  axios.defaults.withCredentials = true
  axios
    .post('/api/v1/user/avatar', formData, {
      headers: { 'Content-Type': 'multipart/form-data' },
    })
    .then((response) => {
      if (response.status === 200) {
        return refreshUser()
      }
    })
    .catch(() => {
      uploadError.value = 'Failed to upload avatar. Please try again.'
    })
    .finally(() => {
      isUploading.value = false
      selectedFile.value = null
    })
}
</script>

<template>
  <div class="flex w-full justify-start px-6 py-8">
    <Card class="w-full max-w-md">
      <CardHeader>
        <CardTitle class="text-2xl">Profile</CardTitle>
        <CardDescription>Manage your personal info and avatar.</CardDescription>
      </CardHeader>
      <CardContent>
        <div class="flex items-center gap-6">
          <Avatar class="h-16 w-16 rounded-lg">
            <AvatarImage v-if="userStore.user?.avatar" :src="userStore.user.avatar" />
            <AvatarFallback class="rounded-lg border">
              {{ userStore.user?.firstName }} {{ userStore.user?.lastName }}
            </AvatarFallback>
          </Avatar>
          <div class="grid gap-1">
            <div class="text-sm font-semibold">
              {{ userStore.user?.firstName }} {{ userStore.user?.lastName }}
            </div>
            <div class="text-xs text-muted-foreground">{{ userStore.user?.email }}</div>
          </div>
        </div>

        <div class="mt-6 grid gap-4">
          <div class="grid gap-2">
            <input
              id="avatar-upload"
              class="sr-only"
              type="file"
              accept="image/*"
              @change="onFileChange"
            />
            <div class="flex items-center justify-between gap-3">
              <span class="text-xs text-muted-foreground">
                {{ selectedFile?.name || 'No file chosen' }}
              </span>
              <div class="flex items-center gap-2">
                <Button as-child size="sm" variant="secondary" class="h-7 px-3 text-xs">
                  <label for="avatar-upload">Browse</label>
                </Button>
                <Button
                  size="sm"
                  class="h-7 px-3 text-xs"
                  :disabled="isUploading"
                  v-on:click="uploadAvatar"
                >
                  {{ isUploading ? 'Uploading...' : 'Upload' }}
                </Button>
              </div>
            </div>
          </div>
          <div class="flex items-center gap-3">
            <span v-if="uploadError" class="text-sm text-red-600">{{ uploadError }}</span>
          </div>
        </div>
      </CardContent>
    </Card>
  </div>
</template>
