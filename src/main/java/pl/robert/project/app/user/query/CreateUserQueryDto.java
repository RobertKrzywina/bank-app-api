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
    private String email;
    private String phoneNumber;
    private String province;
    private String city;
    private String zipCode;
    private String street;
    private String houseNumber;
}
