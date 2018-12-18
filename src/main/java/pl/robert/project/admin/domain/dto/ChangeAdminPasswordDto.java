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

    public ChangeAdminPasswordDto(long id, String oldPassword, String reOldPassword,
                                  String newPassword, String reNewPassword) {
        this.id = id;
        this.oldPassword = oldPassword;
        this.reOldPassword = reOldPassword;
        this.newPassword = newPassword;
        this.reNewPassword = reNewPassword;
    }
}
