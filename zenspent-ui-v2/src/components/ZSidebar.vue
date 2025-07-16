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
import { ArrowUpDown, ChevronsUpDown, Home, LogOut, RotateCw, Settings } from 'lucide-vue-next'
import { Separator } from '@/components/ui/separator'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuGroup,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu'
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar'
import { Button } from '@/components/ui/button'
import { Icon } from '@iconify/vue'
import { useColorMode } from '@vueuse/core'

const routes = [
  { name: 'Dashboard', path: '/dashboard', icon: Home },
  { name: 'Transactions', path: '/transactions', icon: ArrowUpDown },
  { name: 'Subscriptions', path: '/subscriptions', icon: RotateCw },
  { name: 'Settings', path: '/settings', icon: Settings },
]

const props = withDefaults(defineProps<SidebarProps>(), {
  collapsible: 'icon',
})

const mode = useColorMode()
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

    <Separator />

    <SidebarContent>
      <SidebarGroup>
        <SidebarGroupContent>
          <SidebarMenu>
            <SidebarMenuItem v-for="route in routes" :key="route.path">
              <SidebarMenuButton as-child>
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

    <!--    <SidebarGroup>-->
    <!--      <SidebarGroupContent>-->
    <!--        <SidebarMenu>-->
    <!--          <SidebarMenuItem>-->
    <!--            <SidebarMenuButton as-child>-->
    <!--              <SidebarTrigger />-->
    <!--            </SidebarMenuButton>-->
    <!--          </SidebarMenuItem>-->
    <!--        </SidebarMenu>-->
    <!--      </SidebarGroupContent>-->
    <!--    </SidebarGroup>-->

    <SidebarGroup>
      <SidebarGroupContent>
        <SidebarMenu>
          <SidebarMenuItem>
            <DropdownMenu>
              <DropdownMenuTrigger as-child>
                <Button>
                  <Icon
                    icon="radix-icons:moon"
                    class="h-[1.2rem] w-[1.2rem] rotate-0 scale-100 transition-all dark:-rotate-90 dark:scale-0"
                  />
                  <Icon
                    icon="radix-icons:sun"
                    class="absolute h-[1.2rem] w-[1.2rem] rotate-90 scale-0 transition-all dark:rotate-0 dark:scale-100"
                  />
                  <span class="sr-only">Toggle theme</span>
                </Button>
              </DropdownMenuTrigger>
              <DropdownMenuContent align="end">
                <DropdownMenuItem v-on:click="mode = 'light'"> Light</DropdownMenuItem>
                <DropdownMenuItem v-on:click="mode = 'dark'"> Dark</DropdownMenuItem>
                <DropdownMenuItem v-on:click="mode = 'auto'"> System</DropdownMenuItem>
              </DropdownMenuContent>
            </DropdownMenu>
          </SidebarMenuItem>
        </SidebarMenu>
      </SidebarGroupContent>
    </SidebarGroup>

    <Separator />

    <SidebarFooter>
      <SidebarMenu>
        <SidebarMenuItem>
          <DropdownMenu>
            <DropdownMenuTrigger as-child>
              <SidebarMenuButton size="lg">
                <Avatar class="h-8 w-8 rounded-lg">
                  <AvatarImage src="../assets/avatar.jpg" />
                  <AvatarFallback class="rounded-lg border"> MV</AvatarFallback>
                </Avatar>
                <div class="grid flex-1 text-left text-sm leading-tight">
                  <span class="truncate font-semibold">Milen Valchev</span>
                  <span class="truncate text-xs">milen.ovalchev@gmail.com</span>
                </div>
                <ChevronsUpDown class="ml-auto size-4" />
              </SidebarMenuButton>
            </DropdownMenuTrigger>
            <DropdownMenuContent>
              <DropdownMenuGroup>
                <DropdownMenuItem>
                  <LogOut />
                  Log out
                </DropdownMenuItem>
              </DropdownMenuGroup>
            </DropdownMenuContent>
          </DropdownMenu>
        </SidebarMenuItem>
      </SidebarMenu>
    </SidebarFooter>
  </Sidebar>
</template>

<style scoped></style>
