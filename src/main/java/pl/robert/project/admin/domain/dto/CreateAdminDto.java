package pl.robert.project.admin.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@NoArgsConstructor
public class CreateAdminDto extends AdminDto {

    private String rePassword;
    private String reSpecialPassword;

    public CreateAdminDto(String login, String password, String specialPassword,
                          String rePassword, String reSpecialPassword) {
        super(login, password, specialPassword);
        this.rePassword = rePassword;
        this.reSpecialPassword = reSpecialPassword;
    }
}
