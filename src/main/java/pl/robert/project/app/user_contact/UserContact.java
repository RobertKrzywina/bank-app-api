package pl.robert.project.app.user_contact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.ObjectError;
import pl.robert.project.app.user.domain.dto.CreateUserDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static pl.robert.project.app.user_contact.UserContactValidationStrings.PHONE_NUMBER_LENGTH;

@Entity
@Table(name = "user_contact")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserContact {

    @Id
    @JsonIgnore
    @Column(name = "user_contact_pesel", unique = true, nullable = false)
    private String pesel;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false, length = PHONE_NUMBER_LENGTH)
    private String phoneNumber;

    @Transient
    @JsonIgnore
    private List<ObjectError> errors = new ArrayList<>();

    public UserContact(CreateUserDto dto) {
        pesel = dto.getPesel();
        email = dto.getEmail();
        phoneNumber = dto.getPhoneNumber();
    }
}
