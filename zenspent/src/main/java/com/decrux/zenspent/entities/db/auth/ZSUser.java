package com.decrux.zenspent.entities.db.auth;

import com.decrux.zenspent.entities.db.Account;
import com.decrux.zenspent.entities.db.Transaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "zs_users")
public class ZSUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<ZSUserRole> roles;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Transaction> Transactions;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> Accounts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<ZSUserRole> roles = new HashSet<>(this.getRoles());
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toSet());
    }

}
