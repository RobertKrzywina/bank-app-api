package pl.robert.project.app.transaction.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import pl.robert.project.app.transaction.domain.dto.ReadTransactionDto;
import pl.robert.project.app.transaction.domain.dto.ReadUserTransactionsDto;
import pl.robert.project.app.transaction.domain.dto.SendTransactionDto;
import pl.robert.project.app.transaction.query.*;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TransactionFacade {

    private TransactionRepository repository;
    private TransactionFactory factory;
    private TransactionValidator validator;
    private BaseTransactionQuery baseQuery;
    private SendTransactionDto sendTransactionDto;
    private ReadTransactionDto readTransactionDto;

    public void sendTransaction(SendTransactionDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {

                sendTransactionDto.setTitle(dto.getTitle());
                sendTransactionDto.setDescription(dto.getDescription());
                sendTransactionDto.setAmount(dto.getAmount());
                sendTransactionDto.setSenderBankAccountNumber(dto.getSenderBankAccountNumber());
                sendTransactionDto.setReceiverBankAccountNumber(dto.getReceiverBankAccountNumber());
                sendTransactionDto.setPesel(dto.getPesel());

                repository.saveAndFlush(factory.create(dto));
            }
        }
    }

    public List<ReadTransactionQueryDto> getAll(ReadTransactionDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            List<Transaction> transactions = repository.findAll();
            validator.validateGetAllTransactions(dto, result);

            if (!result.hasErrors()) {
                List<ReadTransactionQueryDto> transactionsDto = new ArrayList<>();

                for (Transaction transaction : transactions) {
                    transactionsDto.add(new ReadTransactionQueryDto(
                            transaction.getId(),
                            transaction.getDateOfCompletion(),
                            transaction.getTitle(),
                            transaction.getDescription(),
                            transaction.getAmount().toString(),
                            transaction.getSenderBankAccountNumber(),
                            transaction.getReceiverBankAccountNumber()
                    ));
                }

                return transactionsDto;
            }
        }

        return null;
    }

    public ReadTransactionQueryDto getTransactionById(long id, ReadTransactionDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {

                Transaction transaction = repository.findById(id);

                readTransactionDto.setId(transaction.getId());
                readTransactionDto.setDateTime(transaction.getDateOfCompletion());
                readTransactionDto.setTitle(transaction.getTitle());
                readTransactionDto.setDescription(transaction.getDescription());
                readTransactionDto.setAmount(transaction.getAmount().toString());
                readTransactionDto.setSenderBankAccountNumber(transaction.getSenderBankAccountNumber());
                readTransactionDto.setReceiverBankAccountNumber(transaction.getReceiverBankAccountNumber());

                return baseQuery.query(readTransactionDto);
            }
        }

        return null;
    }

    public List<ReadUserTransactionsQueryDto> getAllUserTransactions(String bankAccountNumber,
                                                                     ReadUserTransactionsDto dto,
                                                                     BindingResult result) {
        if (validator.supports(dto.getClass())) {

            List<Transaction> transactions = repository.findAllByBankAccountNumber(bankAccountNumber);
            validator.validateGetAllUserTransactions(transactions, dto, result);

            if (!result.hasErrors()) {
                List<ReadUserTransactionsQueryDto> transactionsDto = new ArrayList<>();

                for (Transaction transaction : transactions) {
                    transactionsDto.add(new ReadUserTransactionsQueryDto(
                            transaction.getDateOfCompletion(),
                            transaction.getTitle(),
                            transaction.getDescription(),
                            transaction.getAmount().toString(),
                            transaction.getSenderBankAccountNumber(),
                            transaction.getReceiverBankAccountNumber()
                    ));
                }

                return transactionsDto;
            }
        }

        return null;
    }

    public List<ReadUserSentTransactionsQueryDto> getAllUserSentTransactions(String bankAccountNumber,
                                                                             ReadUserTransactionsDto dto,
                                                                             BindingResult result) {
        if (validator.supports(dto.getClass())) {

            List<Transaction> transactions = repository.findAllBySenderBankAccountNumber(bankAccountNumber);
            validator.validateGetAllUserSentTransactions(transactions, dto, result);

            if (!result.hasErrors()) {
                List<ReadUserSentTransactionsQueryDto> transactionsDto = new ArrayList<>();

                for (Transaction transaction : transactions) {
                    transactionsDto.add(new ReadUserSentTransactionsQueryDto(
                            transaction.getDateOfCompletion(),
                            transaction.getTitle(),
                            transaction.getDescription(),
                            transaction.getAmount().toString(),
                            transaction.getReceiverBankAccountNumber()
                    ));
                }

                return transactionsDto;
            }
        }

        return null;
    }

    public List<ReadUserReceivedTransactionsQueryDto> getAllUserReceivedTransactions(String bankAccountNumber,
                                                                                     ReadUserTransactionsDto dto,
                                                                                     BindingResult result) {
        if (validator.supports(dto.getClass())) {

            List<Transaction> transactions = repository.findAllByReceiverBankAccountNumber(bankAccountNumber);
            validator.validateGetAllUserReceivedTransactions(transactions, dto, result);

            if (!result.hasErrors()) {
                List<ReadUserReceivedTransactionsQueryDto> transactionsDto = new ArrayList<>();

                for (Transaction transaction : transactions) {
                    transactionsDto.add(new ReadUserReceivedTransactionsQueryDto(
                            transaction.getDateOfCompletion(),
                            transaction.getTitle(),
                            transaction.getDescription(),
                            transaction.getAmount().toString(),
                            transaction.getSenderBankAccountNumber()
                    ));
                }

                return transactionsDto;
            }
        }

        return null;
    }

    public void addTransaction(String pesel, Double money, String receiverBankAccountNumber) {
        validator.modifyTransaction(sendTransactionDto);

        sendTransactionDto.setPesel(pesel);
        sendTransactionDto.setAmount(money.toString());
        sendTransactionDto.setReceiverBankAccountNumber(receiverBankAccountNumber);

        repository.saveAndFlush(factory.create(sendTransactionDto));
    }
}
