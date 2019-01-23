package pl.robert.project.app.transaction.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.robert.project.app.transaction.domain.dto.ReadTransactionDto;
import pl.robert.project.app.transaction.domain.dto.SendTransactionDto;
import pl.robert.project.app.transaction.domain.dto.TransactionDto;
import pl.robert.project.app.user_bank_account.UserBankAccountFacade;

@Component
@AllArgsConstructor
class TransactionValidator implements Validator, TransactionValidationStrings {

    private TransactionRepository transactionRepo;
    private UserBankAccountFacade userBankAccountFacade;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SendTransactionDto.class) ||
               clazz.isAssignableFrom(ReadTransactionDto.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        if (obj instanceof SendTransactionDto) {
            SendTransactionDto dto = (SendTransactionDto) obj;

            validateSendTransaction(dto, errors);
        } else if (obj instanceof ReadTransactionDto) {
            ReadTransactionDto dto = (ReadTransactionDto) obj;

            validateReadTransactionById(dto, errors);
        }

        ((TransactionDto) obj).setErrors(errors.getAllErrors());
    }

    private void validateSendTransaction(SendTransactionDto dto, Errors errors) {

        if (dto.getTitle() != null) {

            if (isFieldLengthCorrect(dto.getTitle(), MIN_TRANSACTION_TITLE_LENGTH, MAX_TRANSACTION_TITLE_LENGTH)) {
                errors.reject(C_TITLE_LENGTH, M_TITLE_LENGTH);
            }

        } else {
            errors.reject(C_TITLE_NULL, M_TITLE_NULL);
        }

        if (dto.getDescription() != null) {

            if (isFieldLengthCorrect(dto.getDescription(), MIN_TRANSACTION_DESCRIPTION_LENGTH, MAX_TRANSACTION_DESCRIPTION_LENGTH)) {
                errors.reject(C_DESCRIPTION_LENGTH, M_DESCRIPTION_LENGTH);
            }

        } else {
            errors.reject(C_DESCRIPTION_NULL, M_DESCRIPTION_NULL);
        }

        if (dto.getAmount() != null) {

            if (dto.getAmount() > userBankAccountFacade.getAccountBalanceFromGivenAccountNumber(dto.getSenderBankAccountNumber())) {
                errors.reject(C_BANK_ACCOUNT_BALANCE_NOT_ENOUGHT_MONEY, M_BANK_ACCOUNT_BALANCE_NOT_ENOUGHT_MONEY);
            }

        } else {
            errors.reject(C_AMOUNT_NULL, M_AMOUNT_NULL);
        }

        if (dto.getReceiverBankAccountNumber() != null) {

            if (isFieldLengthCorrect(dto.getReceiverBankAccountNumber(), BANK_ACCOUNT_NUMBER_LENGTH, BANK_ACCOUNT_NUMBER_LENGTH)) {
                errors.reject(C_RECEIVER_BANK_ACCOUNT_NUMBER_LENGTH, M_RECEIVER_BANK_ACCOUNT_NUMBER_LENGTH);
            }

            if (dto.getReceiverBankAccountNumber().equals(dto.getSenderBankAccountNumber())) {
                errors.reject(C_RECEIVER_BANK_ACCOUNT_NUMBER_WRONG, M_RECEIVER_BANK_ACCOUNT_NUMBER_WRONG);
            }

            if (userBankAccountFacade.findByAccountNumber(dto.getReceiverBankAccountNumber()) == null) {
                errors.reject(C_RECEIVER_BANK_ACCOUNT_NUMBER_NOT_EXISTS, M_RECEIVER_BANK_ACCOUNT_NUMBER_NOT_EXISTS);
            }

        } else {
            errors.reject(C_RECEIVER_BANK_ACCOUNT_NUMBER_NULL, M_RECEIVER_BANK_ACCOUNT_NUMBER_NULL);
        }
    }

    void validateGetAllTransactions(ReadTransactionDto dto, Errors errors) {

        if (transactionRepo.findAll().isEmpty()) {
            errors.reject(C_TRANSACTIONS_NOT_EXISTS, M_TRANSACTIONS_NOT_EXISTS);
        }

        dto.setErrors(errors.getAllErrors());
    }

    private void validateReadTransactionById(ReadTransactionDto dto, Errors errors) {

        if (transactionRepo.findById(dto.getId()) == null) {
            errors.reject(C_TRANSACTION_NOT_EXISTS, M_TRANSACTION_NOT_EXISTS);
        }
    }

    private boolean isFieldLengthCorrect(String fieldToCheck, int minLength, int maxLength) {
        return fieldToCheck.length() < minLength || fieldToCheck.length() > maxLength;
    }
}
