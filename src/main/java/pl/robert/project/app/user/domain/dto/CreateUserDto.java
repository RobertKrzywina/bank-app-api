package pl.robert.project.app.user.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter @Setter
@NoArgsConstructor
public class CreateUserDto {

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
    private String rePassword;

    public CreateUserDto(String pesel, String firstName, String lastName,
                         String province, String city, String zipCode,
                         String street, String houseNumber, String email,
                         String phoneNumber, String password, String rePassword) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.province = province;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.rePassword = rePassword;
    }

    private List<ObjectError> errors = new ArrayList<>();
}
