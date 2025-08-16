package com.decrux.zenspent.controllers;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.AssetAccountDto;
import com.decrux.zenspent.entities.dtos.AssetAccountTypeDto;
import com.decrux.zenspent.entities.dtos.PaginationRequestDto;
import com.decrux.zenspent.entities.dtos.PaginationResultDto;
import com.decrux.zenspent.services.createassetsaccount.CreateAssetsAccount;
import com.decrux.zenspent.services.get_asset_accounts.GetAssetAccounts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/asset-accounts")
@RequiredArgsConstructor
public class AssetsAccountController {

    private final CreateAssetsAccount createAssetsAccount;
    private final GetAssetAccounts getAssetAccounts;

    @GetMapping
    public PaginationResultDto<AssetAccountDto> getAllAssetAccounts(@AuthenticationPrincipal ZSUser user,
                                                                    @RequestParam(defaultValue = "0") int pageNumber,
                                                                    @RequestParam(defaultValue = "10") int pageSize) {
        PaginationRequestDto paginationRequest = new PaginationRequestDto(pageNumber, pageSize);
        return this.getAssetAccounts.getAllAssetAccounts(user, paginationRequest);
    }

    @PostMapping
    public AssetAccountDto createAssetsAccount(@RequestBody AssetAccountDto assetAccountDto, @AuthenticationPrincipal ZSUser user) {
        return this.createAssetsAccount.createAssetsAccount(assetAccountDto, user);
    }

    @GetMapping("/types")
    public List<AssetAccountTypeDto> getTransactionTypes() {
        return this.getAssetAccounts.getAssetAccountTypes();
    }

}
