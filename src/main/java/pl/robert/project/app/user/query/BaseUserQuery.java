package pl.robert.project.app.user.query;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user.domain.dto.DeleteUserDto;
import pl.robert.project.app.user.domain.dto.ReadUserDto;
import pl.robert.project.app.user_bank_account.UserBankAccount;

@Component
@NoArgsConstructor
public class BaseUserQuery {

    public CreateUserQueryDto query(CreateUserDto dto, UserBankAccount bankAccount) {
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
                bankAccount.getAccountNumber()
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
                dto.getAddress(),
                dto.getBankAccount()
        );
    }

    public DeleteUserQueryDto query(DeleteUserDto dto) {

        return new DeleteUserQueryDto(
                dto.getMessage()
        );
    }
}
