package pl.robert.project.app.user_address;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserAddressConfiguration {

    @Bean
    UserAddressFacade userAddressFacade(UserAddressRepository repository,
                                        UserAddressValidator validator) {
        return new UserAddressFacade(repository, validator);
    }
}
