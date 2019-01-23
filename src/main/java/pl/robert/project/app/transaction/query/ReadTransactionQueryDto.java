package pl.robert.project.app.transaction.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReadTransactionQueryDto {

    private long id;
    private LocalDateTime dateTime;
    private String title;
    private String description;
    private Double amount;
    private String senderBankAccountNumber;
    private String receiverBankAccountNumber;
}
