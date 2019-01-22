package pl.robert.project.app.admin.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@NoArgsConstructor
public class AboutMeAdminDto extends AdminDto {

    private String roleName;

    public AboutMeAdminDto(String name, String login, String password, String roleName) {
        super(name, login, password);
        this.roleName = roleName;
    }
}
