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
                                        TransactionFactory factory,
                                        TransactionValidator validator,
                                        BaseTransactionQuery baseQuery,
                                        SendTransactionDto sendDto,
                                        ReadTransactionDto readDto) {
        return new TransactionFacade(repository, factory, validator, baseQuery,
                                     sendDto,
                                     readDto);
    }
}
