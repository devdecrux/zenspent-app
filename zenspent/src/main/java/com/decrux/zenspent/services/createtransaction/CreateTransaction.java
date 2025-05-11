package com.decrux.zenspent.services.createtransaction;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.TransactionDTO;

public interface CreateTransaction {

    TransactionDTO createTransaction(TransactionDTO transactionDTO, ZSUser user);

}
