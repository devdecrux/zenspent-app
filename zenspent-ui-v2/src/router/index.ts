import { createRouter, createWebHistory } from 'vue-router'
import ZsDashboard from '@/views/ZSDashboard.vue'
import ZsLogin from '@/views/ZSLogin.vue'
import ZsRegistration from '@/views/ZSRegistration.vue'
import NotFound from '@/views/NotFound.vue'
import { useUserStore } from '@/stores/user.ts'
import ZsTransactions from '@/views/ZSTransactions.vue'
import ZsAccounts from '@/views/ZSAccounts.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: ZsLogin,
    },
    {
      path: '/registration',
      name: 'registration',
      component: ZsRegistration,
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: ZsDashboard,
    },
    {
      path: '/transactions',
      name: 'transactions',
      component: ZsTransactions,
    },
    {
      path: '/accounts',
      name: 'accounts',
      component: ZsAccounts,
    },
    // {
    //   path: '/subscriptions',
    //   name: 'subscriptions',
    //   component: Subscriptions,
    // },
    // {
    //   path: '/settings',
    //   name: 'settings',
    //   component: Settings,
    // },
    {
      path: '/:catchAll(.*)*',
      name: 'not-found',
      component: NotFound,
    },
  ],
})

router.beforeEach((to) => {
  const userStore = useUserStore()
  console.log('Authenticated user: ', userStore.user)
  if (userStore.user == null && to.name !== 'login' && to.name !== 'registration') {
    return { name: 'login' }
  }
})

export default router
