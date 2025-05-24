package com.decrux.zenspent.services.createassetsaccount;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.AssetAccountDto;

public interface CreateAssetsAccount {

    AssetAccountDto createAssetsAccount(AssetAccountDto assetAccountDto, ZSUser user);

}
