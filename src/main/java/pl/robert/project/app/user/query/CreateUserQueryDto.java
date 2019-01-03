package pl.robert.project.app.user.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserQueryDto {

    private String PESEL;
    private String firstName;
    private String lastName;
    private String decodedBCryptPassword;
}
