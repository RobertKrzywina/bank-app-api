package pl.robert.project.app.transaction.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter @Setter
@NoArgsConstructor
public class SendTransactionDto extends TransactionDto {

    @JsonIgnore
    private Double currentAccountBalance;

    public SendTransactionDto(String title, String description, Double amount, String receiverBankAccountNumber,
                              String pesel, String senderBankAccountNumber, LocalDateTime dateTime, Double currentAccountBalance) {
        super(title, description, amount, receiverBankAccountNumber, pesel, senderBankAccountNumber, dateTime);
        this.currentAccountBalance = currentAccountBalance;
    }
}
