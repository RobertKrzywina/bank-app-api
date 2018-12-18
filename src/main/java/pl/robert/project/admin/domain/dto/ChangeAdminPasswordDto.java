package pl.robert.project.admin.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@NoArgsConstructor
public class ChangeAdminPasswordDto extends AdminDto {

    private long id;
    private String oldPassword;
    private String reOldPassword;
    private String newPassword;
    private String reNewPassword;
}
