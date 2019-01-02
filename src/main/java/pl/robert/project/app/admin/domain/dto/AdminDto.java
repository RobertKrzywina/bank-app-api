package pl.robert.project.app.admin.domain.dto;

import lombok.Getter;
import lombok.Setter;
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
    protected String roleName;

    protected List<String> errors = new ArrayList<>();
    protected Set<Role> roles = new HashSet<>();
}
