import type { User } from '@/entities/User.ts'
import type { TransactionParticipant } from '@/entities/TransactionParticipant.ts'

export interface Transaction {
  id: number
  recipient: TransactionParticipant
  type: string
  amount: bigint
  category: string
  date: string
  payer: TransactionParticipant
  user: User
}
