import { defineStore } from 'pinia'
import type { User } from '@/entities/User.ts'
import { ref } from 'vue'
import router from '@/router'
import { loadCSRFToken } from '@/utils/LoadCSRFToken.ts'
import { useSelectMenuDataStore } from '@/stores/select_menu_data.ts'

export const useUserStore = defineStore(
  'user',
  () => {
    const user = ref<User | null>(null)
    const isAuthenticated = ref(false)

    function setUser(userData: User) {
      user.value = userData
      isAuthenticated.value = true
    }

    function clearUser() {
      user.value = null
      isAuthenticated.value = false
      sessionStorage.removeItem('user')
      router.push('/login')
      const appMode = import.meta.env.VITE_APP_MODE
      if (appMode != null && appMode == 'development') {
        loadCSRFToken()
      }

      const loadSelectMenuDataStore = useSelectMenuDataStore()
      loadSelectMenuDataStore.clearLoadedData()
    }

    return { user, isAuthenticated, setUser, clearUser }
  },
  {
    persist: {
      storage: sessionStorage,
    },
  },
)
