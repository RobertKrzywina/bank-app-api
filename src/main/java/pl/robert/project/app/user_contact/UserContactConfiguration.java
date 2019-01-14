package pl.robert.project.app.user_contact;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserContactConfiguration {

    @Bean
    UserContactFacade userContactFacade(UserContactRepository repository,
                                        UserContactValidator validator) {
        return new UserContactFacade(repository, validator);
    }
}
