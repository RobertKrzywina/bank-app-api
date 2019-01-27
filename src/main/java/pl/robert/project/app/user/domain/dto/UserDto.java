package pl.robert.project.app.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public abstract class UserDto {

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

    @JsonIgnore
    private String accountNumber;

    @JsonIgnore
    private String accountBalance;

    @JsonIgnore
    private String decodedBCryptPassword;

    @JsonIgnore
    private List<ObjectError> errors = new ArrayList<>();
}
