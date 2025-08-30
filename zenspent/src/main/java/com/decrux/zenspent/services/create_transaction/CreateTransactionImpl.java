package com.decrux.zenspent.services.create_transaction;

import com.decrux.zenspent.entities.db.Account;
import com.decrux.zenspent.entities.db.Transaction;
import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.TransactionDto;
import com.decrux.zenspent.entities.dtos.TransactionParticipantDto;
import com.decrux.zenspent.entities.dtos.ZSUserDto;
import com.decrux.zenspent.repositories.AccountRepository;
import com.decrux.zenspent.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateTransactionImpl implements CreateTransaction {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public TransactionDto createTransaction(TransactionDto transactionDTO, ZSUser user) {

        Account recipentAccount;
        Account sourceAccount;

        Optional<Account> recipientAccountOptional = this.accountRepository.findById(transactionDTO.recipient().accountId());
        if (recipientAccountOptional.isPresent()) {
            recipentAccount = recipientAccountOptional.get();
        } else {
            throw new IllegalArgumentException("Recipient account not found with ID: " + transactionDTO.recipient().accountId());
        }

        Optional<Account> sourceAccountOptional = this.accountRepository.findById(transactionDTO.payer().accountId());
        if (sourceAccountOptional.isPresent()) {
            sourceAccount = sourceAccountOptional.get();
        } else {
            throw new IllegalArgumentException("Source account not found with ID: " + transactionDTO.payer().accountId());
        }

        Transaction savedTransaction = createTransaction(transactionDTO, recipentAccount, sourceAccount, user);

        ZSUserDto zsUserDTO = ZSUserDto.builder()
                .username(savedTransaction.getUser().getUsername())
                .email(savedTransaction.getUser().getEmail())
                .firstName(savedTransaction.getUser().getFirstName())
                .lastName(savedTransaction.getUser().getLastName())
                .build();

        return new TransactionDto(
                savedTransaction.getTransactionId(),
                new TransactionParticipantDto(savedTransaction.getRecipient().getName(), savedTransaction.getRecipient().getAccountId()),
                savedTransaction.getType(),
                savedTransaction.getAmount(),
                savedTransaction.getCategory(),
                savedTransaction.getDate().toString(),
                zsUserDTO,
                new TransactionParticipantDto(savedTransaction.getPayer().getName(), savedTransaction.getPayer().getAccountId())
        );
    }

    private Transaction createTransaction(TransactionDto transactionDTO, Account recipientAccount, Account sourceAccount, ZSUser user) {
        Transaction Transaction = new Transaction();
        Transaction.setAmount(transactionDTO.amount());
        if (transactionDTO.date() != null && !transactionDTO.date().isEmpty()) {
            Transaction.setDate(LocalDate.parse(transactionDTO.date()));
        } else {
            Transaction.setDate(LocalDate.now());
        }
        Transaction.setCategory(transactionDTO.category());
        Transaction.setUser(user);
        Transaction.setType(transactionDTO.type());
        Transaction.setRecipient(recipientAccount);
        Transaction.setPayer(sourceAccount);

        Transaction savedTransactions = this.transactionRepository.save(Transaction);

        BigDecimal newSourceAccountBalance = sourceAccount.getBalance().subtract(savedTransactions.getAmount());
        sourceAccount.setBalance(newSourceAccountBalance);
        this.accountRepository.save(sourceAccount);

        BigDecimal newRecipientAccountBalance = recipientAccount.getBalance().add(savedTransactions.getAmount());
        recipientAccount.setBalance(newRecipientAccountBalance);
        this.accountRepository.save(recipientAccount);

        return savedTransactions;
    }

}
