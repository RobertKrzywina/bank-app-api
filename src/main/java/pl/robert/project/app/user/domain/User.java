package pl.robert.project.app.user.domain;

import lombok.*;
import pl.robert.project.app.role.Role;
import pl.robert.project.app.user_address.UserAddress;
import pl.robert.project.app.user_bank_account.UserBankAccount;
import pl.robert.project.app.user_contact.UserContact;

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
    @Column(unique = true, nullable = false, length = PESEL_LENGTH)
    private String pesel;

    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    @Column(nullable = false)
    private String password;

    @Column(name = "decoded_BCrypt_password", nullable = false)
    private String decodedBCryptPassword;

    @Column(name = "role_name", nullable = false)
    private String roleName = "ROLE_USER";

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "user_contact_pesel")
    private UserContact contact;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "user_address_pesel")
    private UserAddress address;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "user_bank_account_pesel")
    private UserBankAccount bankAccount;
}
