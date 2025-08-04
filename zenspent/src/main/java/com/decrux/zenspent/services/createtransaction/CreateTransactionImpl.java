package com.decrux.zenspent.services.createtransaction;

import com.decrux.zenspent.entities.db.AssetAccount;
import com.decrux.zenspent.entities.db.Transaction;
import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.TransactionDto;
import com.decrux.zenspent.entities.dtos.TransactionParticipantDto;
import com.decrux.zenspent.entities.dtos.ZSUserDto;
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
    public TransactionDto createTransaction(TransactionDto transactionDTO, ZSUser user) {

        AssetAccount recipentAssetAccount;
        AssetAccount sourceAssetAccount;

        Optional<AssetAccount> recipientAssetAccountOptional = this.assetsAccountRepository.findById(transactionDTO.recipient().assetAccountId());
        if (recipientAssetAccountOptional.isPresent()) {
            recipentAssetAccount = recipientAssetAccountOptional.get();
        } else {
            throw new IllegalArgumentException("Recipient asset account not found with ID: " + transactionDTO.recipient().assetAccountId());
        }

        Optional<AssetAccount> sourceAssetAccountOptional = this.assetsAccountRepository.findById(transactionDTO.payer().assetAccountId());
        if (sourceAssetAccountOptional.isPresent()) {
            sourceAssetAccount = sourceAssetAccountOptional.get();
        } else {
            throw new IllegalArgumentException("Source asset account not found with ID: " + transactionDTO.payer().assetAccountId());
        }

        Transaction savedTransaction = createTransaction(transactionDTO, recipentAssetAccount, sourceAssetAccount, user);

        ZSUserDto zsUserDTO = ZSUserDto.builder()
                .username(savedTransaction.getUser().getUsername())
                .email(savedTransaction.getUser().getEmail())
                .firstName(savedTransaction.getUser().getFirstName())
                .lastName(savedTransaction.getUser().getLastName())
                .build();

        return new TransactionDto(
                savedTransaction.getTransactionId(),
                new TransactionParticipantDto(savedTransaction.getRecipient().getName(), savedTransaction.getRecipient().getAssetAccountId()),
                savedTransaction.getType(),
                savedTransaction.getAmount(),
                savedTransaction.getCategory(),
                savedTransaction.getDate().toString(),
                zsUserDTO,
                new TransactionParticipantDto(savedTransaction.getPayer().getName(), savedTransaction.getPayer().getAssetAccountId())
        );
    }

    private Transaction createTransaction(TransactionDto transactionDTO, AssetAccount recipientAssetAccount, AssetAccount payerAssetAccount, ZSUser user) {
        Transaction transaction = createBasicTransaction(transactionDTO, user);
        transaction.setType(transactionDTO.type());

        transaction.setRecipient(recipientAssetAccount);

        transaction.setPayer(payerAssetAccount);

        Transaction savedTransactions = this.transactionRepository.save(transaction);

        BigDecimal newSourceAccountBalance = payerAssetAccount.getBalance().subtract(savedTransactions.getAmount());
        payerAssetAccount.setBalance(newSourceAccountBalance);
        this.assetsAccountRepository.save(payerAssetAccount);

        BigDecimal newRecipientAccountBalance = recipientAssetAccount.getBalance().add(savedTransactions.getAmount());
        recipientAssetAccount.setBalance(newRecipientAccountBalance);
        this.assetsAccountRepository.save(recipientAssetAccount);

        return savedTransactions;
    }

    private Transaction createBasicTransaction(TransactionDto transactionDTO, ZSUser user) {
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
