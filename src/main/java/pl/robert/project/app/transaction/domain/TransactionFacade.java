package pl.robert.project.app.transaction.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransactionFacade {

    private TransactionRepository repository;
    private TransactionValidator validator;
}
