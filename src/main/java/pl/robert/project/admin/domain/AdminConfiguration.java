package pl.robert.project.admin.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.project.admin.domain.dto.*;
import pl.robert.project.admin.query.BaseQuery;

@Configuration
class AdminConfiguration {

    @Bean
    AdminFacade facade(AdminRepository repository,
                       AdminFactory factory,
                       AdminValidator validator,
                       BaseQuery query,
                       CreateAdminDto createDto,
                       ReadAdminDto readDto,
                       DeleteAdminDto deleteDto,
                       ChangeAdminPasswordDto changePasswordDto,
                       ChangeAdminSpecialPasswordDto changeSpecialPasswordDto) {
        return new AdminFacade(repository, factory, validator, query,
                               createDto,
                               readDto,
                               deleteDto,
                               changePasswordDto,
                               changeSpecialPasswordDto);
    }
}
