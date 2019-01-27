package pl.robert.project.app.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.project.app.transaction.domain.TransactionFacade;
import pl.robert.project.app.user.domain.dto.*;
import pl.robert.project.app.user.query.BaseUserQuery;
import pl.robert.project.app.address.AddressFacade;
import pl.robert.project.app.bank_account.BankAccountFacade;
import pl.robert.project.app.contact.ContactFacade;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade userFacade(UserRepository repository,
                          UserFactory factory,
                          UserValidator validator,
                          BaseUserQuery baseQuery,
                          CreateUserDto createDto,
                          ReadUserDto readDto,
                          ChangeUserPasswordDto changePasswordDto,
                          AboutMeUserDto aboutMeDto,
                          ContactFacade contactFacade,
                          AddressFacade addressFacade,
                          BankAccountFacade bankAccountFacade,
                          TransactionFacade transactionFacade) {
        return new UserFacade(repository, factory, validator, baseQuery,
                              createDto, readDto, changePasswordDto, aboutMeDto,
                              contactFacade,
                              addressFacade,
                              bankAccountFacade,
                              transactionFacade);
    }
}
