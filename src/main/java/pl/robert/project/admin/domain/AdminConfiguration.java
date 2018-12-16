package pl.robert.project.admin.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.project.admin.domain.dto.CreateAdminDto;

@Configuration
class AdminConfiguration {

    @Bean
    AdminFacade facade(AdminRepository repository,
                       AdminFactory factory,
                       AdminValidator validator,
                       CreateAdminDto createDto) {
        return new AdminFacade(repository, factory, validator, createDto);
    }
}
