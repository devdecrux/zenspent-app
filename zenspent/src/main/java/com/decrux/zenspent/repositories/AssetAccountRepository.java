package com.decrux.zenspent.repositories;

import com.decrux.zenspent.entities.db.AssetAccount;
import com.decrux.zenspent.entities.db.auth.ZSUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetAccountRepository extends JpaRepository<AssetAccount, Long> {

    Page<AssetAccount> findAllByUser(ZSUser user, Pageable pageable);

}
