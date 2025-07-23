<script setup lang="ts">
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '@/components/ui/table'

import { CalendarIcon, Edit, Trash2 } from 'lucide-vue-next'
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTrigger,
} from '@/components/ui/dialog'
import { Button } from '@/components/ui/button'
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select'
import { Input } from '@/components/ui/input'
import { Popover, PopoverContent, PopoverTrigger } from '@/components/ui/popover'

import { DateFormatter, type DateValue, getLocalTimeZone } from '@internationalized/date'
import { ref } from 'vue'
import { cn } from '@/lib/utils.ts'
import { Calendar } from '@/components/ui/calendar'

const df = new DateFormatter('bg-BG', {
  day: '2-digit',
  month: '2-digit',
  year: '2-digit',
})

const value = ref<DateValue>()
</script>

<template>
  <div class="flex mb-2">
    <Dialog>
      <DialogTrigger as-child>
        <Button>Add Transaction</Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader> Create transaction</DialogHeader>
        <div class="flex flex-col lg:flex-row gap-2">
          <Select>
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Select a recipient" />
            </SelectTrigger>
            <SelectContent class="pl-1 pt-1">
              <SelectGroup>
                <SelectLabel class="font-medium">Expense Accounts</SelectLabel>
                <SelectItem value="id1">Netflix</SelectItem>
                <SelectItem value="id2">Car leasing</SelectItem>
              </SelectGroup>
              <SelectGroup>
                <SelectLabel class="font-medium">Check Accounts</SelectLabel>
                <SelectItem value="new-id-1">DSK razplashatelna smetka</SelectItem>
                <SelectItem value="new-id-2">Revolut smetka</SelectItem>
              </SelectGroup>
            </SelectContent>
          </Select>
          <Select>
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Select a payer" />
            </SelectTrigger>
            <SelectContent class="pl-1 pt-1">
              <SelectGroup>
                <SelectLabel class="font-medium">Expense Accounts</SelectLabel>
                <SelectItem value="id1">Netflix</SelectItem>
                <SelectItem value="id2">Car leasing</SelectItem>
              </SelectGroup>
              <SelectGroup>
                <SelectLabel class="font-medium">Check Accounts</SelectLabel>
                <SelectItem value="new-id-1">DSK razplashatelna smetka</SelectItem>
                <SelectItem value="new-id-2">Revolut smetka</SelectItem>
              </SelectGroup>
            </SelectContent>
          </Select>
        </div>
        <div class="flex flex-col lg:flex-row gap-2">
          <Input id="Amount" type="text" placeholder="Amount" />
          <Select>
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Type" />
            </SelectTrigger>
            <SelectContent class="pl-1 pt-1">
              <SelectItem value="SALARY">Salary</SelectItem>
              <SelectItem value="WITHDRAWAL">WITHDRAWAL</SelectItem>
            </SelectContent>
          </Select>
        </div>
        <div class="flex flex-col lg:flex-row gap-2">
          <Popover>
            <PopoverTrigger as-child>
              <Button
                variant="outline"
                :class="
                  cn('w-1/2 justify-start text-left font-normal', !value && 'text-muted-foreground')
                "
              >
                <CalendarIcon class="mr-2 h-4 w-4" />
                {{ value ? df.format(value.toDate(getLocalTimeZone())) : 'Pick a date' }}
              </Button>
            </PopoverTrigger>
            <PopoverContent class="w-auto p-0">
              <Calendar v-model="value" initial-focus />
            </PopoverContent>
          </Popover>
        </div>
        <DialogFooter>
          <Button>Save</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  </div>

  <div class="rounded-md border">
    <Table>
      <TableHeader class="border-b">
        <TableHead class="text-right">Header1</TableHead>
        <TableHead class="text-right">Header2</TableHead>
        <TableHead class="text-right">Header3</TableHead>
        <TableHead class="text-right">Header4</TableHead>
        <TableHead class="text-right">Header5</TableHead>
      </TableHeader>
      <TableBody>
        <TableRow>
          <TableCell class="text-right">Header1 Content</TableCell>
          <TableCell class="text-right">Header2 Content</TableCell>
          <TableCell class="text-right">Header3 Content</TableCell>
          <TableCell class="text-right">Header4 Content</TableCell>
          <TableCell class="text-right">Header5 Content</TableCell>
          <TableCell class="text-right">
            <div class="flex justify-end gap-2">
              <Edit class="hover:bg-slate-200 dark:hover:bg-slate-700 h-4 w-4" />
              <Trash2 class="hover:bg-slate-200 dark:hover:bg-slate-700 h-4 w-4" />
            </div>
          </TableCell>
        </TableRow>
        <TableRow>
          <TableCell class="text-right">Header1 Content</TableCell>
          <TableCell class="text-right">Header2 Content</TableCell>
          <TableCell class="text-right">Header3 Content</TableCell>
          <TableCell class="text-right">Header4 Content</TableCell>
          <TableCell class="text-right">Header5 Content</TableCell>
          <TableCell>
            <div class="flex justify-end gap-2">
              <Edit class="hover:bg-slate-200 dark:hover:bg-slate-700 h-4 w-4" />
              <Trash2 class="hover:bg-slate-200 dark:hover:bg-slate-700 h-4 w-4" />
            </div>
          </TableCell>
        </TableRow>
      </TableBody>
    </Table>
  </div>
</template>

<style scoped></style>
