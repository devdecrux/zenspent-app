package com.decrux.zenspent.services.gettransactions;

import com.decrux.zenspent.entities.db.Transaction;
import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.*;
import com.decrux.zenspent.entities.enums.TransactionTypes;
import com.decrux.zenspent.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@RequiredArgsConstructor
public class GetTransactionsImpl implements GetTransactions {

    private final TransactionRepository transactionRepository;

    public PaginationResultDto<TransactionDto> getAllTransactions(ZSUser user, PaginationRequestDto paginationRequest) {
        Pageable pageable = PageRequest.of(paginationRequest.pageNumber(), paginationRequest.pageSize(), ASC, "date");
        Page<Transaction> transactions = this.transactionRepository.findAllByUser(user, pageable);
        return new PaginationResultDto<>(
                transactions.stream()
                        .map(transaction -> new TransactionDto(
                                transaction.getTransactionId(),
                                new TransactionParticipantDto(transaction.getRecipient().getName(), transaction.getRecipient().getAssetAccountId()),
                                transaction.getType(),
                                transaction.getAmount(),
                                transaction.getCategory(),
                                transaction.getDate().toString(),
                                ZSUserDto.builder()
                                        .username(transaction.getUser().getUsername())
                                        .email(transaction.getUser().getEmail())
                                        .firstName(transaction.getUser().getFirstName())
                                        .lastName(transaction.getUser().getLastName())
                                        .build(),
                                new TransactionParticipantDto(transaction.getPayer().getName(), transaction.getPayer().getAssetAccountId())
                        ))
                        .toList(),
                transactions.getTotalPages(),
                transactions.getTotalElements(),
                transactions.getSize(),
                transactions.getNumber(),
                transactions.isEmpty()
        );
    }

    public List<TransactionTypeDto> getTransactionTypes() {
        return Arrays.stream(
                        TransactionTypes.values())
                .map(type -> new TransactionTypeDto(type.name(), type.name()))
                .toList();
    }

}
