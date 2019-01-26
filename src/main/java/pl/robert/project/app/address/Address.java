package pl.robert.project.app.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.validation.ObjectError;
import pl.robert.project.app.user.domain.dto.CreateUserDto;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static pl.robert.project.app.address.AddressValidationStrings.*;

@Entity
@Table(name = "addresses")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @JsonIgnore
    @Column(name = "address_owner_pesel", unique = true, nullable = false)
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
    private List<ObjectError> errors = new ArrayList<>();

    public Address(CreateUserDto dto) {
        pesel = dto.getPesel().trim();
        province = dto.getProvince().trim();
        city = dto.getCity().trim();
        zipCode = dto.getZipCode().trim();
        street = dto.getStreet().trim();
        houseNumber = dto.getHouseNumber().trim();
    }
}
