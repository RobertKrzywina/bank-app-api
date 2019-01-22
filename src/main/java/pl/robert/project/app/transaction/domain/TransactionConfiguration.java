package pl.robert.project.app.transaction.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TransactionConfiguration {

    @Bean
    TransactionFacade transactionFacade(TransactionRepository repository,
                                        TransactionValidator validator) {
        return new TransactionFacade(repository, validator);
    }
}
