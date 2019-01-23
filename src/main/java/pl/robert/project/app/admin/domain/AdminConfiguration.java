package pl.robert.project.app.admin.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.project.app.admin.domain.dto.AboutMeAdminDto;
import pl.robert.project.app.admin.domain.dto.ChangeAdminPasswordDto;
import pl.robert.project.app.admin.domain.dto.CreateAdminDto;
import pl.robert.project.app.admin.domain.dto.ReadAdminDto;
import pl.robert.project.app.admin.query.BaseAdminQuery;

@Configuration
class AdminConfiguration {

    @Bean
    AdminFacade adminFacade(AdminRepository repository,
                            AdminFactory factory,
                            AdminValidator validator,
                            BaseAdminQuery baseQuery,
                            CreateAdminDto createDto,
                            ReadAdminDto readDto,
                            ChangeAdminPasswordDto changePasswordDto,
                            AboutMeAdminDto aboutMeDto) {
        return new AdminFacade(repository, factory, validator, baseQuery,
                               createDto,
                               readDto,
                               changePasswordDto,
                               aboutMeDto);
    }
}
