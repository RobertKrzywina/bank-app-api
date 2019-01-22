package pl.robert.project.app.transaction.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public abstract class TransactionDto {

    private String title;
    private String description;
    private Double amount;
    private String receiverBankAccountNumber;

    @JsonIgnore
    private String pesel;

    @JsonIgnore
    private String senderBankAccountNumber;

    @JsonIgnore
    private Date date;

    @JsonIgnore
    private List<ObjectError> errors = new ArrayList<>();

    public TransactionDto(String title, String description, Double amount, String receiverBankAccountNumber,
                          String pesel, String senderBankAccountNumber, Date date) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.receiverBankAccountNumber = receiverBankAccountNumber;
        this.pesel = pesel;
        this.senderBankAccountNumber = senderBankAccountNumber;
        this.date = date;
    }
}
