package pl.robert.project.app.user.domain;

import lombok.AllArgsConstructor;
import pl.robert.project.app.admin.query.BaseAdminQuery;
import pl.robert.project.app.user.domain.dto.CreateUserDto;

@AllArgsConstructor
public class UserFacade {

    private UserRepository repository;
    private UserFactory factory;
    private UserValidator validator;
    private BaseAdminQuery baseQuery;
    private CreateUserDto createUserDto;

}
