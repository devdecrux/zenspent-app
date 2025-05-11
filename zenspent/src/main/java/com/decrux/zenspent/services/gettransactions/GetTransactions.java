package com.decrux.zenspent.services.gettransactions;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.TransactionDTO;

import java.util.List;

public interface GetTransactions {

    List<TransactionDTO> getAllTransactions(ZSUser user);

}
