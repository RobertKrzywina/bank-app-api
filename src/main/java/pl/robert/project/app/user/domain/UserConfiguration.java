package pl.robert.project.app.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.robert.project.app.admin.domain.AdminFacade;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user.domain.dto.DeleteUserDto;
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
                          DeleteUserDto deleteDto,
                          UserContactFacade userContactFacade,
                          UserAddressFacade userAddressFacade,
                          UserBankAccountFacade userBankAccountFacade) {
        return new UserFacade(repository, factory, validator, baseQuery,
                              createDto, readDto, deleteDto,
                              userContactFacade, userAddressFacade, userBankAccountFacade);
    }
}
