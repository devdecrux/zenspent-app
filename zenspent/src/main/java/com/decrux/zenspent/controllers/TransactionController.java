package com.decrux.zenspent.controllers;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.TransactionDTO;
import com.decrux.zenspent.services.createtransaction.CreateTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final CreateTransaction createTransaction;

    @PostMapping
    public TransactionDTO createTransaction(@RequestBody TransactionDTO transactionDTO, @AuthenticationPrincipal ZSUser user) {
        return createTransaction.createTransaction(transactionDTO, user);
    }

}
