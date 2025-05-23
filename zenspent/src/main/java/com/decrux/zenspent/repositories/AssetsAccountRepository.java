package com.decrux.zenspent.repositories;

import com.decrux.zenspent.entities.db.AssetAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetsAccountRepository extends JpaRepository<AssetAccount, Long> {

}
