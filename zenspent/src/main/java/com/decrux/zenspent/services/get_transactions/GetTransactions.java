package com.decrux.zenspent.services.get_transactions;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.PaginationRequestDto;
import com.decrux.zenspent.entities.dtos.PaginationResultDto;
import com.decrux.zenspent.entities.dtos.TransactionDto;
import com.decrux.zenspent.entities.dtos.TransactionTypeDto;

import java.util.List;

public interface GetTransactions {
    PaginationResultDto<TransactionDto> getAllTransactions(ZSUser user, PaginationRequestDto paginationRequest);

    List<TransactionTypeDto> getTransactionTypes();
}
