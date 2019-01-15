package pl.robert.project.app.user.domain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.robert.project.app.role.Role;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user_address.UserAddress;
import pl.robert.project.app.user_bank_account.UserBankAccount;
import pl.robert.project.app.user_contact.UserContact;

import java.util.HashSet;

@Component
class UserFactory {

    User create(CreateUserDto dto, UserContact contact, UserAddress address, UserBankAccount bankAccount) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        HashSet<Role> roles = new HashSet<>();
        roles.add(new Role(3L, "ROLE_USER"));

        return User
                .builder()
                .pesel(dto.getPesel())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .decodedBCryptPassword(dto.getPassword())
                .roleName("ROLE_USER")
                .roles(roles)
                .contact(contact)
                .address(address)
                .bankAccount(bankAccount)
                .build();
    }
}
