package pl.robert.project.app.transaction.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public abstract class TransactionDto {

    private long id;
    private String title;
    private String description;
    private Double amount;
    private String receiverBankAccountNumber;

    @JsonIgnore
    private String pesel;

    @JsonIgnore
    private String senderBankAccountNumber;

    @JsonIgnore
    private LocalDateTime dateTime;

    @JsonIgnore
    private List<ObjectError> errors = new ArrayList<>();

    public TransactionDto(String title, String description, Double amount, String receiverBankAccountNumber,
                          String pesel, String senderBankAccountNumber, LocalDateTime dateTime) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.receiverBankAccountNumber = receiverBankAccountNumber;
        this.pesel = pesel;
        this.senderBankAccountNumber = senderBankAccountNumber;
        this.dateTime = dateTime;
    }

    public TransactionDto(long id, String title, String description, Double amount, String receiverBankAccountNumber,
                          String senderBankAccountNumber, LocalDateTime dateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.receiverBankAccountNumber = receiverBankAccountNumber;
        this.senderBankAccountNumber = senderBankAccountNumber;
        this.dateTime = dateTime;
    }
}
