package pl.robert.project.app.transaction.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.project.app.transaction.domain.dto.SendTransactionDto;

@Configuration
class TransactionConfiguration {

    @Bean
    TransactionFacade transactionFacade(TransactionRepository repository,
                                        TransactionValidator validator,
                                        TransactionFactory factory,
                                        SendTransactionDto sendTransactionDto) {
        return new TransactionFacade(repository, validator, factory, sendTransactionDto);
    }
}
