package com.decrux.zenspent.entities.db;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.enums.TransactionTypes;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "zs_transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    @Enumerated(EnumType.STRING)
    private TransactionTypes type;
    private BigDecimal amount;
    private LocalDate date;
    private String category;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "recipient_account_id")
    private Account recipient;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "payer_account_id")
    private Account payer;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private ZSUser user;

}
