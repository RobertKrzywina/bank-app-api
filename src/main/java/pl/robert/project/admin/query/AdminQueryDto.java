package pl.robert.project.admin.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminQueryDto {

    private String login;
    private String password;
    private String specialPassword;
    private String rePassword;
    private String reSpecialPassword;
}
