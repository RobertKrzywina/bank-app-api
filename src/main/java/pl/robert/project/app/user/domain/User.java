package pl.robert.project.app.user.domain;

import lombok.*;
import pl.robert.project.app.role.Role;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static pl.robert.project.app.user.domain.UserValidationStrings.*;

@Entity
@Table(name = "users")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class User {

    @Id
    @Size(min = LENGTH_PESEL, max = LENGTH_PESEL)
    @Column(nullable = false, unique = true)
    private String pesel;

    @Size(min = MIN_LENGTH_NAME, max = MAX_LENGTH_NAME)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(min = MIN_LENGTH_NAME, max = MAX_LENGTH_NAME)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Size(min = MIN_LENGTH_PASSWORD, max = MAX_LENGTH_PASSWORD)
    @Column(nullable = false)
    private String password;

    @Column(name = "decoded_BCrypt_password", nullable = false)
    private String decodedBCryptPassword;

    @Column(nullable = false, name = "role_name")
    private String roleName = "ROLE_USER";

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
}
