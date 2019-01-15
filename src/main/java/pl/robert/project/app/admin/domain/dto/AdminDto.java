package pl.robert.project.app.admin.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;
import pl.robert.project.app.role.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
public abstract class AdminDto {

    protected long id;
    protected String name;
    protected String login;
    protected String password;
    protected String decodedBCryptPassword;
    protected String roleName;

    protected List<ObjectError> errors = new ArrayList<>();
    protected Set<Role> roles = new HashSet<>();
}
