package pl.robert.project.app.transaction.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.project.app.transaction.domain.dto.ReadTransactionDto;
import pl.robert.project.app.transaction.domain.dto.SendTransactionDto;
import pl.robert.project.app.transaction.query.BaseTransactionQuery;

@Configuration
class TransactionConfiguration {

    @Bean
    TransactionFacade transactionFacade(TransactionRepository repository,
                                        TransactionValidator validator,
                                        TransactionFactory factory,
                                        SendTransactionDto sendTransactionDto,
                                        ReadTransactionDto readTransactionDto,
                                        BaseTransactionQuery baseQuery) {
        return new TransactionFacade(repository, validator, factory,
                                     sendTransactionDto,
                                     readTransactionDto,
                                     baseQuery);
    }
}
