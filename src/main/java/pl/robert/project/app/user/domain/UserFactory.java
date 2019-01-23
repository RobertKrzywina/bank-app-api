package pl.robert.project.app.user.domain;

import org.springframework.stereotype.Component;
import pl.robert.project.app.role.Role;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.address.Address;
import pl.robert.project.app.bank_account.BankAccount;
import pl.robert.project.app.contact.Contact;

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
                .contact(new Contact(dto))
                .address(new Address(dto))
                .bankAccount(new BankAccount(dto))
                .build();
    }
}
