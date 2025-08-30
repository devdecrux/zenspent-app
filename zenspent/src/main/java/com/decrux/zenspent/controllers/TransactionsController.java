package com.decrux.zenspent.controllers;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.PaginationRequestDto;
import com.decrux.zenspent.entities.dtos.PaginationResultDto;
import com.decrux.zenspent.entities.dtos.TransactionDto;
import com.decrux.zenspent.entities.dtos.TransactionTypeDto;
import com.decrux.zenspent.services.create_transaction.CreateTransaction;
import com.decrux.zenspent.services.get_transactions.GetTransactions;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDTO, @AuthenticationPrincipal ZSUser user) {
        return ResponseEntity.status(201).body(this.createTransaction.createTransaction(transactionDTO, user));
    }

    @GetMapping("/types")
    public List<TransactionTypeDto> getTransactionTypes() {
        return this.getTransactions.getTransactionTypes();
    }

}
