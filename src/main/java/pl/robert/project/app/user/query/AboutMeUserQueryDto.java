package pl.robert.project.app.user.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AboutMeUserQueryDto {

    private String pesel;
    private String firstName;
    private String lastName;
    private String province;
    private String city;
    private String zipCode;
    private String street;
    private String houseNumber;
    private String email;
    private String phoneNumber;
    private String password;
    private String accountNumber;
    private Double accountBalance;
}
