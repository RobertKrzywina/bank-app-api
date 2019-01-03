package pl.robert.project.app.user.domain;

import lombok.AllArgsConstructor;
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

import java.util.ArrayList;
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

    public CreateUserQueryDto add(CreateUserDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {

                createUserDto.setPesel(dto.getPesel());
                createUserDto.setFirstName(dto.getFirstName());
                createUserDto.setLastName(dto.getLastName());
                createUserDto.setPassword(dto.getPassword());

                dto.getRoles().add(new Role(3L, "ROLE_USER"));
                createUserDto.setRoles(dto.getRoles());

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
                    user.getPassword()
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
}
