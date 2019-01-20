package pl.robert.project.app.admin.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@NoArgsConstructor
public class ChangeAdminPasswordDto extends AdminDto {

    public ChangeAdminPasswordDto(String password, String rePassword,
                                  String newPassword, String reNewPassword) {
        super(password, rePassword, newPassword, reNewPassword);
    }
}
