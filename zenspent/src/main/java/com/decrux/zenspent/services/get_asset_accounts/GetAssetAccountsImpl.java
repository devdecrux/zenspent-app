package com.decrux.zenspent.services.get_asset_accounts;

import com.decrux.zenspent.entities.db.AssetAccount;
import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.AssetAccountDto;
import com.decrux.zenspent.entities.dtos.AssetAccountTypeDto;
import com.decrux.zenspent.entities.dtos.PaginationRequestDto;
import com.decrux.zenspent.entities.dtos.PaginationResultDto;
import com.decrux.zenspent.entities.enums.AssetAccountTypes;
import com.decrux.zenspent.repositories.AssetAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@RequiredArgsConstructor
public class GetAssetAccountsImpl implements GetAssetAccounts {

    private final AssetAccountRepository repository;

    public PaginationResultDto<AssetAccountDto> getAllAssetAccounts(ZSUser user, PaginationRequestDto paginationRequest) {
        Pageable pageable = PageRequest.of(paginationRequest.pageNumber(), paginationRequest.pageSize(), ASC, "date");
        Page<AssetAccount> assetAccounts = this.repository.findAllByUser(user, pageable);
        return new PaginationResultDto<>(
                assetAccounts.stream().map(account -> new AssetAccountDto(
                                account.getAssetAccountId(),
                                account.getName(),
                                account.getBalance(),
                                account.getType(),
                                account.getDescription()
                        ))
                        .toList(),
                assetAccounts.getTotalPages(),
                assetAccounts.getTotalElements(),
                assetAccounts.getSize(),
                assetAccounts.getNumber(),
                assetAccounts.isEmpty()
        );
    }

    @Override
    public List<AssetAccountTypeDto> getAssetAccountTypes() {
        return Arrays.stream(
                        AssetAccountTypes.values())
                .map(type -> new AssetAccountTypeDto(type.name(), type.name()))
                .toList();
    }

}
