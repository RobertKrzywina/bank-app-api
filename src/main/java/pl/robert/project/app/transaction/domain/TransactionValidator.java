package pl.robert.project.app.transaction.domain;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.robert.project.app.transaction.domain.dto.ReadTransactionDto;
import pl.robert.project.app.transaction.domain.dto.SendTransactionDto;
import pl.robert.project.app.transaction.domain.dto.TransactionDto;

@Component
class TransactionValidator implements Validator, TransactionValidationStrings {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SendTransactionDto.class) || clazz.isAssignableFrom(ReadTransactionDto.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        if (obj instanceof SendTransactionDto) {
            SendTransactionDto dto = (SendTransactionDto) obj;

            validateSendTransaction(dto, errors);
        }

        ((TransactionDto) obj).setErrors(errors.getAllErrors());
    }

    private void validateSendTransaction(SendTransactionDto dto, Errors errors) {

    }

    void validateGetAllTransactions(ReadTransactionDto dto, Errors errors) {

    }
}
