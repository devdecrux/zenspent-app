package com.decrux.zenspent.services.get_asset_accounts;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.AssetAccountDto;
import com.decrux.zenspent.entities.dtos.AssetAccountTypeDto;
import com.decrux.zenspent.entities.dtos.PaginationRequestDto;
import com.decrux.zenspent.entities.dtos.PaginationResultDto;

import java.util.List;

public interface GetAssetAccounts {

    List<AssetAccountTypeDto> getAssetAccountTypes();

    PaginationResultDto<AssetAccountDto> getAllAssetAccounts(ZSUser user, PaginationRequestDto paginationRequest);

}
