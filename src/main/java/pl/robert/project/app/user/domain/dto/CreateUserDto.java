package pl.robert.project.app.user.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.robert.project.app.role.Role;

import java.util.HashSet;
import java.util.Set;

@Component
@Getter @Setter
@NoArgsConstructor
public class CreateUserDto extends UserDto {

    private String rePassword;
    private String roleName;

    private Set<Role> roles = new HashSet<>();
}
