package pl.robert.project.app.admin.query;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.robert.project.app.admin.domain.dto.ChangeAdminPasswordDto;
import pl.robert.project.app.admin.domain.dto.CreateAdminDto;
import pl.robert.project.app.admin.domain.dto.ReadAdminDto;

@Component
@NoArgsConstructor
public class BaseAdminQuery {

    public CreateAdminQueryDto query(CreateAdminDto dto) {
        return new CreateAdminQueryDto(
                dto.getName(),
                dto.getLogin(),
                dto.getPassword(),
                dto.getRoleName()
        );
    }

    public ReadAdminQueryDto query(ReadAdminDto dto) {
        return new ReadAdminQueryDto(
                dto.getId(),
                dto.getName(),
                dto.getRoleName()
        );
    }

    public ChangeAdminPasswordQueryDto query(ChangeAdminPasswordDto dto) {
        return new ChangeAdminPasswordQueryDto(
                dto.getNewPassword()
        );
    }
}
