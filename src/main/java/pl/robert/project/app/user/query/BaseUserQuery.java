package pl.robert.project.app.user.query;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user.domain.dto.DeleteUserDto;
import pl.robert.project.app.user.domain.dto.ReadUserDto;

@Component
@NoArgsConstructor
public class BaseUserQuery {

    public CreateUserQueryDto query(CreateUserDto dto) {
        return new CreateUserQueryDto(
                dto.getPesel(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getDecodedBCryptPassword(),
                dto.getContact().getEmail(),
                dto.getContact().getPhoneNumber(),
                dto.getAddress().getProvince(),
                dto.getAddress().getCity(),
                dto.getAddress().getZipCode(),
                dto.getAddress().getStreet(),
                dto.getAddress().getHouseNumber()
        );
    }

    public ReadUserQueryDto query(ReadUserDto dto) {

        return new ReadUserQueryDto(
                dto.getPesel(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPassword(),
                dto.getContact().getEmail(),
                dto.getContact().getPhoneNumber(),
                dto.getAddress().getProvince(),
                dto.getAddress().getCity(),
                dto.getAddress().getZipCode(),
                dto.getAddress().getStreet(),
                dto.getAddress().getHouseNumber()
        );
    }

    public DeleteUserQueryDto query(DeleteUserDto dto) {

        return new DeleteUserQueryDto(
                dto.getMessage()
        );
    }
}
