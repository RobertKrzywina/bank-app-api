package pl.robert.project.app.admin.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@NoArgsConstructor
public class ChangeAdminSpecialPasswordDto extends AdminDto {

    private long id;
    private String oldSpecialPassword;
    private String reOldSpecialPassword;
    private String newSpecialPassword;
    private String reNewSpecialPassword;
}
