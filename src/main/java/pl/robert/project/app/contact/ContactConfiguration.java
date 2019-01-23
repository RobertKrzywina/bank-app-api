package pl.robert.project.app.contact;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ContactConfiguration {

    @Bean
    ContactFacade userContactFacade(ContactRepository repository,
                                    ContactValidator validator) {
        return new ContactFacade(repository, validator);
    }
}
