<script setup lang="ts">
import {
  Sidebar,
  SidebarContent,
  SidebarFooter,
  SidebarGroup,
  SidebarGroupContent,
  SidebarHeader,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
  type SidebarProps,
} from '@/components/ui/sidebar'
import {
  ArrowUpDown,
  ChevronsUpDown,
  Home,
  LogOut,
  Palette,
  RotateCw,
  Settings,
  User,
  WalletMinimal,
} from 'lucide-vue-next'
import { Separator } from '@/components/ui/separator'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuGroup,
  DropdownMenuItem,
  DropdownMenuSeparator,
  DropdownMenuSub,
  DropdownMenuSubContent,
  DropdownMenuSubTrigger,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu'
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar'
import ThemeMenu from '@/components/ThemeMenu.vue'
import { useUserStore } from '@/stores/user.ts'
import axios from 'axios'
import router from '@/router'
import { initialsFromName } from '@/lib/utils/initials.ts'

const routes = [
  { name: 'Dashboard', path: '/dashboard', icon: Home, enabled: true },
  { name: 'Transactions', path: '/transactions', icon: ArrowUpDown, enabled: true },
  { name: 'Accounts', path: '/accounts', icon: WalletMinimal, enabled: true },
  { name: 'Subscriptions', path: '/subscriptions', icon: RotateCw, enabled: false },
  { name: 'Settings', path: '/settings', icon: Settings, enabled: false },
]

const props = withDefaults(defineProps<SidebarProps>(), {
  collapsible: 'icon',
})

const userStore = useUserStore()

const logout = () => {
  axios.defaults.withXSRFToken = true
  axios.defaults.withCredentials = true
  axios
    .post(
      '/api/v1/user/logout',
      {},
      {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      },
    )
    .then((response) => {
      if (response.status === 200) {
        userStore.clearUser()
        router.push('/login')
      }
    })
    .catch((error) => {
      if (error.status === 400) {
      }
    })
}
</script>

<template>
  <Sidebar v-bind="props">
    <SidebarHeader>
      <SidebarMenu>
        <SidebarMenuItem>
          <SidebarMenuButton>
            <RouterLink to="/dashboard">
              <div class="grid flex-1 text-left text-sm leading-tight">
                <span class="truncate font-semibold">ZenSpent</span>
                <span class="truncate text-xs">Your path to financial zen</span>
              </div>
            </RouterLink>
          </SidebarMenuButton>
        </SidebarMenuItem>
      </SidebarMenu>
    </SidebarHeader>

    <Separator class="bg-[#8fc79c] dark:bg-border" />

    <SidebarContent>
      <SidebarGroup>
        <SidebarGroupContent>
          <SidebarMenu>
            <SidebarMenuItem v-for="route in routes" :key="route.path">
              <SidebarMenuButton as-child v-if="route.enabled">
                <RouterLink :to="route.path">
                  <component :is="route.icon" />
                  <span>{{ route.name }}</span>
                </RouterLink>
              </SidebarMenuButton>
            </SidebarMenuItem>
          </SidebarMenu>
        </SidebarGroupContent>
      </SidebarGroup>
    </SidebarContent>

    <SidebarFooter v-if="userStore.user != null">
      <SidebarMenu>
        <SidebarMenuItem>
          <DropdownMenu>
            <DropdownMenuTrigger as-child>
              <SidebarMenuButton size="lg">
                <Avatar class="h-10 w-10 rounded-lg border border-border">
                  <AvatarImage v-if="userStore.user?.avatar" :src="userStore.user.avatar" />
                  <AvatarFallback class="rounded-lg border">
                    {{ initialsFromName(userStore.user?.firstName, userStore.user?.lastName) }}
                  </AvatarFallback>
                </Avatar>
                <div class="grid flex-1 text-left text-sm leading-tight">
                  <span class="truncate font-semibold">{{
                    userStore.user?.firstName + ' ' + userStore.user?.lastName
                  }}</span>
                  <span class="truncate text-xs">{{ userStore.user?.email }}</span>
                </div>
                <ChevronsUpDown class="ml-auto size-4" />
              </SidebarMenuButton>
            </DropdownMenuTrigger>
            <DropdownMenuContent>
              <DropdownMenuGroup>
                <DropdownMenuItem
                  class="font-semibold text-destructive focus:text-destructive dark:text-red-400 dark:focus:text-red-400"
                  v-on:click="logout"
                >
                  <LogOut class="text-destructive dark:text-red-400" />
                  Log out
                </DropdownMenuItem>
                <DropdownMenuItem v-on:click="router.push('/profile')">
                  <User />
                  Profile
                </DropdownMenuItem>
              </DropdownMenuGroup>
              <DropdownMenuSeparator />
              <DropdownMenuSub>
                <DropdownMenuSubTrigger class="flex items-center gap-2">
                  <Palette class="size-4" />
                  Theme
                </DropdownMenuSubTrigger>
                <DropdownMenuSubContent>
                  <ThemeMenu />
                </DropdownMenuSubContent>
              </DropdownMenuSub>
            </DropdownMenuContent>
          </DropdownMenu>
        </SidebarMenuItem>
      </SidebarMenu>
    </SidebarFooter>
  </Sidebar>
</template>

<style scoped></style>
