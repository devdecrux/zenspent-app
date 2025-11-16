package com.decrux.zenspent.controllers;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.AccountDto;
import com.decrux.zenspent.entities.dtos.AccountTypeDto;
import com.decrux.zenspent.entities.dtos.PaginationRequestDto;
import com.decrux.zenspent.entities.dtos.PaginationResultDto;
import com.decrux.zenspent.services.create_account.CreateAccount;
import com.decrux.zenspent.services.get_accounts.GetAccounts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountsController {

    private final CreateAccount createAccount;
    private final GetAccounts getAccounts;

    @GetMapping
    public PaginationResultDto<AccountDto> getAllAccounts(@AuthenticationPrincipal ZSUser user,
                                                          @RequestParam(defaultValue = "0") int pageNumber,
                                                          @RequestParam(defaultValue = "10") int pageSize) {
        PaginationRequestDto paginationRequest = new PaginationRequestDto(pageNumber, pageSize);
        return this.getAccounts.getAllAccounts(user, paginationRequest);
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto AccountDto, @AuthenticationPrincipal ZSUser user) {
        return ResponseEntity.status(201).body(this.createAccount.createAccount(AccountDto, user));
    }

    @GetMapping("/types")
    public List<AccountTypeDto> getAccountTypes() {
        return this.getAccounts.getAccountTypes();
    }

}
