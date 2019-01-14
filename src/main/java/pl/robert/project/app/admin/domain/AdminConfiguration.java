package pl.robert.project.app.admin.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.robert.project.app.admin.domain.dto.ChangeAdminPasswordDto;
import pl.robert.project.app.admin.domain.dto.CreateAdminDto;
import pl.robert.project.app.admin.domain.dto.DeleteAdminDto;
import pl.robert.project.app.admin.domain.dto.ReadAdminDto;
import pl.robert.project.app.admin.query.BaseAdminQuery;
import pl.robert.project.app.user.domain.UserFacade;

@Configuration
class AdminConfiguration {

    @Bean
    AdminFacade adminFacade(AdminRepository repository,
                            AdminFactory factory,
                            AdminValidator validator,
                            BaseAdminQuery query,
                            CreateAdminDto createDto,
                            ReadAdminDto readDto,
                            DeleteAdminDto deleteDto,
                            ChangeAdminPasswordDto changePasswordDto) {
        return new AdminFacade(repository, factory, validator, query,
                               createDto,
                               readDto,
                               deleteDto,
                               changePasswordDto);
    }
}
