package com.decrux.zenspent.services.create_account;

import com.decrux.zenspent.entities.db.Account;
import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.AccountDto;
import com.decrux.zenspent.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CreateAccountImpl implements CreateAccount {

    private final AccountRepository accountRepository;

    public AccountDto createAccount(AccountDto AccountDto, ZSUser user) {
        Account Account = new Account();
        Account.setName(AccountDto.name());
        Account.setBalance(AccountDto.balance());
        Account.setType(AccountDto.type());
        Account.setDescription(AccountDto.description());
        Account.setDate(LocalDate.now());
        Account.setUser(user);

        Account savedAccount = this.accountRepository.save(Account);

        return new AccountDto(
                savedAccount.getAccountId(),
                savedAccount.getName(),
                savedAccount.getBalance(),
                savedAccount.getType(),
                savedAccount.getDescription()
        );
    }

}
