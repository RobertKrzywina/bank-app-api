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
    private String amount;
    private String receiverBankAccountNumber;

    @JsonIgnore
    private String pesel;

    @JsonIgnore
    private String senderBankAccountNumber;

    @JsonIgnore
    private LocalDateTime dateTime;

    @JsonIgnore
    private List<ObjectError> errors = new ArrayList<>();
}
