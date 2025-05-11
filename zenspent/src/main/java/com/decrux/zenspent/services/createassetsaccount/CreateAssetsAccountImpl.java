package com.decrux.zenspent.services.createassetsaccount;

import com.decrux.zenspent.entities.db.AssetsAccount;
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
        AssetsAccount assetsAccount = new AssetsAccount();
        assetsAccount.setName(assetsAccountDTO.name());
        assetsAccount.setBalance(assetsAccountDTO.balance());
        assetsAccount.setType(assetsAccountDTO.type());
        assetsAccount.setDescription(assetsAccountDTO.description());
        assetsAccount.setCreatedAt(LocalDate.now());
        assetsAccount.setUser(user);

        AssetsAccount savedAssetsAccount = this.assetsAccountRepository.save(assetsAccount);

        return new AssetsAccountDTO(
                savedAssetsAccount.getAssetsAccountId(),
                savedAssetsAccount.getName(),
                savedAssetsAccount.getBalance(),
                savedAssetsAccount.getType(),
                savedAssetsAccount.getDescription()
        );
    }

}
