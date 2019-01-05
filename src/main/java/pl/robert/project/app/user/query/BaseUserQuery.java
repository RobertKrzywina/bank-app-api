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
                dto.getContact(),
                dto.getAddress()
        );
    }

    public ReadUserQueryDto query(ReadUserDto dto) {

        return new ReadUserQueryDto(
                dto.getPesel(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPassword(),
                dto.getDecodedBCryptPassword(),
                dto.getContact(),
                dto.getAddress()
        );
    }

    public DeleteUserQueryDto query(DeleteUserDto dto) {

        return new DeleteUserQueryDto(
                dto.getMessage()
        );
    }
}
