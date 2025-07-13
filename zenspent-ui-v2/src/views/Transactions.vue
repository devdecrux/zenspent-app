<script setup lang="ts">
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '@/components/ui/table'

import { Edit, Trash2 } from 'lucide-vue-next'
import { Dialog, DialogContent, DialogHeader, DialogTrigger } from '@/components/ui/dialog'
import { Button } from '@/components/ui/button'
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select'
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
            <SelectContent>
              <SelectGroup>
                <SelectLabel>Expense Accounts</SelectLabel>
                <SelectItem value="id1">Netflix</SelectItem>
                <SelectItem value="id2">Car leasing</SelectItem>
              </SelectGroup>
              <SelectGroup>
                <SelectLabel>Check Accounts</SelectLabel>
                <SelectItem value="new-id-1">DSK razplashatelna smetka</SelectItem>
                <SelectItem value="new-id-2">Revolut smetka</SelectItem>
              </SelectGroup>
            </SelectContent>
          </Select>
          <Select>
            <SelectTrigger class="w-full">
              <SelectValue placeholder="Select a recipient" />
            </SelectTrigger>
            <SelectContent>
              <SelectGroup>
                <SelectLabel>Expense Accounts</SelectLabel>
                <SelectItem value="id1">Netflix</SelectItem>
                <SelectItem value="id2">Car leasing</SelectItem>
              </SelectGroup>
              <SelectGroup>
                <SelectLabel>Check Accounts</SelectLabel>
                <SelectItem value="new-id-1">DSK razplashatelna smetka</SelectItem>
                <SelectItem value="new-id-2">Revolut smetka</SelectItem>
              </SelectGroup>
            </SelectContent>
          </Select>
        </div>
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
            <div class="flex gap-2">
              <Edit class="hover:bg-slate-200" />
              <Trash2 class="hover:bg-slate-200" />
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
            <div class="flex gap-2">
              <Edit class="hover:bg-slate-200" />
              <Trash2 class="hover:bg-slate-200" />
            </div>
          </TableCell>
        </TableRow>
      </TableBody>
    </Table>
  </div>
</template>

<style scoped></style>
