import type { User } from '@/entities/User.ts'
import type { TransactionParticipant } from '@/entities/TransactionParticipant.ts'

export interface Transaction {
  id: string
  recipient: TransactionParticipant
  type: string
  amount: string
  category: string
  date: string
  payer: TransactionParticipant
  user: User
}
