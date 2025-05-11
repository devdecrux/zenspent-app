import type {User} from "@/entities/User.ts";

export interface Transaction {
    transactionId: number;
    type: string;
    amount: bigint;
    category: string;
    description: string;
    date: string;
    user: User;
}