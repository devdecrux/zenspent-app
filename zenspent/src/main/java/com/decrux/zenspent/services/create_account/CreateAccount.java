package com.decrux.zenspent.services.create_account;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.AccountDto;

public interface CreateAccount {

    AccountDto createAccount(AccountDto AccountDto, ZSUser user);

}
