package com.decrux.zenspent.repositories;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ZSUser, Long> {

    @Query("SELECT u FROM ZSUser u JOIN FETCH u.roles WHERE u.email = :email")
    ZSUser findUserByEmail(String email);

}
