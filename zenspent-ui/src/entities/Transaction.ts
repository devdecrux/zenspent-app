import type {User} from "@/entities/User.ts";
import type {Recipient} from "@/entities/Recipient.ts";
import type {AssetAccount} from "@/entities/AssetAccount.ts";

export interface Transaction {
    transactionId: number;
    recipient: Recipient
    type: string;
    amount: bigint;
    category: string;
    date: string;
    user: User;
    sourceAssetAccount: AssetAccount;
}