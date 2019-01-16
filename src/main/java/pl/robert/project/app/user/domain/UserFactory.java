package pl.robert.project.app.user.domain;

import org.springframework.stereotype.Component;
import pl.robert.project.app.role.Role;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user_address.UserAddress;
import pl.robert.project.app.user_bank_account.UserBankAccount;
import pl.robert.project.app.user_contact.UserContact;

import java.util.Collections;
import java.util.HashSet;

@Component
class UserFactory {

    User create(CreateUserDto dto) {

        return User
                .builder()
                .pesel(dto.getPesel())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
                .decodedBCryptPassword(dto.getRePassword())
                .roleName("ROLE_USER")
                .roles(new HashSet<>(Collections.singleton(new Role(3L, "ROLE_USER"))))
                .contact(new UserContact(dto))
                .address(new UserAddress(dto))
                .bankAccount(new UserBankAccount(dto))
                .build();
    }
}
