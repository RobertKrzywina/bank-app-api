package pl.robert.project.app.user.domain.dto;

import lombok.Getter;
import lombok.Setter;
import pl.robert.project.app.role.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
public abstract class UserDto {

    protected String PESEL;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String roleName;

    protected List<String> errors = new ArrayList<>();
    protected Set<Role> roles = new HashSet<>();
}
