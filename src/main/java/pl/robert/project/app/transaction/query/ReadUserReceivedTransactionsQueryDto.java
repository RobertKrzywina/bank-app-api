package pl.robert.project.app.transaction.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReadUserReceivedTransactionsQueryDto {

    private LocalDateTime dateTime;
    private String title;
    private String description;
    private String amount;
    private String senderBankAccountNumber;
}
