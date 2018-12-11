package pl.robert.project.admin.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.robert.project.admin.domain.dto.CreateAdminDto;

@AllArgsConstructor
@Component
class AdminFactory {

    Admin create(CreateAdminDto dto) {

        return Admin
                .builder()
                .login(dto.getLogin())
                .password(dto.getPassword())
                .specialPassword(dto.getSpecialPassword())
                .build();
    }
}
