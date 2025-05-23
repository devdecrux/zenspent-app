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
public class AssetAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetAccountId;
    private String name;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private AssetsAccountTypes type;
    private String description;
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private ZSUser user;
    @OneToMany(mappedBy = "sourceAssetAccount", cascade = CascadeType.ALL)
    private List<Transaction> transaction;

}
