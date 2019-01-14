package pl.robert.project.app.user_address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.robert.project.app.user.domain.dto.CreateUserDto;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static pl.robert.project.app.user_address.UserAddressValidationStrings.*;

@Entity
@Table(name = "user_address")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {

    @Id
    @JsonIgnore
    @Column(name = "user_address_pesel", unique = true, nullable = false)
    private String pesel;

    @Column(nullable = false)
    private String province;

    @Size(min = CITY_MIN_LENGTH, max = CITY_MAX_LENGTH)
    @Column(nullable = false)
    private String city;

    @Column(name = "zip_code", nullable = false, length = ZIP_CODE_LENGTH)
    private String zipCode;

    @Size(min = STREET_MIN_LENGTH, max = STREET_MAX_LENGTH)
    @Column(nullable = false)
    private String street;

    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @Transient
    @JsonIgnore
    private List<String> errors = new ArrayList<>();

    public UserAddress(CreateUserDto dto) {
        pesel = dto.getPesel();
        province = dto.getAddress().getProvince();
        city = dto.getAddress().getCity();
        zipCode = dto.getAddress().getZipCode();
        street = dto.getAddress().getStreet();
        houseNumber = dto.getAddress().getHouseNumber();
    }
}
