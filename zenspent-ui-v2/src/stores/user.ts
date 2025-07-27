import { defineStore } from 'pinia'
import type { User } from '@/entities/User.ts'
import { ref } from 'vue'

export const useUserStore = defineStore(
  'user',
  () => {
    const user = ref<User | null>(null)

    function setUser(userData: User) {
      user.value = userData
    }

    function clearUser() {
      user.value = null
      sessionStorage.removeItem('user')
      // router.push('/login')
    }

    return { user, setUser, clearUser }
  },
  {
    persist: {
      storage: sessionStorage,
      // afterHydrate: (context) => {
      //   axios.defaults.withXSRFToken = true
      //   axios.defaults.withCredentials = true
      //   axios
      //     .get('/api/v1/user')
      //     .then((response) => {
      //       if (response.status >= 300) {
      //         context.store.clearUser()
      //       }
      //     })
      //     .catch(() => {
      //       context.store.clearUser()
      //     })
      // },
    },
  },
)
