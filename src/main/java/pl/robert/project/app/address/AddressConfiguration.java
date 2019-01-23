package pl.robert.project.app.address;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AddressConfiguration {

    @Bean
    AddressFacade userAddressFacade(AddressRepository repository,
                                    AddressValidator validator) {
        return new AddressFacade(repository, validator);
    }
}
