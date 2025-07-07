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

const routes = [
  { name: 'Dashboard', path: '/dashboard', icon: Home },
  { name: 'Transactions', path: '/transactions', icon: ArrowUpDown },
  { name: 'Subscriptions', path: '/subscriptions', icon: RotateCw },
  { name: 'Settings', path: '/settings', icon: Settings },
]

const props = withDefaults(defineProps<SidebarProps>(), {
  collapsible: 'icon',
})
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
