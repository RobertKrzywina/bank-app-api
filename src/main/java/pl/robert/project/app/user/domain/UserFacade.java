package pl.robert.project.app.user.domain;

import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import pl.robert.project.app.role.Role;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user.query.BaseUserQuery;
import pl.robert.project.app.user.query.CreateUserQueryDto;

@AllArgsConstructor
public class UserFacade {

    private UserRepository repository;
    private UserFactory factory;
    private UserValidator validator;
    private BaseUserQuery baseQuery;
    private CreateUserDto createUserDto;

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
}
