package com.decrux.zenspent.controllers;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.TransactionDTO;
import com.decrux.zenspent.services.createtransaction.CreateTransaction;
import com.decrux.zenspent.services.gettransactions.GetTransactions;
import lombok.RequiredArgsConstructor;
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
    public List<TransactionDTO> getAllTransactions(@AuthenticationPrincipal ZSUser user) {
        return this.getTransactions.getAllTransactions(user);
    }

    @PostMapping
    public TransactionDTO createTransaction(@RequestBody TransactionDTO transactionDTO, @AuthenticationPrincipal ZSUser user) {
        return createTransaction.createTransaction(transactionDTO, user);
    }

}
