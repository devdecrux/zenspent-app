import { createRouter, createWebHistory } from 'vue-router'
import Dashboard from '@/views/Dashboard.vue'
import Login from '@/views/Login.vue'
import Registration from '@/views/Registration.vue'
import NotFound from '@/views/NotFound.vue'
import { useUserStore } from '@/stores/user.ts'
import Transactions from '@/views/Transactions.vue'
import AssetAccounts from '@/views/AssetAccounts.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/registration',
      name: 'registration',
      component: Registration,
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: Dashboard,
    },
    {
      path: '/transactions',
      name: 'transactions',
      component: Transactions,
    },
    {
      path: '/asset-accounts',
      name: 'asset-accounts',
      component: AssetAccounts,
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

router.beforeEach((to, from) => {
  const userStore = useUserStore()
  console.log('Authenticated user: ', userStore.user)
  if (userStore.user == null && to.name !== 'login' && to.name !== 'registration') {
    return { name: 'login' }
  }
})

export default router
