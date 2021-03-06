package pl.robert.project.app.user.query;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.robert.project.app.user.domain.dto.AboutMeUserDto;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user.domain.dto.ReadUserDto;

@Component
@NoArgsConstructor
public class BaseUserQuery {

    public CreateUserQueryDto query(CreateUserDto dto) {
        return new CreateUserQueryDto(
                dto.getPesel(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getProvince(),
                dto.getCity(),
                dto.getZipCode(),
                dto.getStreet(),
                dto.getHouseNumber(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getPassword(),
                dto.getAccountNumber()
        );
    }

    public ReadUserQueryDto query(ReadUserDto dto) {
        return new ReadUserQueryDto(
                dto.getPesel(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getProvince(),
                dto.getCity(),
                dto.getZipCode(),
                dto.getStreet(),
                dto.getHouseNumber(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getPassword(),
                dto.getAccountNumber(),
                dto.getAccountBalance()
        );
    }

    public AboutMeUserQueryDto query(AboutMeUserDto dto) {
        return new AboutMeUserQueryDto(
                dto.getPesel(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getProvince(),
                dto.getCity(),
                dto.getZipCode(),
                dto.getStreet(),
                dto.getHouseNumber(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getPassword(),
                dto.getAccountNumber(),
                dto.getAccountBalance()
        );
    }
}
