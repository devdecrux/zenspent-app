package com.decrux.zenspent.services.createtransaction;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.TransactionDto;

public interface CreateTransaction {

    TransactionDto createTransaction(TransactionDto transactionDTO, ZSUser user);

}
