package pl.robert.project.app.admin.domain.dto;

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
public class CreateAdminDto extends AdminDto {

    private String rePassword;
    private String reSpecialPassword;
    private String roleName;

    private Set<Role> roles = new HashSet<>();
}
