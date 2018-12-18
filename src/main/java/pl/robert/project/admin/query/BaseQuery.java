package pl.robert.project.admin.query;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.robert.project.admin.domain.dto.*;

@Component
@NoArgsConstructor
public class BaseQuery {

    public CreateAdminQueryDto query(CreateAdminDto dto) {
        return new CreateAdminQueryDto(
                dto.getName(),
                dto.getLogin(),
                dto.getPassword(),
                dto.getSpecialPassword(),
                dto.getRePassword(),
                dto.getReSpecialPassword()
        );
    }

    public ReadAdminQueryDto query(ReadAdminDto dto) {

        return new ReadAdminQueryDto(
                dto.getId(),
                dto.getName()
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

    public ChangeAdminSpecialPasswordQueryDto query(ChangeAdminSpecialPasswordDto dto) {

        return new ChangeAdminSpecialPasswordQueryDto(
                dto.getNewSpecialPassword()
        );
    }
}
