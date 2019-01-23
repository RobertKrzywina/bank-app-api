package pl.robert.project.app.admin.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String roleName;

    @JsonIgnore
    private String decodedBCryptPassword;

    @JsonIgnore
    private List<ObjectError> errors = new ArrayList<>();

    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
}
