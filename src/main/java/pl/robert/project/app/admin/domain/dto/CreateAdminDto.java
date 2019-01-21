package pl.robert.project.app.admin.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@NoArgsConstructor
public class CreateAdminDto extends AdminDto {

    private String roleName;

    public CreateAdminDto(long id, String name, String login,
                          String password, String rePassword,
                          String roleName) {
        super(id, name, login, password, rePassword);
        this.roleName = roleName;
    }
}
