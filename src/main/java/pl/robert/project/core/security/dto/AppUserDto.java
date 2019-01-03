package pl.robert.project.core.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.robert.project.app.role.Role;

import java.util.HashSet;
import java.util.Set;

@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {

    private String login;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
