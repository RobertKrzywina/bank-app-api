package pl.robert.project.app.admin.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@NoArgsConstructor
public class ReadAdminDto extends AdminDto {

    private String roleName;

    public ReadAdminDto(long id, String name,
                        String roleName) {
        super(id, name);
        this.roleName = roleName;
    }
}
