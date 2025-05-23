package com.decrux.zenspent.entities.dtos;

import com.decrux.zenspent.entities.enums.TransactionTypes;

import java.math.BigDecimal;

public record TransactionDTO(Long transactionId,
                             RecipientDTO recipient,
                             TransactionTypes type,
                             BigDecimal amount,
                             String category,
                             String date,
                             ZSUserDTO user,
                             AssetsAccountDTO sourceAssetAccount) { }
