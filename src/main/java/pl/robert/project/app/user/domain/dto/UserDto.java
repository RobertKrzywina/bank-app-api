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
    private Double accountBalance;

    @JsonIgnore
    private String decodedBCryptPassword;

    @JsonIgnore
    private List<ObjectError> errors = new ArrayList<>();

    public UserDto(String pesel, String firstName, String lastName,
                   String province, String city, String zipCode, String street, String houseNumber,
                   String email, String phoneNumber,
                   String password, String rePassword,
                   String accountNumber, Double accountBalance,
                   int type) {
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
        this.accountNumber = accountNumber;

        if (type == 1) { this.rePassword = rePassword; }

        else { this.accountBalance = accountBalance; }
    }

    public UserDto(String pesel, String firstName, String lastName,
                   String province, String city, String zipCode, String street, String houseNumber,
                   String email, String phoneNumber,
                   String decodedBCryptPassword,
                   String accountNumber, Double accountBalance) {
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
        this.decodedBCryptPassword = decodedBCryptPassword;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }
}
