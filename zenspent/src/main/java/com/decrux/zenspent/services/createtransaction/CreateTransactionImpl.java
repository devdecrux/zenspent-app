package com.decrux.zenspent.services.createtransaction;

import com.decrux.zenspent.entities.db.AssetAccount;
import com.decrux.zenspent.entities.db.Transaction;
import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.AssetsAccountDTO;
import com.decrux.zenspent.entities.dtos.RecipientDTO;
import com.decrux.zenspent.entities.dtos.TransactionDTO;
import com.decrux.zenspent.entities.dtos.ZSUserDTO;
import com.decrux.zenspent.entities.enums.TransactionTypes;
import com.decrux.zenspent.repositories.AssetsAccountRepository;
import com.decrux.zenspent.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public TransactionDTO createTransaction(TransactionDTO transactionDTO, ZSUser user) {
        Transaction transaction = new Transaction();
        transaction.setRecipientName(transactionDTO.recipient().name());
        transaction.setRecipientAssetAccountId(transactionDTO.recipient().assetAccountId());
        transaction.setType(transactionDTO.type());
        transaction.setAmount(transactionDTO.amount());
        transaction.setDate(LocalDate.now());
        transaction.setCategory(transactionDTO.category());
        transaction.setUser(user);

        AssetAccount assetAccount = null;
        if (transactionDTO.sourceAssetAccount().assetsAccountId() != null) {
            Optional<AssetAccount> sourceAssetAccountOptional = this.assetsAccountRepository.findById(transactionDTO.sourceAssetAccount().assetsAccountId());
            if (sourceAssetAccountOptional.isPresent()) {
                assetAccount = sourceAssetAccountOptional.get();
                transaction.setSourceAssetAccount(assetAccount);
            }
        }

        Transaction savedTransaction = this.transactionRepository.save(transaction);

        if (assetAccount != null) {
            BigDecimal newSourceAccountBalance;
            if (TransactionTypes.WITHDRAWAL.equals(savedTransaction.getType())) {
                newSourceAccountBalance = assetAccount.getBalance().subtract(savedTransaction.getAmount());
            } else if (TransactionTypes.TRANSFER.equals(savedTransaction.getType())) {
                newSourceAccountBalance = assetAccount.getBalance().subtract(savedTransaction.getAmount());
                Optional<AssetAccount> destinationAssetAccountOptional = this.assetsAccountRepository.findById(transactionDTO.recipient().assetAccountId());
                if (destinationAssetAccountOptional.isPresent()) {
                    AssetAccount destinationAssetAccount = destinationAssetAccountOptional.get();
                    BigDecimal newDestinationAccountBalance = destinationAssetAccount.getBalance().add(savedTransaction.getAmount());
                    destinationAssetAccount.setBalance(newDestinationAccountBalance);
                    this.assetsAccountRepository.save(destinationAssetAccount);
                }
            } else {
                newSourceAccountBalance = assetAccount.getBalance().add(savedTransaction.getAmount());
            }

            assetAccount.setBalance(newSourceAccountBalance);
            this.assetsAccountRepository.save(assetAccount);
        }

        ZSUserDTO zsUserDTO = ZSUserDTO.builder()
                .username(savedTransaction.getUser().getUsername())
                .email(savedTransaction.getUser().getEmail())
                .firstName(savedTransaction.getUser().getFirstName())
                .lastName(savedTransaction.getUser().getLastName())
                .build();

        return new TransactionDTO(
                savedTransaction.getTransactionId(),
                new RecipientDTO(savedTransaction.getRecipientName(), savedTransaction.getRecipientAssetAccountId()),
                savedTransaction.getType(),
                savedTransaction.getAmount(),
                savedTransaction.getCategory(),
                savedTransaction.getDate().toString(),
                zsUserDTO,
                new AssetsAccountDTO(
                        savedTransaction.getSourceAssetAccount().getAssetAccountId(),
                        savedTransaction.getSourceAssetAccount().getName(),
                        savedTransaction.getSourceAssetAccount().getBalance(),
                        savedTransaction.getSourceAssetAccount().getType(),
                        savedTransaction.getSourceAssetAccount().getDescription()
                )
        );
    }
}
