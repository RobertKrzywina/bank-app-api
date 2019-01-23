package pl.robert.project.app.transaction.domain;

import org.springframework.stereotype.Component;
import pl.robert.project.app.transaction.domain.dto.SendTransactionDto;
import pl.robert.project.app.user_bank_account.UserBankAccount;

import java.time.LocalDateTime;

@Component
class TransactionFactory {

    Transaction create(SendTransactionDto dto) {

    return Transaction
            .builder()
            .account(new UserBankAccount(dto.getPesel()))
            .dateTime(LocalDateTime.now())
            .title(dto.getTitle())
            .description(dto.getDescription())
            .amount(dto.getAmount())
            .senderBankAccountNumber(dto.getSenderBankAccountNumber())
            .receiverBankAccountNumber(dto.getReceiverBankAccountNumber())
            .build();
    }
}
