package com.decrux.zenspent.services.get_accounts;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.AccountDto;
import com.decrux.zenspent.entities.dtos.AccountTypeDto;
import com.decrux.zenspent.entities.dtos.PaginationRequestDto;
import com.decrux.zenspent.entities.dtos.PaginationResultDto;

import java.util.List;

public interface GetAccounts {

    List<AccountTypeDto> getAccountTypes();

    PaginationResultDto<AccountDto> getAllAccounts(ZSUser user, PaginationRequestDto paginationRequest);

}
