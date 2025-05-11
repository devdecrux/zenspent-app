package com.decrux.zenspent.entities.db;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.enums.AssetsAccountTypes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "zs_assets_accounts")
public class AssetsAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetsAccountId;
    private String name;
    private BigDecimal balance;
    private AssetsAccountTypes type;
    private String description;
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private ZSUser user;
    @OneToMany(mappedBy = "assetsAccount", cascade = CascadeType.ALL)
    private List<Transaction> transaction;

}
