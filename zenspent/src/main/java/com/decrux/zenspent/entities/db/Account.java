package com.decrux.zenspent.entities.db;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.enums.AccountTypes;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "zs_accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String name;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private AccountTypes type;
    private String description;
    private LocalDate date;
    @JsonManagedReference
    @OneToMany(mappedBy = "recipient")
    private List<Transaction> incomingTransactions;
    @JsonManagedReference
    @OneToMany(mappedBy = "payer")
    private List<Transaction> outgoingTransactions;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private ZSUser user;

}
