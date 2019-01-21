package pl.robert.project.app.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.project.app.user.domain.dto.ChangeUserPasswordDto;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user.domain.dto.ReadUserDto;
import pl.robert.project.app.user.query.BaseUserQuery;
import pl.robert.project.app.user_address.UserAddressFacade;
import pl.robert.project.app.user_bank_account.UserBankAccountFacade;
import pl.robert.project.app.user_contact.UserContactFacade;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade userFacade(UserRepository repository,
                          UserFactory factory,
                          UserValidator validator,
                          BaseUserQuery baseQuery,
                          CreateUserDto createDto,
                          ReadUserDto readDto,
                          UserContactFacade userContactFacade,
                          UserAddressFacade userAddressFacade,
                          ChangeUserPasswordDto changePasswordDto,
                          UserBankAccountFacade userBankAccountFacade) {
        return new UserFacade(repository, factory, validator, baseQuery,
                              createDto, readDto,
                              userContactFacade, userAddressFacade, changePasswordDto,
                              userBankAccountFacade);
    }
}
