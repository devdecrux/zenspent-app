import {createRouter, createWebHistory} from 'vue-router'
import Dashboard from '@/views/Dashboard.vue'
import Transactions from '@/views/Transactions.vue'
import Subscriptions from '@/views/Subscriptions.vue'
import Settings from '@/views/Settings.vue'
import Registration from "@/views/Registration.vue";
import Login from "@/views/Login.vue";
import NotFound from "@/views/NotFound.vue";
import AssetAccounts from "@/views/AssetAccounts.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
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
      path: '/asset-accounts/:assetAccountId',
      name: 'asset-accounts',
      component: AssetAccounts,
    },
    {
      path: '/subscriptions',
      name: 'subscriptions',
      component: Subscriptions,
    },
    {
      path: '/settings',
      name: 'settings',
      component: Settings,
    },
    {
      path: '/registration',
      name: 'registration',
      component: Registration,
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/:catchAll(.*)*',
      name: 'not-found',
      component: NotFound
    }
  ],
})

export default router
