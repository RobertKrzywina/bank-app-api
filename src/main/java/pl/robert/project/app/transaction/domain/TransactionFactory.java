package pl.robert.project.app.transaction.domain;

import org.springframework.stereotype.Component;
import pl.robert.project.app.transaction.domain.dto.SendTransactionDto;

@Component
class TransactionFactory {

    Transaction create(SendTransactionDto dto) {

    return Transaction
            .builder()
            .date(dto.getDate())
            .title(dto.getTitle())
            .description(dto.getDescription())
            .amount(dto.getAmount())
            .senderBankAccountNumber(dto.getSenderBankAccountNumber())
            .receiverBankAccountNumber(dto.getReceiverBankAccountNumber())
            .build();
    }
}
