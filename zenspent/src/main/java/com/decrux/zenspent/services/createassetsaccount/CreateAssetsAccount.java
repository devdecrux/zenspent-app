package com.decrux.zenspent.services.createassetsaccount;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.AssetsAccountDTO;

public interface CreateAssetsAccount {

    AssetsAccountDTO createAssetsAccount(AssetsAccountDTO assetsAccountDTO, ZSUser user);

}
