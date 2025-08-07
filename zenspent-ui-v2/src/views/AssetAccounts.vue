<script setup lang="ts">
import { useRoute } from 'vue-router'
import { cn } from '@/lib/utils.ts'
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectLabel,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select'
import { Popover, PopoverContent, PopoverTrigger } from '@/components/ui/popover'
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTrigger,
} from '@/components/ui/dialog'
import { Button } from '@/components/ui/button'
import { CalendarIcon } from 'lucide-vue-next'
import { Input } from '@/components/ui/input'
import { Calendar } from '@/components/ui/calendar'
import { ref } from 'vue'
import type { DateValue } from 'reka-ui'

import { getLocalTimeZone } from '@internationalized/date'

const date = ref<DateValue>()

const df = new Intl.DateTimeFormat('bg-BG', {
  year: 'numeric',
  month: '2-digit',
  day: '2-digit',
})
</script>

<template>
  <div class="flex mb-2">
    <Dialog>
      <DialogTrigger as-child>
        <Button>Add Asset Account</Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader> Create transaction</DialogHeader>
        <div class="flex flex-col lg:flex-row gap-2">
          <Select>
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Select a recipient" />
            </SelectTrigger>
            <SelectContent>
              <SelectGroup>
                <SelectLabel class="font-medium">Expense Accounts</SelectLabel>
                <SelectItem value="netflix">Netflix</SelectItem>
                <SelectItem value="car-leasing">Car leasing</SelectItem>
              </SelectGroup>
              <SelectGroup>
                <SelectLabel class="font-medium">Check Accounts</SelectLabel>
                <SelectItem value="dsk">DSK</SelectItem>
                <SelectItem value="revolut">Revolut</SelectItem>
              </SelectGroup>
            </SelectContent>
          </Select>
          <Select>
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Select a payer" />
            </SelectTrigger>
            <SelectContent>
              <SelectGroup>
                <SelectLabel class="font-medium">Expense Accounts</SelectLabel>
                <SelectItem value="netflix">Netflix</SelectItem>
                <SelectItem value="car-leasing">Car leasing</SelectItem>
              </SelectGroup>
              <SelectGroup>
                <SelectLabel class="font-medium">Check Accounts</SelectLabel>
                <SelectItem value="dsk">DSK</SelectItem>
                <SelectItem value="revolut">Revolut</SelectItem>
              </SelectGroup>
            </SelectContent>
          </Select>
        </div>
        <div class="flex flex-col lg:flex-row gap-2">
          <Input id="amount" type="text" placeholder="Amount" />
          <Select>
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Select a type" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="item1">First Item</SelectItem>
            </SelectContent>
          </Select>
        </div>
        <div class="flex flex-col lg:flex-row gap-2">
          <Select>
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Select a category" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="dining">Dining</SelectItem>
              <SelectItem value="cinema">Cinema</SelectItem>
            </SelectContent>
          </Select>
          <Popover>
            <PopoverTrigger as-child>
              <Button
                variant="outline"
                :class="
                  cn('w-1/2 justify-start text-left font-normal', !date && 'text-muted-foreground')
                "
              >
                <CalendarIcon class="mr-2 h-4 w-4" />
                {{ date ? df.format(date.toDate(getLocalTimeZone())) : 'Pick a date' }}
              </Button>
            </PopoverTrigger>
            <PopoverContent class="w-auto p-0">
              <Calendar v-model="date" initial-focus />
            </PopoverContent>
          </Popover>
        </div>
        <DialogFooter>
          <Button>Save</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  </div>
  {{ useRoute().params.assetAccountId }}
</template>
