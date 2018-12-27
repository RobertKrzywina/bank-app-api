package pl.robert.project.app.admin.domain;

import org.springframework.stereotype.Component;
import pl.robert.project.app.admin.domain.dto.CreateAdminDto;

@Component
class AdminFactory {

    Admin create(CreateAdminDto dto) {

        return Admin
                .builder()
                .name(dto.getName())
                .login(dto.getLogin())
                .password(dto.getPassword())
                .specialPassword(dto.getSpecialPassword())
                .roleName(dto.getRoleName())
                .roles(dto.getRoles())
                .build();
    }
}
