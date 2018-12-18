package pl.robert.project.admin.domain.dto;

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

    public ChangeAdminSpecialPasswordDto(long id, String oldSpecialPassword, String reOldSpecialPassword,
                                         String newSpecialPassword, String reNewSpecialPassword) {
        this.id = id;
        this.oldSpecialPassword = oldSpecialPassword;
        this.reOldSpecialPassword = reOldSpecialPassword;
        this.newSpecialPassword = newSpecialPassword;
        this.reNewSpecialPassword = reNewSpecialPassword;
    }
}
