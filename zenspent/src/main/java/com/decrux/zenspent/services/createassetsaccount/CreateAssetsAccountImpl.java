package com.decrux.zenspent.services.createassetsaccount;

import com.decrux.zenspent.entities.db.AssetAccount;
import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.AssetsAccountDTO;
import com.decrux.zenspent.repositories.AssetsAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CreateAssetsAccountImpl implements CreateAssetsAccount {

    private final AssetsAccountRepository assetsAccountRepository;

    public AssetsAccountDTO createAssetsAccount(AssetsAccountDTO assetsAccountDTO, ZSUser user) {
        AssetAccount assetAccount = new AssetAccount();
        assetAccount.setName(assetsAccountDTO.name());
        assetAccount.setBalance(assetsAccountDTO.balance());
        assetAccount.setType(assetsAccountDTO.type());
        assetAccount.setDescription(assetsAccountDTO.description());
        assetAccount.setCreatedAt(LocalDate.now());
        assetAccount.setUser(user);

        AssetAccount savedAssetAccount = this.assetsAccountRepository.save(assetAccount);

        return new AssetsAccountDTO(
                savedAssetAccount.getAssetAccountId(),
                savedAssetAccount.getName(),
                savedAssetAccount.getBalance(),
                savedAssetAccount.getType(),
                savedAssetAccount.getDescription()
        );
    }

}
