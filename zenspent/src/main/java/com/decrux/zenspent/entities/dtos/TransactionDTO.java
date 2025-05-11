package com.decrux.zenspent.entities.dtos;

import com.decrux.zenspent.entities.enums.TransactionTypes;

import java.math.BigDecimal;

public record TransactionDTO(Long transactionId,
                             TransactionTypes type,
                             BigDecimal amount,
                             String category,
                             String description,
                             ZSUserDTO user,
                             Long assetsAccountId) { }
