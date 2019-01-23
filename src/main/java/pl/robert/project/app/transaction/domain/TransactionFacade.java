package pl.robert.project.app.transaction.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import pl.robert.project.app.transaction.domain.dto.ReadTransactionDto;
import pl.robert.project.app.transaction.domain.dto.SendTransactionDto;
import pl.robert.project.app.transaction.query.BaseTransactionQuery;
import pl.robert.project.app.transaction.query.ReadTransactionQueryDto;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TransactionFacade {

    private TransactionRepository repository;
    private TransactionValidator validator;
    private TransactionFactory factory;
    private SendTransactionDto sendTransactionDto;
    private ReadTransactionDto readTransactionDto;
    private BaseTransactionQuery baseQuery;

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
                            transaction.getDateTime(),
                            transaction.getTitle(),
                            transaction.getDescription(),
                            transaction.getAmount(),
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
                readTransactionDto.setDateTime(transaction.getDateTime());
                readTransactionDto.setTitle(transaction.getTitle());
                readTransactionDto.setDescription(transaction.getDescription());
                readTransactionDto.setAmount(transaction.getAmount());
                readTransactionDto.setSenderBankAccountNumber(transaction.getSenderBankAccountNumber());
                readTransactionDto.setReceiverBankAccountNumber(transaction.getReceiverBankAccountNumber());

                return baseQuery.query(readTransactionDto);
            }
        }

        return null;
    }
}
