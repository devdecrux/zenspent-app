package com.decrux.zenspent.entities.dtos;

import com.decrux.zenspent.entities.enums.AssetAccountTypes;

import java.math.BigDecimal;

public record AssetAccountDto(Long id,
                              String name,
                              BigDecimal balance,
                              AssetAccountTypes type,
                              String description) {
}
