package com.decrux.zenspent.entities.dtos;

import com.decrux.zenspent.entities.enums.TransactionTypes;

import java.math.BigDecimal;

public record TransactionDto(Long id,
                             TransactionParticipantDto recipient,
                             TransactionTypes type,
                             BigDecimal amount,
                             String category,
                             String date,
                             ZSUserDto user,
                             TransactionParticipantDto payer) {
}
