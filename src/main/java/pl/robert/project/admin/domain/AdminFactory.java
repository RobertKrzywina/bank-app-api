package pl.robert.project.admin.domain;

import org.springframework.stereotype.Component;
import pl.robert.project.admin.domain.dto.CreateAdminDto;

@Component
class AdminFactory {

    Admin create(CreateAdminDto dto) {

        return Admin
                .builder()
                .name(dto.getName())
                .login(dto.getLogin())
                .password(dto.getPassword())
                .specialPassword(dto.getSpecialPassword())
                .build();
    }
}
