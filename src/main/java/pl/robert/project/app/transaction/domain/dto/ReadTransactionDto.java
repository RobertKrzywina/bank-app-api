package pl.robert.project.app.transaction.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter @Setter
@NoArgsConstructor
public class ReadTransactionDto extends TransactionDto {

    public ReadTransactionDto(long id, String title, String description, Double amount, String receiverBankAccountNumber,
                              String senderBankAccountNumber, LocalDateTime dateTime) {
        super(id, title, description, amount, receiverBankAccountNumber, senderBankAccountNumber, dateTime);
    }
}
