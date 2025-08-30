import { createRouter, createWebHistory } from 'vue-router'
import DashboardPage from '@/views/DashboardPage.vue'
import LoginPage from '@/views/LoginPage.vue'
import RegistrationPage from '@/views/RegistrationPage.vue'
import NotFound from '@/views/NotFound.vue'
import { useUserStore } from '@/stores/user.ts'
import TransactionsPage from '@/views/TransactionsPage.vue'
import AccountsPage from '@/views/AccountsPage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: LoginPage,
    },
    {
      path: '/registration',
      name: 'registration',
      component: RegistrationPage,
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: DashboardPage,
    },
    {
      path: '/transactions',
      name: 'transactions',
      component: TransactionsPage,
    },
    {
      path: '/accounts',
      name: 'accounts',
      component: AccountsPage,
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
