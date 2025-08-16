package com.decrux.zenspent.services.get_accounts;

import com.decrux.zenspent.entities.db.Account;
import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.AccountDto;
import com.decrux.zenspent.entities.dtos.AccountTypeDto;
import com.decrux.zenspent.entities.dtos.PaginationRequestDto;
import com.decrux.zenspent.entities.dtos.PaginationResultDto;
import com.decrux.zenspent.entities.enums.AccountTypes;
import com.decrux.zenspent.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@RequiredArgsConstructor
public class GetAccountsImpl implements GetAccounts {

    private final AccountRepository repository;

    public PaginationResultDto<AccountDto> getAllAccounts(ZSUser user, PaginationRequestDto paginationRequest) {
        Pageable pageable = PageRequest.of(paginationRequest.pageNumber(), paginationRequest.pageSize(), ASC, "date");
        Page<Account> accounts = this.repository.findAllByUser(user, pageable);
        return new PaginationResultDto<>(
                accounts.stream().map(account -> new AccountDto(
                                account.getAccountId(),
                                account.getName(),
                                account.getBalance(),
                                account.getType(),
                                account.getDescription()
                        ))
                        .toList(),
                accounts.getTotalPages(),
                accounts.getTotalElements(),
                accounts.getSize(),
                accounts.getNumber(),
                accounts.isEmpty()
        );
    }

    @Override
    public List<AccountTypeDto> getAccountTypes() {
        return Arrays.stream(
                        AccountTypes.values())
                .map(type -> new AccountTypeDto(type.name(), type.name()))
                .toList();
    }

}
