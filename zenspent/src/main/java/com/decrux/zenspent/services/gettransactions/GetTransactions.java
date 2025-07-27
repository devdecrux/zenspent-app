package com.decrux.zenspent.services.gettransactions;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.PaginationRequestDto;
import com.decrux.zenspent.entities.dtos.PaginationResultDto;
import com.decrux.zenspent.entities.dtos.TransactionDto;

public interface GetTransactions {
    PaginationResultDto<TransactionDto> getAllTransactions(ZSUser user, PaginationRequestDto paginationRequest);
}
