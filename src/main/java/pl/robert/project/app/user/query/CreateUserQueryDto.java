package pl.robert.project.app.user.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.robert.project.app.role.Role;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
public class CreateUserQueryDto {

    private String PESEL;
    private String firstName;
    private String lastName;
    private String password;

    private Set<Role> roles = new HashSet<>();
}
