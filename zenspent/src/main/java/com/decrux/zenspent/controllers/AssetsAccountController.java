package com.decrux.zenspent.controllers;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.AssetAccountDto;
import com.decrux.zenspent.services.createassetsaccount.CreateAssetsAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/assets-accounts")
@RequiredArgsConstructor
public class AssetsAccountController {

    private final CreateAssetsAccount createAssetsAccount;

    @PostMapping
    public AssetAccountDto createAssetsAccount(@RequestBody AssetAccountDto assetAccountDto, @AuthenticationPrincipal ZSUser user) {
        return this.createAssetsAccount.createAssetsAccount(assetAccountDto, user);
    }

}
