import axios from 'axios'
import { useUserStore } from '@/stores/user.ts'

axios.interceptors.response.use(
  (response) => response,
  (error) => {
    const userStore = useUserStore()
    console.error('API error:', error)
    if (error.response.status === 401 && userStore.user) {
      console.warn('User session expired, clearing user data.')
      userStore.clearUser()
    }
    return Promise.reject(error)
  },
)
