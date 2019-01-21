package pl.robert.project.app.user.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@NoArgsConstructor
public class ChangeUserPasswordDto extends UserDto {

    public ChangeUserPasswordDto(String password, String rePassword,
                                 String newPassword, String reNewPassword) {
        super(password, rePassword, newPassword, reNewPassword);
    }
}
