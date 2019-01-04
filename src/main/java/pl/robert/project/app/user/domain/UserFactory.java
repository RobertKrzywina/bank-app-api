package pl.robert.project.app.user.domain;

import org.springframework.stereotype.Component;
import pl.robert.project.app.user.domain.dto.CreateUserDto;

@Component
class UserFactory {

    User create(CreateUserDto dto) {

        return User
                .builder()
                .pesel(dto.getPesel())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
                .decodedBCryptPassword(dto.getDecodedBCryptPassword())
                .roleName(dto.getRoleName())
                .roles(dto.getRoles())
                .contact(dto.getContact())
                .address(dto.getAddress())
                .build();
    }
}
