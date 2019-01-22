package pl.robert.project.app.transaction.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import pl.robert.project.app.transaction.domain.dto.SendTransactionDto;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@AllArgsConstructor
public class TransactionFacade {

    private TransactionRepository repository;
    private TransactionValidator validator;
    private TransactionFactory factory;
    private SendTransactionDto sendTransactionDto;

    public void sendTransaction(SendTransactionDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {

                sendTransactionDto.setDate(convertToDateViaSqlTimestamp(LocalDateTime.now()));
                sendTransactionDto.setTitle(dto.getTitle());
                sendTransactionDto.setDescription(isDescriptionEmpty(dto));
                sendTransactionDto.setAmount(dto.getAmount());
                sendTransactionDto.setSenderBankAccountNumber(dto.getSenderBankAccountNumber());
                sendTransactionDto.setReceiverBankAccountNumber(dto.getReceiverBankAccountNumber());
                sendTransactionDto.setPesel(dto.getPesel());

                repository.saveAndFlush(factory.create(dto));
            }
        }
    }

    private Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }

    private String isDescriptionEmpty(SendTransactionDto dto) {
        return dto.getDescription().isEmpty() ? "No description." : dto.getDescription();
    }
}
