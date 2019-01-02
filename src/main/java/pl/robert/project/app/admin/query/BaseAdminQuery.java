package pl.robert.project.app.admin.query;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.robert.project.app.admin.domain.dto.ChangeAdminPasswordDto;
import pl.robert.project.app.admin.domain.dto.CreateAdminDto;
import pl.robert.project.app.admin.domain.dto.DeleteAdminDto;
import pl.robert.project.app.admin.domain.dto.ReadAdminDto;

@Component
@NoArgsConstructor
public class BaseAdminQuery {

    public CreateAdminQueryDto query(CreateAdminDto dto) {
        return new CreateAdminQueryDto(
                dto.getName(),
                dto.getLogin(),
                dto.getPassword(),
                dto.getRoles()
        );
    }

    public ReadAdminQueryDto query(ReadAdminDto dto) {

        return new ReadAdminQueryDto(
                dto.getId(),
                dto.getName(),
                dto.getRoles()
        );
    }

    public DeleteAdminQueryDto query(DeleteAdminDto dto) {

        return new DeleteAdminQueryDto(
                dto.getMessage()
        );
    }

    public ChangeAdminPasswordQueryDto query(ChangeAdminPasswordDto dto) {

            return new ChangeAdminPasswordQueryDto(
                    dto.getNewPassword()
            );
    }
}
