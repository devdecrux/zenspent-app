package com.decrux.zenspent.services.createtransaction;

import com.decrux.zenspent.entities.db.AssetAccount;
import com.decrux.zenspent.entities.db.Transaction;
import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.TransactionDTO;
import com.decrux.zenspent.entities.dtos.TransactionParticipantDto;
import com.decrux.zenspent.entities.dtos.ZSUserDTO;
import com.decrux.zenspent.entities.enums.TransactionTypes;
import com.decrux.zenspent.repositories.AssetsAccountRepository;
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

    private final AssetsAccountRepository assetsAccountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public TransactionDTO createTransaction(TransactionDTO transactionDTO, ZSUser user) {

        AssetAccount recipentAssetAccount = null;
        AssetAccount sourceAssetAccount = null;

        if (transactionDTO.recipient().assetAccountId() != null) {
            Optional<AssetAccount> recipientAssetAccountOptional = this.assetsAccountRepository.findById(transactionDTO.recipient().assetAccountId());
            if (recipientAssetAccountOptional.isPresent()) {
                recipentAssetAccount = recipientAssetAccountOptional.get();
            }
        }

        if (transactionDTO.payer().assetAccountId() != null) {
            Optional<AssetAccount> sourceAssetAccountOptional = this.assetsAccountRepository.findById(transactionDTO.payer().assetAccountId());
            if (sourceAssetAccountOptional.isPresent()) {
                sourceAssetAccount = sourceAssetAccountOptional.get();
            }
        }

        Transaction savedTransaction;

        switch (transactionDTO.type()) {
            case TransactionTypes.WITHDRAWAL -> savedTransaction = createWithdrawalTransaction(transactionDTO, sourceAssetAccount, user);
            case TransactionTypes.INCOME, TransactionTypes.SALARY, TransactionTypes.REFUND ->
                    savedTransaction = createIncomeTransaction(transactionDTO, recipentAssetAccount, user);
            case TransactionTypes.TRANSFER ->
                    savedTransaction = createTransferTransaction(transactionDTO, recipentAssetAccount, sourceAssetAccount, user);
            default -> throw new IllegalStateException("Unexpected transaction type: " + transactionDTO.type());
        }

        ZSUserDTO zsUserDTO = ZSUserDTO.builder()
                .username(savedTransaction.getUser().getUsername())
                .email(savedTransaction.getUser().getEmail())
                .firstName(savedTransaction.getUser().getFirstName())
                .lastName(savedTransaction.getUser().getLastName())
                .build();

        return new TransactionDTO(
                savedTransaction.getTransactionId(),
                new TransactionParticipantDto(savedTransaction.getRecipientName(), savedTransaction.getRecipientAssetAccountId()),
                savedTransaction.getType(),
                savedTransaction.getAmount(),
                savedTransaction.getCategory(),
                savedTransaction.getDate().toString(),
                zsUserDTO,
                new TransactionParticipantDto(savedTransaction.getPayerName(), savedTransaction.getPayerAssetAccountId())
        );
    }

    private Transaction createWithdrawalTransaction(TransactionDTO transactionDTO, AssetAccount sourceAssetAccount, ZSUser user) {
        Transaction transaction = createBasicTransaction(transactionDTO, user);
        transaction.setType(TransactionTypes.WITHDRAWAL);

        transaction.setRecipientName(transactionDTO.recipient().name());

        transaction.setPayerName(sourceAssetAccount.getName());
        transaction.setPayerAssetAccountId(sourceAssetAccount.getAssetAccountId());

        Transaction savedTransactions = this.transactionRepository.save(transaction);

        BigDecimal newSourceAccountBalance = sourceAssetAccount.getBalance().subtract(savedTransactions.getAmount());
        sourceAssetAccount.setBalance(newSourceAccountBalance);
        this.assetsAccountRepository.save(sourceAssetAccount);

        return savedTransactions;
    }

    private Transaction createIncomeTransaction(TransactionDTO transactionDTO, AssetAccount recipientAssetAccount, ZSUser user) {
        Transaction transaction = createBasicTransaction(transactionDTO, user);
        transaction.setType(transactionDTO.type());

        System.out.println("recipientAssetAccount: " + recipientAssetAccount);
        transaction.setRecipientName(recipientAssetAccount.getName());
        transaction.setRecipientAssetAccountId(recipientAssetAccount.getAssetAccountId());

        transaction.setPayerName(transactionDTO.payer().name());

        Transaction savedTransactions = this.transactionRepository.save(transaction);

        BigDecimal newRecipientAccountBalance = recipientAssetAccount.getBalance().add(savedTransactions.getAmount());
        recipientAssetAccount.setBalance(newRecipientAccountBalance);
        this.assetsAccountRepository.save(recipientAssetAccount);

        return savedTransactions;
    }

    private Transaction createTransferTransaction(TransactionDTO transactionDTO, AssetAccount recipientAssetAccount, AssetAccount sourceAssetAccount, ZSUser user) {
        Transaction transaction = createBasicTransaction(transactionDTO, user);
        transaction.setType(TransactionTypes.TRANSFER);

        transaction.setRecipientName(recipientAssetAccount.getName());
        transaction.setRecipientAssetAccountId(recipientAssetAccount.getAssetAccountId());

        transaction.setPayerName(sourceAssetAccount.getName());
        transaction.setPayerAssetAccountId(sourceAssetAccount.getAssetAccountId());

        Transaction savedTransactions = this.transactionRepository.save(transaction);

        BigDecimal newSourceAccountBalance = sourceAssetAccount.getBalance().subtract(savedTransactions.getAmount());
        sourceAssetAccount.setBalance(newSourceAccountBalance);
        this.assetsAccountRepository.save(sourceAssetAccount);

        BigDecimal newRecipientAccountBalance = recipientAssetAccount.getBalance().add(savedTransactions.getAmount());
        recipientAssetAccount.setBalance(newRecipientAccountBalance);
        this.assetsAccountRepository.save(recipientAssetAccount);

        return savedTransactions;
    }

    private Transaction createBasicTransaction(TransactionDTO transactionDTO, ZSUser user) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.amount());
        if (transactionDTO.date() != null) {
            transaction.setDate(LocalDate.parse(transactionDTO.date()));
        } else {
            transaction.setDate(LocalDate.now());
        }
        transaction.setCategory(transactionDTO.category());
        transaction.setUser(user);
        return transaction;
    }

}
