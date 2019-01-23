package pl.robert.project.app.transaction.query;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.robert.project.app.transaction.domain.dto.ReadTransactionDto;

@Component
@NoArgsConstructor
public class BaseTransactionQuery {

    public ReadTransactionQueryDto query(ReadTransactionDto dto) {
        return new ReadTransactionQueryDto(
                dto.getId(),
                dto.getDateTime(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getAmount(),
                dto.getSenderBankAccountNumber(),
                dto.getReceiverBankAccountNumber()
        );
    }
}
