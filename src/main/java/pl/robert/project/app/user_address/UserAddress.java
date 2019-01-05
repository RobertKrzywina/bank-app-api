package pl.robert.project.app.user_address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "user_address_pesel")
    private String pesel;

    @Column(nullable = false)
    private String province;

    @Size(min = MIN_LENGTH_CITY, max = MAX_LENGTH_CITY)
    @Column(nullable = false)
    private String city;

    @Size(min = LENGTH_ZIP_CODE, max = LENGTH_ZIP_CODE)
    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Size(min = MIN_LENGTH_STREET, max = MAX_LENGTH_STREET)
    @Column(nullable = false)
    private String street;

    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @Transient
    @JsonIgnore
    private List<String> errors = new ArrayList<>();
}
