package com.decrux.zenspent.services.gettransactions;

import com.decrux.zenspent.entities.db.Transaction;
import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.TransactionDTO;
import com.decrux.zenspent.entities.dtos.TransactionParticipantDto;
import com.decrux.zenspent.entities.dtos.ZSUserDTO;
import com.decrux.zenspent.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTransactionsImpl implements GetTransactions {

    private final TransactionRepository transactionRepository;

    public List<TransactionDTO> getAllTransactions(ZSUser user) {
        List<Transaction> transactions = this.transactionRepository.findAllByUser(user);
        return transactions.stream()
                .map(transaction -> new TransactionDTO(
                        transaction.getTransactionId(),
                        new TransactionParticipantDto(transaction.getRecipientName(), transaction.getRecipientAssetAccountId()),
                        transaction.getType(),
                        transaction.getAmount(),
                        transaction.getCategory(),
                        transaction.getDate().toString(),
                        ZSUserDTO.builder()
                                .username(transaction.getUser().getUsername())
                                .email(transaction.getUser().getEmail())
                                .firstName(transaction.getUser().getFirstName())
                                .lastName(transaction.getUser().getLastName())
                                .build(),
                        new TransactionParticipantDto(transaction.getPayerName(), transaction.getPayerAssetAccountId())
                ))
                .toList();
    }

}
