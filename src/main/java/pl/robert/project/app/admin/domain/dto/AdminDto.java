package pl.robert.project.app.admin.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.ObjectError;
import pl.robert.project.app.role.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
public abstract class AdminDto {

    private long id;
    private String name;
    private String login;
    private String password;
    private String rePassword;
    private String decodedBCryptPassword;
    private String roleName;

    private List<ObjectError> errors = new ArrayList<>();
    private Set<Role> roles = new HashSet<>();

    public AdminDto(long id, String name, String login,
                    String password, String rePassword,
                    String roleName) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.rePassword = rePassword;
        this.roleName = roleName;
    }

    public AdminDto(long id, String name, String roleName) {
        this.id = id;
        this.name = name;
        this.roleName = roleName;
    }
}
