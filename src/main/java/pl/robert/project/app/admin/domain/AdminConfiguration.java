package pl.robert.project.app.admin.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.robert.project.app.admin.domain.dto.*;
import pl.robert.project.app.admin.query.BaseAdminQuery;
import pl.robert.project.app.user.domain.UserFacade;

@Configuration
@Import({UserFacade.class})
class AdminConfiguration {

    @Bean
    AdminFacade facade(AdminRepository repository,
                       AdminFactory factory,
                       AdminValidator validator,
                       BaseAdminQuery query,
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
