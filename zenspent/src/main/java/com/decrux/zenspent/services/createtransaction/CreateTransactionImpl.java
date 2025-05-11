package com.decrux.zenspent.services.createtransaction;

import com.decrux.zenspent.entities.db.AssetsAccount;
import com.decrux.zenspent.entities.db.Transaction;
import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.TransactionDTO;
import com.decrux.zenspent.entities.dtos.ZSUserDTO;
import com.decrux.zenspent.entities.enums.TransactionTypes;
import com.decrux.zenspent.repositories.AssetsAccountRepository;
import com.decrux.zenspent.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateTransactionImpl implements CreateTransaction {

    private final AssetsAccountRepository assetsAccountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionDTO createTransaction(TransactionDTO transactionDTO, ZSUser user) {
        Transaction transaction = new Transaction();
        transaction.setType(transactionDTO.type());
        transaction.setAmount(transactionDTO.amount());
        transaction.setDate(LocalDate.now());
        transaction.setCategory(transactionDTO.category());
        transaction.setDescription(transactionDTO.description());
        transaction.setUser(user);

        Optional<AssetsAccount> assetsAccountOptional;
        AssetsAccount assetsAccount = null;
        if (transactionDTO.assetsAccountId() != null) {
            assetsAccountOptional = this.assetsAccountRepository.findById(transactionDTO.assetsAccountId());
            if (assetsAccountOptional.isPresent()) {
                assetsAccount = assetsAccountOptional.get();
                transaction.setAssetsAccount(assetsAccount);
            }
        }

        Transaction savedTransaction = this.transactionRepository.save(transaction);

        if (assetsAccount != null) {
            BigDecimal newAccountBalance;
            if (TransactionTypes.WITHDRAWAL.equals(savedTransaction.getType())) {
                newAccountBalance = assetsAccount.getBalance().subtract(savedTransaction.getAmount());
            } else {
                newAccountBalance = assetsAccount.getBalance().add(savedTransaction.getAmount());
            }
            assetsAccount.setBalance(newAccountBalance);
            this.assetsAccountRepository.save(assetsAccount);
        }

        ZSUserDTO zsUserDTO = ZSUserDTO.builder()
                .username(savedTransaction.getUser().getUsername())
                .email(savedTransaction.getUser().getEmail())
                .firstName(savedTransaction.getUser().getFirstName())
                .lastName(savedTransaction.getUser().getLastName())
                .build();

        return new TransactionDTO(
                savedTransaction.getTransactionId(),
                savedTransaction.getType(),
                savedTransaction.getAmount(),
                savedTransaction.getCategory(),
                savedTransaction.getDescription(),
                zsUserDTO,
                Optional.ofNullable(savedTransaction.getAssetsAccount()).map(AssetsAccount::getAssetsAccountId).orElse(null)
        );
    }
}
