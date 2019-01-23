package pl.robert.project.app.contact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.validation.ObjectError;
import pl.robert.project.app.user.domain.dto.CreateUserDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static pl.robert.project.app.contact.ContactValidationStrings.PHONE_NUMBER_LENGTH;

@Entity
@Table(name = "contacts")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @JsonIgnore
    @Column(name = "contact_owner_pesel", unique = true, nullable = false)
    private String pesel;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false, length = PHONE_NUMBER_LENGTH)
    private String phoneNumber;

    @Transient
    @JsonIgnore
    private List<ObjectError> errors = new ArrayList<>();

    public Contact(CreateUserDto dto) {
        pesel = dto.getPesel();
        email = dto.getEmail();
        phoneNumber = dto.getPhoneNumber();
    }
}
