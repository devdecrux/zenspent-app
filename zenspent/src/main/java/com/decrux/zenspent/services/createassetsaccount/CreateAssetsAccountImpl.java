package com.decrux.zenspent.services.createassetsaccount;

import com.decrux.zenspent.entities.db.AssetAccount;
import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.AssetAccountDto;
import com.decrux.zenspent.repositories.AssetsAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CreateAssetsAccountImpl implements CreateAssetsAccount {

    private final AssetsAccountRepository assetsAccountRepository;

    public AssetAccountDto createAssetsAccount(AssetAccountDto assetAccountDto, ZSUser user) {
        AssetAccount assetAccount = new AssetAccount();
        assetAccount.setName(assetAccountDto.name());
        assetAccount.setBalance(assetAccountDto.balance());
        assetAccount.setType(assetAccountDto.type());
        assetAccount.setDescription(assetAccountDto.description());
        assetAccount.setCreatedAt(LocalDate.now());
        assetAccount.setUser(user);

        AssetAccount savedAssetAccount = this.assetsAccountRepository.save(assetAccount);

        return new AssetAccountDto(
                savedAssetAccount.getAssetAccountId(),
                savedAssetAccount.getName(),
                savedAssetAccount.getBalance(),
                savedAssetAccount.getType(),
                savedAssetAccount.getDescription()
        );
    }

}
