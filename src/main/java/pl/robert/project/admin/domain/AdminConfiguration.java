package pl.robert.project.admin.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AdminConfiguration {

    @Bean
    AdminFacade facade(AdminRepository repository,
                       AdminFactory factory,
                       AdminValidator validator) {
        return new AdminFacade(repository, factory, validator);
    }
}
