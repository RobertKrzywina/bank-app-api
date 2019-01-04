package pl.robert.project.app.user.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import pl.robert.project.app.role.Role;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user.domain.dto.DeleteUserDto;
import pl.robert.project.app.user.domain.dto.ReadUserDto;
import pl.robert.project.app.user.query.BaseUserQuery;
import pl.robert.project.app.user.query.CreateUserQueryDto;
import pl.robert.project.app.user.query.DeleteUserQueryDto;
import pl.robert.project.app.user.query.ReadUserQueryDto;
import pl.robert.project.app.user_contact.UserContact;
import pl.robert.project.app.user_contact.UserContactFacade;
import pl.robert.project.core.security.dto.AppUserDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@AllArgsConstructor
public class UserFacade implements UserValidationStrings {

    private UserRepository repository;
    private UserFactory factory;
    private UserValidator validator;
    private BaseUserQuery baseQuery;
    private CreateUserDto createUserDto;
    private ReadUserDto readUserDto;
    private DeleteUserDto deleteUserDto;
    private UserContactFacade userContactFacade;

    public CreateUserQueryDto add(CreateUserDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            UserContact contact = new UserContact();

            contact.setPesel(dto.getPesel());
            contact.setEmail(dto.getContact().getEmail());
            contact.setPhoneNumber(dto.getContact().getPhoneNumber());

            userContactFacade.validate(contact, result);
            validator.validate(dto, result);

            if (!result.hasErrors()) {

                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                createUserDto.setPesel(dto.getPesel());
                createUserDto.setFirstName(dto.getFirstName());
                createUserDto.setLastName(dto.getLastName());
                createUserDto.setPassword(dto.getPassword());
                createUserDto.setDecodedBCryptPassword(dto.getPassword());

                dto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
                dto.setDecodedBCryptPassword(createUserDto.getDecodedBCryptPassword());

                dto.getRoles().add(new Role(3L, "ROLE_USER"));
                createUserDto.setRoles(dto.getRoles());

                createUserDto.setContact(contact);
                dto.setContact(createUserDto.getContact());

                userContactFacade.saveUserContact(contact);
                repository.saveAndFlush(factory.create(dto));

                return baseQuery.query(createUserDto);
            }
        }

        return null;
    }

    public List<ReadUserQueryDto> getAll() {
        List<User> users = repository.findAll();
        List<ReadUserQueryDto> usersDto = new ArrayList<>();

        for (User user : users) {
            usersDto.add(new ReadUserQueryDto(
                    user.getPesel(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPassword(),
                    user.getDecodedBCryptPassword(),
                    user.getContact().getEmail(),
                    user.getContact().getPhoneNumber()
            ));
        }

        return usersDto;
    }

    public ReadUserQueryDto getUserByPesel(String pesel, ReadUserDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {

                User user = repository.findByPesel(pesel);

                readUserDto.setPesel(user.getPesel());
                readUserDto.setFirstName(user.getFirstName());
                readUserDto.setLastName(user.getLastName());
                readUserDto.setPassword(user.getPassword());
                readUserDto.setDecodedBCryptPassword(user.getDecodedBCryptPassword());

                return baseQuery.query(readUserDto);
            }
        }

        return null;
    }

    public DeleteUserQueryDto delete() {
        List<User> users = repository.findAll();

        if (users != null) {
            repository.deleteAll();
            deleteUserDto.setMessage(M_DELETED_ALL_USERS);
        } else {
            deleteUserDto.setMessage(M_NO_USERS);
        }

        return baseQuery.query(deleteUserDto);
    }

    public DeleteUserQueryDto deleteUserByPesel(String pesel, DeleteUserDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {
                repository.delete(repository.findByPesel(pesel));
                deleteUserDto.setMessage(M_USER_DELETED);

                return baseQuery.query(deleteUserDto);
            }
        }

        return null;
    }

    public HashMap<String, Object> aboutMe(Authentication authentication) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("NAME", authentication.getName());
        map.put("AUTHORITIES", authentication.getAuthorities());
        map.put("CREDENTIALS", authentication.getCredentials());
        map.put("DETIALS", authentication.getDetails());
        map.put("PRINCIPAL", authentication.getPrincipal());
        map.put("CLASS", authentication.getClass());

        return map;
    }

    public AppUserDto getAppUser(String login) {
        User user = repository.findByPesel(login);

        if (user != null) {
            return new AppUserDto(
                    user.getPesel(),
                    user.getPassword(),
                    user.getRoles()
            );
        }

        return null;
    }
}
