package com.decrux.zenspent.controllers;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.PaginationRequestDto;
import com.decrux.zenspent.entities.dtos.PaginationResultDto;
import com.decrux.zenspent.entities.dtos.TransactionDto;
import com.decrux.zenspent.services.createtransaction.CreateTransaction;
import com.decrux.zenspent.services.gettransactions.GetTransactions;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionsController {

    private final CreateTransaction createTransaction;
    private final GetTransactions getTransactions;

    @GetMapping
    public PaginationResultDto<TransactionDto> getAllTransactions(@AuthenticationPrincipal ZSUser user,
                                                                  @RequestParam(defaultValue = "0") int pageNumber,
                                                                  @RequestParam(defaultValue = "10") int pageSize) {
        PaginationRequestDto paginationRequest = new PaginationRequestDto(pageNumber, pageSize);
        return this.getTransactions.getAllTransactions(user, paginationRequest);
    }

    @PostMapping
    public TransactionDto createTransaction(@RequestBody TransactionDto transactionDTO, @AuthenticationPrincipal ZSUser user) {
        return createTransaction.createTransaction(transactionDTO, user);
    }

}
