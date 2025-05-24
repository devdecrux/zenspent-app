package com.decrux.zenspent.entities.dtos;

import com.decrux.zenspent.entities.enums.AssetsAccountTypes;

import java.math.BigDecimal;

public record AssetAccountDto(Long assetsAccountId,
                              String name,
                              BigDecimal balance,
                              AssetsAccountTypes type,
                              String description) {
}
