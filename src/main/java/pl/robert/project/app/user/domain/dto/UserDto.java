package pl.robert.project.app.user.domain.dto;

import lombok.Getter;
import lombok.Setter;
import pl.robert.project.app.role.Role;
import pl.robert.project.app.user_address.UserAddress;
import pl.robert.project.app.user_bank_account.UserBankAccount;
import pl.robert.project.app.user_contact.UserContact;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
public abstract class UserDto {

    protected String pesel;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String decodedBCryptPassword;
    protected String roleName = "ROLE_USER";
    protected UserContact contact;
    protected UserAddress address;
    protected UserBankAccount bankAccount;

    protected List<String> errors = new ArrayList<>();
    protected Set<Role> roles = new HashSet<>();
}
