package pl.robert.project.admin.domain;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.robert.project.admin.domain.dto.CreateAdminDto;
import pl.robert.project.admin.query.CreateAdminQueryDto;

@Component
@NoArgsConstructor
class AdminFactory {

    Admin create(CreateAdminDto dto) {

        return Admin
                .builder()
                .login(dto.getLogin())
                .password(dto.getPassword())
                .specialPassword(dto.getSpecialPassword())
                .build();
    }

    CreateAdminQueryDto query(CreateAdminDto dto) {
        return new CreateAdminQueryDto(
                dto.getLogin(),
                dto.getPassword(),
                dto.getSpecialPassword(),
                dto.getRePassword(),
                dto.getReSpecialPassword()
        );
    }
}
