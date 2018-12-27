package pl.robert.project.app.user.query;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.robert.project.app.user.domain.dto.CreateUserDto;

@Component
@NoArgsConstructor
public class BaseUserQuery {

    public CreateUserQueryDto query(CreateUserDto dto) {
        return new CreateUserQueryDto(
                dto.getPESEL(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPassword(),
                dto.getRoles()
        );
    }
}
