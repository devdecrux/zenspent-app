package com.decrux.zenspent.entities.dtos;

import com.decrux.zenspent.entities.enums.AccountTypes;

import java.math.BigDecimal;

public record AccountDto(Long id,
                         String name,
                         BigDecimal balance,
                         AccountTypes type,
                         String description) {
}
